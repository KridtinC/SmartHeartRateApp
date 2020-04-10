package com.cloudproject.smartheartrate.ui.elderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.model.Elder
import kotlinx.android.synthetic.main.list_elders.view.*

class ElderListAdapter(private val items: ArrayList<Elder>) :
    RecyclerView.Adapter<ElderListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(elder: Elder) {
            itemView.apply {
                elderFirstName.text = elder.firstName
                elderLastName.text = elder.lastName
                elderAge.text = elder.age.toString()
                elderDeviceID.text = elder.deviceID.toString()
                elderLat.text = elder.lat.toString()
                elderLng.text = elder.lng.toString()
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