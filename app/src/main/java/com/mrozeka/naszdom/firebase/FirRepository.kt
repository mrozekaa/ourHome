package com.mrozeka.naszdom.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirRepository {
    private val db = Firebase.firestore

    fun getHouseById(
        id: String,
        onSuccess: (house: House?) -> Unit,
        onError: (exception: Exception) -> Unit
    ) {
        db.collection(FIR_HOUSES)
            .document(id)
            .get()
            .addOnSuccessListener { result ->
                if (result.exists()) {
                    onSuccess(result.toObject(House::class.java))
                }else{
                    onError(java.lang.Exception("Dom nie istnieje"))
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}