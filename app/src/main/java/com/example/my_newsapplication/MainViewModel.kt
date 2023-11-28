package com.example.my_newsapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_newsapplication.domain.usecases.application_entry.AppEntryUseCases
import com.example.my_newsapplication.showcase.navigationgraph.Dispatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {


    var splashState by mutableStateOf(true)
        private set

    var startDestination by  mutableStateOf(Dispatch.AppLaunchNavigation.dispatch)
        private set

    init {
        appEntryUseCases.retrieveAppEntry().onEach {
                initiateWithMainScreen ->
            if(initiateWithMainScreen){
                startDestination = Dispatch.HeadlineNavigate.dispatch
            }else{
                startDestination = Dispatch.AppLaunchNavigation.dispatch
            }
            delay(200) //the program will wait for 200 milliseconds before proceeding to execute the next instructions. useful for introduce a waiting period in the code.
            splashState = false
        }.launchIn(viewModelScope)
    }
}