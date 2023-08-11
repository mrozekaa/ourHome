package com.mrozeka.naszdom.firebase

import com.google.firebase.firestore.PropertyName

data class House(
    @PropertyName("Title") var title:String = "",
)