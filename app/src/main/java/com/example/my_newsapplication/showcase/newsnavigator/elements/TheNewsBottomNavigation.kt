package com.example.my_newsapplication.showcase.newsnavigator.elements

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my_newsapplication.R
import com.example.my_newsapplication.showcase.Distance.ExtraSmallPadding2
import com.example.my_newsapplication.showcase.Distance.IconSize
import com.example.my_newsapplication.ui.theme.My_NewsApplicationTheme

@Composable
fun TheNewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int, //if the item is selected
    onItemClick:(Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed{index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally){
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.body),
                    selectedTextColor = colorResource(id = R.color.body),
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.background


                )

            )

        }
    }
    
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TheNewsBottomNavigationPreview() {
    My_NewsApplicationTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            TheNewsBottomNavigation(
                items = listOf(
                    BottomNavigationItem(icon = R.drawable.ic_main_24, text = "Main"),
                    BottomNavigationItem(icon = R.drawable.ic_search_24, text = "Search"),
                    BottomNavigationItem(icon = R.drawable.ic_bookmark_24, text = "Bookmark")
                    ),
                selected = 0,
                onItemClick = {}
            )
        }
    }
    
}