package com.mrozeka.naszdom.ui.contacts

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrozeka.naszdom.pref.PrefRepository

class ContactsViewModelFactory(private val pref:PrefRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(pref) as T
    }
}
