package com.example.my_newsapplication.showcase.navigationgraph

sealed class Dispatch(
    val dispatch: String
){
    object initiationScreen : Dispatch(dispatch = "initiationScreen")
    object MainScreen : Dispatch(dispatch = "mainScreen")
    object SearchScreen : Dispatch(dispatch = "searchScreen")
    object BookmarkScreen : Dispatch(dispatch = "bookmarkScreen")
    object DetailsScreen : Dispatch(dispatch = "detailsScreen")
    object AppLaunchNavigation : Dispatch(dispatch = "appLaunchNavigation")
    object HeadlineNavigate : Dispatch(dispatch = "headlineNavigation")
    object HeadlineScreenNavigation : Dispatch(dispatch = "headlineScreenNavigation")


}

