package com.example.my_newsapplication.domain.management

import kotlinx.coroutines.flow.Flow


interface LocalUserManagment {
    suspend fun storeAppEntry()

     //able to read this app entry we saved
    fun retrieveAppEntry() : Flow<Boolean>

}