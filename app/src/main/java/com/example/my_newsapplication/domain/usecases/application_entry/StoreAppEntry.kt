package com.example.my_newsapplication.domain.usecases.application_entry

import com.example.my_newsapplication.domain.management.LocalUserManagment

class StoreAppEntry(
    private val localUserManagment: LocalUserManagment
) {

    suspend operator fun invoke(){
        localUserManagment.storeAppEntry()
    }
}