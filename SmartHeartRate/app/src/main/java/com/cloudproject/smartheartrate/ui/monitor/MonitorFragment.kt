package com.cloudproject.smartheartrate.ui.monitor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.SharedViewModel
import kotlinx.android.synthetic.main.fragment_monitor.*

class MonitorFragment : Fragment() {
    private lateinit var monitorViewModel: MonitorViewModel
    private var elderList:SharedViewModel = SharedViewModel()
    private lateinit var viewAdapter : RecyclerAdapter
    private var token : MonitorSendMessage = MonitorSendMessage()

    private var elderInfo: ArrayList<Elder> = ArrayList() // from getElderResponse
    private  var elderRate : ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        monitorViewModel = ViewModelProviders.of(this).get(MonitorViewModel::class.java)
        return inflater.inflate(R.layout.fragment_monitor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val rate = monitorViewModel.rate.observe(viewLifecycleOwner)
//        viewAdapter.reset()
        getElderList(true)
        getElderRate()
        getToken()
//        monitor_swipeRefresh.setOnRefreshListener {
//            viewAdapter.reset()
//            getElderList(true)
//            getElderRate()
//            createAdapter()
//        }
    }

    override fun onPause() {
        super.onPause()
        monitorViewModel.stopTimer()
    }

    private fun getToken() {
        token.getToken()
    }
    private fun createAdapter() {
//        if(elderRate.size==0) return

        viewAdapter = RecyclerAdapter(elderInfo,elderRate)
        val ori = resources.configuration.orientation
        monitor_recyclerView.apply{
            layoutManager = LinearLayoutManager(context,ori,false)
            adapter = viewAdapter
            onFlingListener = null
        }

    }
    private fun getElderList(isRefresh:Boolean) {
        elderList.getElderList(isRefresh).observe(viewLifecycleOwner,Observer{ item->
            if(item==null){
                Toast.makeText(context, "Cannot fetch data from server. Please check your internet connection.", Toast.LENGTH_SHORT).show()
            }else {
//                if(item.size==0) {
//                    monitor_recyclerView.visibility = View.GONE
//                    noElderDesc.visibility = View.VISIBLE
//                }else {
//                    monitor_recyclerView.visibility = View.VISIBLE
//                    noElderDesc.visibility = View.GONE
//                }
                monitorViewModel.setElderNumb(item.size)
                elderInfo = item


            }

        })

    }

    private fun getElderRate() {
        monitorViewModel.getElderRate().observe(viewLifecycleOwner, Observer { item ->
            if(item!=null) {
                this.elderRate = item
                createAdapter()
            }
        })

    }

}