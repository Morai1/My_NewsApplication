package com.example.my_newsapplication.showcase.initiation

// This class have the events
// that will be sent from the UI to the ViewModel
sealed class InitiationEvent {
    object StoreAppEntry: InitiationEvent()
}