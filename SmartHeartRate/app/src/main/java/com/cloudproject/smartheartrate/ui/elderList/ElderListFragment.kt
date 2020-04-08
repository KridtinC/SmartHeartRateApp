package com.cloudproject.smartheartrate.ui.elderList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.cloudproject.smartheartrate.R
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.fragment_elder_list.*
import org.json.JSONObject


class ElderListFragment : Fragment() {

    private lateinit var elderListViewModel: ElderListViewModel
    private lateinit var elderListAdapter: ElderListAdapter
    private lateinit var elderData: ArrayList<Any>

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
        getElderData()
    }

    private fun addExampleData(): Array<Array<String>> {
        return arrayOf(
            arrayOf("1234", "Kridtin", "C", "22", "10.0", "20.0"),
            arrayOf("5678", "Josh", "W", "60", "15.0", "21.0")
        )
    }

    private fun getElderData() {
        val queue = Volley.newRequestQueue(context)
        val url = "http://192.168.1.37:5000/api/elder-account/get-elderlist"
        val params: MutableMap<String?, String?> = HashMap()
        params["email"] = "aaa@gmail.com"

        val parameters = JSONObject(params as Map<*, *>)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, parameters,
            Response.Listener { response ->
                val results: ArrayList<LinkedTreeMap<String, Any>> = Gson().fromJson(
                    response["result"].toString(),
                    ArrayList::class.java
                ) as ArrayList<LinkedTreeMap<String, Any>>
                Log.d("ElderList", "Response: + ${results}")
                elderListAdapter =
                    ElderListAdapter(
                        results
                    )
                recyclerElders.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = elderListAdapter
                }
            },
            Response.ErrorListener { error ->
                Log.e("ElderList", error.toString())
            }
        )
        queue.add(jsonObjectRequest)
    }
}