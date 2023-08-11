package com.mrozeka.naszdom.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrozeka.naszdom.firebase.FirRepository
import com.mrozeka.naszdom.pref.PrefRepository

class HomeViewModelFactory(private val pref:PrefRepository, private val fir:FirRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(pref, fir) as T
    }
}
