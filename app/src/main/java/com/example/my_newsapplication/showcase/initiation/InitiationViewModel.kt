package com.example.my_newsapplication.showcase.initiation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_newsapplication.domain.usecases.application_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitiationViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCases
): ViewModel() {


    fun onEvent(event: InitiationEvent){
        when(event){
            is InitiationEvent.StoreAppEntry -> {
                storeAppEntry()

            }
        }
    }

    private fun storeAppEntry() {
        viewModelScope.launch {
            appEntryUseCase.storeAppEntry()
        }
    }

}