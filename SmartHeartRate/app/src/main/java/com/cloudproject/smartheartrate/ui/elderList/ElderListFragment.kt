package com.cloudproject.smartheartrate.ui.elderList

import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.SharedViewModel
import com.cloudproject.smartheartrate.ui.repository.ElderRepository
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.fragment_elder_list.*
import kotlinx.android.synthetic.main.fragment_elder_list.view.*
import org.json.JSONObject


class ElderListFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()
    private lateinit var elderList: ArrayList<Elder>
    private lateinit var elderListAdapter: ElderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_elder_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getElderList().observe(viewLifecycleOwner, Observer { item ->
            elderList = item
            if (::elderList.isInitialized) {
                if (elderList.size > 0) {
                    Log.d("ElderListFragment", elderList.toString())
                        elderListAdapter = ElderListAdapter(elderList)
                    recyclerElders.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        adapter = elderListAdapter
                    }
                    progressBarElderList.visibility = View.INVISIBLE
                }
            }
        })
        model.isFetchElderListSuccess().observe(viewLifecycleOwner, Observer { item ->
            if (item == false){
                Toast.makeText(context, "Cannot fetch data from server. Please check your internet connection.", Toast.LENGTH_SHORT).show()
            }
        })


        buttonAddElder.setOnClickListener {
//            onCreateDialog()
        }
        swipeContainer.setOnRefreshListener {
//            LongOperation(context!!, view).execute()
            swipeContainer.isRefreshing = false
        }
    }

//    private fun onCreateDialog(): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            val inflater = requireActivity().layoutInflater;
//            val view = inflater.inflate(R.layout.dialog_add_elder, null)
//            builder.setView(view)
//                .setPositiveButton(
//                    "Add"
//                ) { dialog, _ ->
//                    val body: MutableMap<String, Any> = HashMap()
//                    body["firstName"] = view.findViewById<EditText>(R.id.addElderFirstName).text
//                    body["lastName"] = view.findViewById<EditText>(R.id.addElderLastName).text
//                    body["age"] = view.findViewById<EditText>(R.id.addElderAge).text
//                    body["lat"] = view.findViewById<EditText>(R.id.addElderLat).text
//                    body["lng"] = view.findViewById<EditText>(R.id.addElderLng).text
//                    body["email"] = email
//
//                    val url = "http://$hostname:5000/api/elder-account/add-elder"
//                    val parameters = JSONObject(body.toString())
//                    val jsonObjectRequest = JsonObjectRequest(
//                        Request.Method.POST, url, parameters,
//                        Response.Listener { response ->
//                            Log.d("ElderList", "Response: + $response")
//                        },
//                        Response.ErrorListener { error ->
//                            Log.e("ElderList", error.toString())
//                        }
//                    )
//                    queue.add(jsonObjectRequest)
//                }
//                .setNegativeButton(
//                    "Cancel"
//                ) { dialog, _ ->
//                    dialog.cancel()
//                }
//            builder.show()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
}

