package com.cloudproject.smartheartrate.ui.monitor

import android.media.Image
import android.os.Bundle
import android.os.Debug
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.R
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_monitor.*
import kotlinx.android.synthetic.main.monitor_list.*

class MonitorFragment : Fragment() {
    private lateinit var monitorViewModel: MonitorViewModel
//    private lateinit var recyclerView :RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        monitorViewModel = ViewModelProviders.of(this).get(MonitorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_monitor, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewAdapter = RecyclerAdapter(monitorViewModel.getElderName(),monitorViewModel.getElderRate())
        val ori = resources.configuration.orientation
        monitor_recyclerView.apply{
            layoutManager = LinearLayoutManager(context,ori,false)
            adapter = viewAdapter
            onFlingListener = null
        }
        viewAdapter.notifyDataSetChanged()
    }


}