package com.mrozeka.naszdom.ui.notes

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrozeka.naszdom.R

class NoteAdapter(var dataSet: Array<Note>, private val onClick: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class NoteViewHolder(view: View,val onClick: (Note) -> Unit) : RecyclerView.ViewHolder(view) {
        private val txt: TextView
        private var currentNote:Note? = null

        init {
            txt = view.findViewById(R.id.note_item_txt)

            view.setOnClickListener {
                currentNote?.let {
                    onClick(it)
                }
            }
        }

        fun bind(note: Note){
            currentNote = note
            txt.text = note.txt
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listview_note_item, viewGroup, false)
        return NoteViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        val note = dataSet[position]
        viewHolder.bind(note)
    }

    override fun getItemCount() = dataSet.size

}
