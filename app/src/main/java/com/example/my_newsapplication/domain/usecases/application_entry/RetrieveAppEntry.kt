package com.example.my_newsapplication.domain.usecases.application_entry

import com.example.my_newsapplication.domain.management.LocalUserManagment
import kotlinx.coroutines.flow.Flow

class RetrieveAppEntry(
    private val localUserManagment: LocalUserManagment
) {

   operator fun invoke(): Flow<Boolean>{
        return localUserManagment.retrieveAppEntry()
    }
}
