package com.example.my_newsapplication.showcase.navigationgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.my_newsapplication.showcase.bookmark.BookmarkScreen
import com.example.my_newsapplication.showcase.bookmark.BookmarkViewModel
import com.example.my_newsapplication.showcase.initiation.InitiationScreen
import com.example.my_newsapplication.showcase.initiation.InitiationViewModel
import com.example.my_newsapplication.showcase.newsnavigator.NewsNavigator

@Composable
fun NavigationGraph(
    launchDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = launchDestination) {
        navigation(
            route = Dispatch.AppLaunchNavigation.dispatch,
            startDestination = Dispatch.initiationScreen.dispatch
        ) {
            composable(
                route = Dispatch.initiationScreen.dispatch
            ) {
                val viewModel: InitiationViewModel = hiltViewModel()
                InitiationScreen(
                    event = {
                        viewModel.onEvent(it)
                    }
                )
            }
        }
            navigation(
                route = Dispatch.HeadlineNavigate.dispatch,
                startDestination = Dispatch.HeadlineScreenNavigation.dispatch
            ){
                composable(route = Dispatch.HeadlineScreenNavigation.dispatch){NavBackStackEntry
                    NewsNavigator()
                }

            }
        }

}