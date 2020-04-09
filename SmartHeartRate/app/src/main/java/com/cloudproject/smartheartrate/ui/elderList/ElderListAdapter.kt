package com.cloudproject.smartheartrate.ui.elderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.list_elders.view.*

class ElderListAdapter(private val items: ArrayList<LinkedTreeMap<String, Any>>) :
    RecyclerView.Adapter<ElderListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(elder: LinkedTreeMap<String, Any>) {
            itemView.apply {
                elderFirstName.text = elder["firstName"] as CharSequence?
                elderLastName.text = elder["lastName"] as CharSequence?
                elderAge.text = (elder["age"] as Double?)?.toInt().toString()
                elderDeviceID.text = (elder["deviceID"] as Double?)?.toInt().toString()
                elderLat.text = (elder["lat"] as Double?).toString()
                elderLng.text = (elder["lng"] as Double?).toString()
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

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }
}