package com.cloudproject.smartheartrate.ui.elderList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.ui.home.ElderListAdapter
import kotlinx.android.synthetic.main.fragment_elder_list.*

class ElderListFragment : Fragment() {

    private lateinit var elderListViewModel: ElderListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        elderListViewModel =
            ViewModelProviders.of(this).get(ElderListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_elder_list, container, false)
//        val textView: TextView = root.findViewById(R.id.text_elder_list)
//        elderListViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAddElder.setOnClickListener {
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
        }
        val exampleData = addExampleData()
        val elderListAdapter = ElderListAdapter(exampleData)
        recyclerElders.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = elderListAdapter
        }
    }

    private fun addExampleData(): Array<Array<String>> {
        return arrayOf(arrayOf("1234", "Kridtin", "C", "22", "10.0", "20.0"), arrayOf("5678", "Josh", "W", "60", "15.0", "21.0"))
    }

    private fun getElderData(){
        StringRequest
    }
}