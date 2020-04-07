package com.cloudproject.smartheartrate.ui.monitor


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R
import kotlin.properties.Delegates


class RecyclerAdapter(private val eldername_list: ArrayList<String>, private val elderrate_list: ArrayList<String>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()  {
    class MyViewHolder(textview : View):RecyclerView.ViewHolder(textview) , View.OnClickListener{

        private lateinit var eldername:String
        private lateinit var heartrate:String

        init {
            eldername = textview.findViewById<View>(R.id.monitor_name).toString()
            heartrate = textview.findViewById<View>(R.id.monitor_rate).toString()
        }
        public fun bindElder(eldernamed:String,elderrated:String){
            this.eldername = eldernamed
            this.heartrate = elderrated

        }
        override fun onClick(v: View?) {
            Log.d("RecycleView","Click!!")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textview = LayoutInflater.from(parent.context).inflate(R.layout.fragment_monitor,parent,false)
        return MyViewHolder(textview)
    }

    override fun getItemCount() = eldername_list.size

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        holder.bindElder(this.eldername_list[position],this.elderrate_list[position])
    }


}
