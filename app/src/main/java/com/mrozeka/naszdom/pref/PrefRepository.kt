package com.mrozeka.naszdom.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mrozeka.naszdom.ui.contacts.Contact
import com.mrozeka.naszdom.ui.notes.Note

class PrefRepository(context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("PREFERENCES_NAME", Context.MODE_PRIVATE)

    private val editor = pref.edit()

    private val gson = Gson()

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.apply()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.apply()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getLong() = pref.getLong(this, 0)

    private fun String.getInt() = pref.getInt(this, 0)

    private fun String.getString() = pref.getString(this, "")!!

    private fun String.getBoolean() = pref.getBoolean(this, false)

    fun setContact(contact: Contact) {
        var contacts = getContacts()
        if(contacts.isNullOrEmpty()){
            contacts = emptyArray()
        }
        val contactsM = contacts.toMutableList()
        contactsM.add(contact)
        PREF_CONTACTS.put(gson.toJson(contactsM))
    }

    fun getContacts(): Array<Contact>? = Gson().fromJson(PREF_CONTACTS.getString(), Array<Contact>::class.java)

    fun setNote(note: Note) {
        var notes = getNotes()
        if(notes.isNullOrEmpty()){
            notes = emptyArray()
        }
        val contactsM = notes.toMutableList()
        contactsM.add(note)
        PREF_NOTES.put(gson.toJson(contactsM))
    }

    fun getNotes(): Array<Note>? = Gson().fromJson(PREF_NOTES.getString(), Array<Note>::class.java)

    fun getHomeId():String = PREF_HOME_ID.getString()
    fun setHomeId(id: String){
        PREF_HOME_ID.put(id)
    }

    fun clearData() {
        editor.clear()
        editor.commit()
    }
}