package com.mrozeka.naszdom.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrozeka.naszdom.R
import com.mrozeka.naszdom.firebase.models.Project


class HomeChildAdapter(var dataSet: ArrayList<Project>, private val onClick: (Project) -> Unit) :
    RecyclerView.Adapter<HomeChildAdapter.HomeChildViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class HomeChildViewHolder(view: View, val onClick: (Project) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val txt: TextView
        private val price: TextView
        private val image: ImageView
        private var currentProject: Project? = null

        init {
            txt = view.findViewById(R.id.home_child_item_txt)
            price = view.findViewById(R.id.home_child_item_price)
            image = view.findViewById(R.id.home_child_item_img)

            view.setOnClickListener {
                currentProject?.let {
                    onClick(it)
                }
            }
        }

        fun bind(project: Project) {
            currentProject = project
            txt.text = project.title
            price.text = project.price
            if (project.imgId.isNotEmpty()) {
                //TMP
                image.setImageResource(R.drawable.klasycznyxl)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeChildViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listview_child_home_item, viewGroup, false)
        return HomeChildViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: HomeChildViewHolder, position: Int) {
        val project = dataSet[position]
        viewHolder.bind(project)
    }

    override fun getItemCount() = dataSet.size

}
