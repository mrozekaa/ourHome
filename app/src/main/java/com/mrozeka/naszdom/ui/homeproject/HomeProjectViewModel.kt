package com.mrozeka.naszdom.ui.homeproject

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeProjectViewModel(bundle: Bundle?) : ViewModel() {
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

    private val _tags = MutableLiveData<Array<String>>().apply {
        if (bundle != null) {
            val tags = bundle.getString("tags", "-").split(";").toTypedArray()
            value = tags
        }
    }
    val tags: LiveData<Array<String>> = _tags
}