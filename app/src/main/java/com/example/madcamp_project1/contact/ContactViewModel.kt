package com.example.madcamp_project1.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel: ViewModel() {

    private var inputData: MutableLiveData<ContactData> = MutableLiveData()

    fun getData(): MutableLiveData<ContactData> = inputData

    fun updateData(input: ContactData) {
        inputData.value = input
    }
}