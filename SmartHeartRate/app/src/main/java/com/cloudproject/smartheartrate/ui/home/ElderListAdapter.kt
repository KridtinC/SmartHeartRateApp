package com.cloudproject.smartheartrate.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R
import kotlinx.android.synthetic.main.list_elders.view.*

class ElderListAdapter(private val items: Array<Array<String>>) :
    RecyclerView.Adapter<ElderListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(elder: Array<String>) {
            itemView.apply {
                elderFirstName.text = elder[1]
                elderLastName.text = elder[2]
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_elders, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}