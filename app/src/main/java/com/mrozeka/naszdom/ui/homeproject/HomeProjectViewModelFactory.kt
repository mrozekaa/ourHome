package com.mrozeka.naszdom.ui.homeproject

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeProjectViewModelFactory(private val bundle:Bundle?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeProjectViewModel(bundle) as T
    }
}
