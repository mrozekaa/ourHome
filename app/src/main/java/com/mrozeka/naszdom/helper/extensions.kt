package com.mrozeka.naszdom.helper

fun Array<*>.safeLastIndex():Int{
    return if(isEmpty()){
        0
    }else{
        size - 1
    }
}