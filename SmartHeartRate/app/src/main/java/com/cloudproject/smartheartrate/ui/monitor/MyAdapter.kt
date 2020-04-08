package com.cloudproject.smartheartrate.ui.monitor


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R
import kotlin.properties.Delegates


class RecyclerAdapter(private val eldername_list: ArrayList<String>, private val elderrate_list: ArrayList<String>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()  {
    class MyViewHolder(private var textview : View):RecyclerView.ViewHolder(textview) , View.OnClickListener{

        fun bindElder(eldernamed:String,elderrated:String){
            textview.findViewById<TextView>(R.id.monitor_name).text = eldernamed
            textview.findViewById<TextView>(R.id.monitor_rate).text = elderrated

        }
        override fun onClick(v: View?) {
            Log.d("RecycleView","Click!!")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textview = LayoutInflater.from(parent.context).inflate(R.layout.monitor_list,parent,false)
        return MyViewHolder(textview)
    }

    override fun getItemCount() = eldername_list.size

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        holder.bindElder(this.eldername_list[position],this.elderrate_list[position])
    }


}
