package com.mrozeka.naszdom.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrozeka.naszdom.pref.PrefRepository

class ContactsViewModel(private val pref: PrefRepository): ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun fillView(){
        _state.value = State.FillView(pref.getContacts() ?: emptyArray())
    }

    fun onAddContactButtonClicked(){
        _state.value = State.ShowAddContactDialog("Dodaj kontakt", "Zapisz")
    }

    fun onSaveContact(name:String, data:String){
        val contact = Contact(name, data)
        pref.setContact(contact)
        _state.value = State.RefreshView(contact)
    }

    fun onContactClicked(isPermissionGranted:Boolean, contact: Contact){
        if(isPermissionGranted){
            if(android.util.Patterns.PHONE.matcher(contact.data).matches()){
                _state.value = State.MakeACall("tel:" + contact.data)
            }
        }else{
            _state.value = State.OnError("Aplikacja nie ma uprawnień do wykonywania połączeń")
        }

    }

    sealed class State{
        data class ShowAddContactDialog(val title: String, val buttonTitle:String):State()
        data class FillView(val contacts: Array<Contact>):State()
        data class MakeACall(val tel:String):State()
        data class OnError(val msg:String):State()
        data class RefreshView(val contact: Contact):State()
    }
}