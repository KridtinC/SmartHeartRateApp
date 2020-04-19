package com.cloudproject.smartheartrate.ui.monitor


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.model.Elder


class RecyclerAdapter(private val elderList: ArrayList<Elder>, private val elderRate: ArrayList<String>)
    : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()  {
    class MyViewHolder(private var textview : View):RecyclerView.ViewHolder(textview) , View.OnClickListener{
        fun bindElder(eldernamed:Elder,elderrated:String){
            textview.findViewById<TextView>(R.id.monitor_name).text = eldernamed.firstName+" "+eldernamed.lastName
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

    override fun getItemCount() = elderRate.size

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        if(elderRate.size!=elderList.size) return
        holder.bindElder(elderList[position],elderRate[position])
    }
    fun reset() {
        elderList.clear()
        elderRate.clear()
        notifyDataSetChanged()
    }

}
