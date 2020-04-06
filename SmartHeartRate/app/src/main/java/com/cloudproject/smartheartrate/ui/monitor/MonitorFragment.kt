package com.cloudproject.smartheartrate.ui.monitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.R

class MonitorFragment : Fragment() {

    private lateinit var monitorViewModel: MonitorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monitorViewModel =
            ViewModelProviders.of(this).get(MonitorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_monitor, container, false)
        val textView: TextView = root.findViewById(R.id.text_monitor)
        monitorViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}