package com.mrozeka.naszdom.helper

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.mrozeka.naszdom.R

object DialogHelper {
    fun withError(context: Context, layoutInflater: LayoutInflater, msg: String,onClick: ()-> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        val dialogLayout = layoutInflater.inflate(R.layout.alert_dialog, null)
        val tv = dialogLayout.findViewById<TextView>(R.id.textView)
        tv.text = msg
        builder.setView(dialogLayout)
        builder.setCancelable(false)
        builder.setPositiveButton("Ok") { _, _ ->
            onClick()
        }
        builder.show()
    }

    fun with2EditText(
        context: Context,
        layoutInflater: LayoutInflater,
        title: String,
        buttonTitle: String,
        onClick: (et1: String, et2: String) -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        val dialogLayout = layoutInflater.inflate(R.layout.alert_dialog_2edtitext, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        val editText2 = dialogLayout.findViewById<EditText>(R.id.editText2)
        builder.setView(dialogLayout)
        builder.setPositiveButton(buttonTitle) { _, _ ->
            onClick(editText.text.toString(), editText2.text.toString())
        }
        builder.show()
    }

    fun withForceEditText(
        context: Context,
        layoutInflater: LayoutInflater,
        title: String,
        buttonTitle: String,
        onClick: (txt: String) -> Unit
    ) {
        val builder = withEditTextBuilder(context, layoutInflater, title, buttonTitle, onClick)
        builder.setCancelable(false)
        builder.show()
    }

    fun withEditText(
        context: Context,
        layoutInflater: LayoutInflater,
        title: String,
        buttonTitle: String,
        onClick: (txt: String) -> Unit
    ) {
        val builder = withEditTextBuilder(context, layoutInflater, title, buttonTitle, onClick)
        builder.setCancelable(true)
        builder.show()
    }

    private fun withEditTextBuilder(
        context: Context,
        layoutInflater: LayoutInflater,
        title: String,
        buttonTitle: String,
        onClick: (txt: String) -> Unit
    ): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        val dialogLayout = layoutInflater.inflate(R.layout.alert_dialog_edtitext, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setCancelable(false)
        builder.setView(dialogLayout)
        builder.setPositiveButton(buttonTitle) { _, _ ->
            onClick(editText.text.toString())
        }
        return builder
    }

}