package com.mrozeka.naszdom.ui.notes

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrozeka.naszdom.pref.PrefRepository

class NotesViewModelFactory(private val pref:PrefRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(pref) as T
    }
}
