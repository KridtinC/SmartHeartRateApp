package com.cloudproject.smartheartrate.ui.monitor


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.R


class RecyclerAdapter(private val eldername_list: ArrayList<String>, private val elderrate_list: ArrayList<Int>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()  {
    class MyViewHolder(v:View):RecyclerView.ViewHolder(v) , View.OnClickListener{

        private lateinit var eldername:String
        private var heartrate:Int? = null
        init {
            v.setOnClickListener(this)
        }
        public fun bindElder(eldernamed:String,elderrated:Int){
            this.eldername = eldernamed
            this.heartrate = elderrated

        }
        override fun onClick(v: View?) {
            Log.d("RecycleView","Click!!")
        }

    }
    private lateinit var monitorViewModel: MonitorViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textview = LayoutInflater.from(parent.context).inflate(R.layout.fragment_monitor,parent,false)
        return MyViewHolder(textview)
    }

    override fun getItemCount() = monitorViewModel.getElderNumber()

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        holder.bindElder(this.eldername_list[position],this.elderrate_list[position])
    }


}
