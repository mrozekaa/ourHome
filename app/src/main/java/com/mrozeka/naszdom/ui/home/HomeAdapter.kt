package com.mrozeka.naszdom.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrozeka.naszdom.R
import com.mrozeka.naszdom.firebase.models.Cost
import com.mrozeka.naszdom.firebase.models.Project


class HomeAdapter(var dataSet: ArrayList<Cost>, private val onClick: (Project) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class HomeViewHolder(view: View, val onClick: (Project) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val txt: TextView
        private val childRecyclerView: RecyclerView
        private var currentCost: Cost? = null

        init {
            txt = view.findViewById(R.id.home_item_txt)
            childRecyclerView = view.findViewById(R.id.child_home_recyclerview)
        }

        fun bind(parentViewHolder: HomeViewHolder, cost: Cost) {
            currentCost = cost
            txt.text = cost.title
            val arrayAdapter = HomeChildAdapter(cost.projects) {
                onClick(it)
            }
            val layoutManager = LinearLayoutManager(
                parentViewHolder.childRecyclerView
                    .context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            layoutManager.initialPrefetchItemCount = cost.projects.size
            childRecyclerView.layoutManager = layoutManager
            childRecyclerView.adapter = arrayAdapter
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listview_home_item, viewGroup, false)
        return HomeViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {
        val cost = dataSet[position]
        viewHolder.bind(viewHolder, cost)
    }

    override fun getItemCount() = dataSet.size

}
