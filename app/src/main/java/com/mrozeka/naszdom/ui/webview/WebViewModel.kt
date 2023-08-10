package com.mrozeka.naszdom.ui.webview

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel(bundle: Bundle?) : ViewModel() {

    private val _url = MutableLiveData<String>().apply {
        if (bundle != null) {
            val link = bundle.getString("url", "https://www.google.com/")
            value = if(link.endsWith("pdf")){
                "https://drive.google.com/viewerng/viewer?embedded=true&url=$link"
            }else {
                link
            }
        }
    }
    val url: LiveData<String> = _url

}