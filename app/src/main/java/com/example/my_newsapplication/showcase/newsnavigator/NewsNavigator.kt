package com.example.my_newsapplication.showcase.newsnavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.my_newsapplication.showcase.main.MainViewModel
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.showcase.bookmark.BookmarkScreen
import com.example.my_newsapplication.showcase.bookmark.BookmarkViewModel
import com.example.my_newsapplication.showcase.details.DetailsEvent
import com.example.my_newsapplication.showcase.details.DetailsScreen
import com.example.my_newsapplication.showcase.details.DetailsViewModel
import com.example.my_newsapplication.showcase.main.MainScreen
import com.example.my_newsapplication.showcase.navigationgraph.Dispatch
import com.example.my_newsapplication.showcase.newsnavigator.elements.BottomNavigationItem
import com.example.my_newsapplication.showcase.newsnavigator.elements.TheNewsBottomNavigation
import com.example.my_newsapplication.showcase.search.SearchScreen
import com.example.my_newsapplication.showcase.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_main_24, text = "Main"),
            BottomNavigationItem(icon = R.drawable.ic_search_24, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark_24, text = "Bookmark")
        )
    }
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Dispatch.MainScreen.dispatch -> 0
            Dispatch.SearchScreen.dispatch -> 1
            Dispatch.BookmarkScreen.dispatch -> 2

            else -> 0 //not a possible option but we need to add it


        }
    }
    //When the user is in the details screen, the bottom navigation will be hided.
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Dispatch.MainScreen.dispatch ||
                backstackState?.destination?.route == Dispatch.SearchScreen.dispatch ||
                backstackState?.destination?.route == Dispatch.BookmarkScreen.dispatch
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {

                TheNewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                dispatch = Dispatch.MainScreen.dispatch
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                dispatch = Dispatch.SearchScreen.dispatch
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                dispatch = Dispatch.BookmarkScreen.dispatch
                            )
                        }


                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Dispatch.MainScreen.dispatch,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){
            composable(route = Dispatch.MainScreen.dispatch){
                val viewModel: MainViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                MainScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController = navController, dispatch = Dispatch.SearchScreen.dispatch)
                    },
                    navigateToDetails = {article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )

                    }
                )
            }

            composable(route = Dispatch.SearchScreen.dispatch){
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                        navController = navController,
                        article = it
                    )
                    }
                )
            }
            composable(route = Dispatch.DetailsScreen.dispatch){
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUpward = { navController.navigateUp()}
                        )
                    }
                }
            composable(route = Dispatch.BookmarkScreen.dispatch){
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = {article ->
                    navigateToDetails(navController = navController, article = article)

                })
            }

        }

    }
}

private fun navigateToTab(navController: NavController,dispatch: String){
    navController.navigate(dispatch){
        navController.graph.startDestinationRoute?.let {mainScreen ->
            popUpTo(mainScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }


}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Dispatch.DetailsScreen.dispatch
    )
}

