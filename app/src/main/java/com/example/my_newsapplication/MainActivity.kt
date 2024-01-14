package com.example.my_newsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.my_newsapplication.showcase.navigationgraph.NavigationGraph
import com.example.my_newsapplication.ui.theme.My_NewsApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {//1 SplashScreen
            setKeepOnScreenCondition{
                viewModel.splashState //we need the splashState, so when it get false, we gonna exit the splashScreen
            }
        }
        setContent {
            My_NewsApplicationTheme {

                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode //pass the negative of SystemInDarkMode

                    )
                }

                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background )){
                    val startDestination = viewModel.startDestination
                    NavigationGraph(launchDestination = startDestination )



                }
            }
                }
            }
        }

