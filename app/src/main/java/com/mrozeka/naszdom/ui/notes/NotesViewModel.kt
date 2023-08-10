package com.mrozeka.naszdom.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrozeka.naszdom.pref.PrefRepository

class NotesViewModel(private val pref: PrefRepository) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun fillView(){
        _state.value = State.FillView(pref.getNotes() ?: emptyArray())
    }

    fun onAddNoteButtonClicked(){
        _state.value = State.ShowAddNoteDialog("Dodaj Notatkę", "Zapisz")
    }

    fun onSaveNote(note:String){
        val note = Note(note)
        pref.setNote(note)
        _state.value = State.RefreshView(note)
    }

    sealed class State{
        data class ShowAddNoteDialog(val title: String, val buttonTitle:String):State()
        data class FillView(val notes: Array<Note>):State()
        data class RefreshView(val note: Note):State()
    }
}