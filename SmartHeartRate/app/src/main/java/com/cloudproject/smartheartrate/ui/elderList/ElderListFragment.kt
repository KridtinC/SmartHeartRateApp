package com.cloudproject.smartheartrate.ui.elderList

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.cloudproject.smartheartrate.R
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.dialog_add_elder.*
import kotlinx.android.synthetic.main.fragment_elder_list.*
import org.json.JSONObject


class ElderListFragment : Fragment() {

    private lateinit var elderListViewModel: ElderListViewModel
    private lateinit var elderListAdapter: ElderListAdapter
    private lateinit var elderData: ArrayList<Any>
    private lateinit var queue: RequestQueue
    private val hostname = "192.168.1.37"
    private val email = "aaa@gmail.com"

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
            onCreateDialog()
        }
//        val exampleData = addExampleData()
        queue = Volley.newRequestQueue(context)
        getElderData()
    }

    private fun onCreateDialog(): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.dialog_add_elder, null)
            builder.setView(view)
                .setPositiveButton(
                    "Add"
                ) { dialog, _ ->
                    val body: MutableMap<String, Any> = HashMap()
                    body["firstName"] = view.findViewById<EditText>(R.id.addElderFirstName).text
                    body["lastName"] = view.findViewById<EditText>(R.id.addElderLastName).text
                    body["age"] = view.findViewById<EditText>(R.id.addElderAge).text
                    body["lat"] = view.findViewById<EditText>(R.id.addElderLat).text
                    body["lng"] = view.findViewById<EditText>(R.id.addElderLng).text
                    body["email"] = email

                    val url = "http://$hostname:5000/api/elder-account/add-elder"
                    val parameters = JSONObject(body.toString())
                    val jsonObjectRequest = JsonObjectRequest(
                        Request.Method.POST, url, parameters,
                        Response.Listener { response ->
                            Log.d("ElderList", "Response: + $response")
                        },
                        Response.ErrorListener { error ->
                            Log.e("ElderList", error.toString())
                        }
                    )
                    queue.add(jsonObjectRequest)
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, _ ->
                    dialog.cancel()
                }
            builder.show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun addExampleData(): Array<Array<String>> {
        return arrayOf(
            arrayOf("1234", "Kridtin", "C", "22", "10.0", "20.0"),
            arrayOf("5678", "Josh", "W", "60", "15.0", "21.0")
        )
    }

    private fun getElderData() {
        val url = "http://$hostname:5000/api/elder-account/get-elderlist"
        val body: MutableMap<String?, String?> = HashMap()
        body["email"] = email

        val parameters = JSONObject(body as Map<*, *>)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, parameters,
            Response.Listener { response ->
                val results: ArrayList<LinkedTreeMap<String, Any>> = Gson().fromJson(
                    response["result"].toString(),
                    ArrayList::class.java
                ) as ArrayList<LinkedTreeMap<String, Any>>
                Log.d("ElderList", "Response: + $results")
                elderListAdapter =
                    ElderListAdapter(
                        results
                    )
                recyclerElders.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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