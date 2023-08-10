package com.mrozeka.naszdom.ui.webview

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WebViewModelFactory(private val bundle:Bundle?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WebViewModel(bundle) as T
    }
}
