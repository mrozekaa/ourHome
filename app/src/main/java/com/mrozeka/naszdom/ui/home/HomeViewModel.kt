package com.mrozeka.naszdom.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrozeka.naszdom.firebase.FirRepository
import com.mrozeka.naszdom.firebase.House
import com.mrozeka.naszdom.pref.PrefRepository

class HomeViewModel(private val pref: PrefRepository, private val fir: FirRepository) :
    ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun fillView() {
        val homeId = pref.getHomeId()
        if (pref.getHomeId().isEmpty()) {
            _state.value = State.ShowHomeIdDialog(
                "Podaj unikalne id twojego domu :)(12345 dla testowego)",
                "OK"
            )
        } else {
            fir.getHouseById(homeId, { house ->
                if (house != null) {
                    _state.value = State.FillView(house)
                } else {
                    _state.value = State.OnError("Dom nie znaleziony")
                }
            }, {
                _state.value = State.OnError("Firebase error")
            })
        }
    }

    fun checkAndSaveHomeId(id: String) {
        fir.getHouseById(id, { house ->
            if (house != null) {
                pref.setHomeId(id)
                _state.value = State.FillView(house)
            } else {
                _state.value = State.OnError("Dom nie znaleziony")
            }
        }, {
            _state.value = State.OnError("Firebase error")
        })
    }

    fun onErrorClosed(){
        _state.value = State.ShowHomeIdDialog(
            "Podaj unikalne id twojego domu :)(12345 dla testowego)",
            "OK"
        )
    }
    sealed class State {
        data class ShowHomeIdDialog(val title: String, val buttonTitle: String) : State()
        data class FillView(val house: House) : State()
        data class RefreshView(val s: String) : State()
        data class OnError(val msg: String) : State()
    }
}