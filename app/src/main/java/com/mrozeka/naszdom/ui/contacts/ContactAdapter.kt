package com.mrozeka.naszdom.ui.contacts

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrozeka.naszdom.R

class ContactAdapter(var dataSet: Array<Contact>,private val onClick: (Contact) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ContactViewHolder(view: View,val onClick: (Contact) -> Unit) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val data: TextView
        val sign: TextView
        private var currentContact:Contact? = null

        init {
            name = view.findViewById(R.id.contact_item_name)
            data = view.findViewById(R.id.contact_item_data)
            sign = view.findViewById(R.id.contact_item_sign)

            view.setOnClickListener {
                currentContact?.let {
                    onClick(it)
                }
            }
        }

        fun bind(contact: Contact){
            currentContact = contact
            name.text = contact.name
            data.text = contact.data
            sign.text = contact.name.first().toString().uppercase()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listview_contact_item, viewGroup, false)
        return ContactViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ContactViewHolder, position: Int) {
        val contact = dataSet[position]
        viewHolder.bind(contact)
    }

    override fun getItemCount() = dataSet.size

}
