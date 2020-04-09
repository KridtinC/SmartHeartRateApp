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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.cloudproject.smartheartrate.R
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.fragment_elder_list.*
import kotlinx.android.synthetic.main.fragment_elder_list.view.*
import org.json.JSONObject

private const val hostname = "192.168.1.37"
private const val email = "aaa@gmail.com"
private lateinit var queue: RequestQueue

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
        queue = Volley.newRequestQueue(context)
        LongOperation(context!!, root).execute()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        buttonAddElder.setOnClickListener {
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
            onCreateDialog()
        }
        swipeContainer.setOnRefreshListener {
            LongOperation(context!!, view).execute()
            swipeContainer.isRefreshing = false
        }
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
}

private class LongOperation(context: Context, view: View) :
    AsyncTask<String?, Void?, String?>() {

    private var mContext: Context = context
    private var mView: View = view
    private lateinit var elderListAdapter: ElderListAdapter

    override fun doInBackground(vararg params: String?): String {
        getElderData()
        return "Executed"
    }

    override fun onPostExecute(result: String?) {

    }

    override fun onPreExecute() {
        mView.progressBarElderList.visibility = View.VISIBLE
    }

    override fun onProgressUpdate(vararg values: Void?) {}

    private fun getElderData() {
        val url = "http://$hostname:5000/api/elder-account/get-elderlist"
        val body: MutableMap<String?, String?> = HashMap()
        body["email"] = email

        val parameters = JSONObject(body as Map<*, *>)

        if(::elderListAdapter.isInitialized){
            Log.d("ElderList", "Remove items")
            elderListAdapter.clear()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, parameters,
            Response.Listener { response ->
                val results: ArrayList<LinkedTreeMap<String, Any>> = Gson().fromJson(
                    response["result"].toString(),
                    ArrayList::class.java
                ) as ArrayList<LinkedTreeMap<String, Any>>
                Log.d("ElderList", "Response: + $results")
                elderListAdapter = ElderListAdapter(results)
                mView.recyclerElders.apply {
                    layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
                    adapter = elderListAdapter
                }
                mView.progressBarElderList.visibility = View.INVISIBLE
            },
            Response.ErrorListener { error ->
                Log.e("ElderList", error.toString())
                mView.progressBarElderList.visibility = View.INVISIBLE
                Toast.makeText(mContext, "Cannot fetch data from server. Please check your internet connection", Toast.LENGTH_SHORT).show()
                elderListAdapter = ElderListAdapter(addExampleData())
                mView.recyclerElders.apply {
                    layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
                    adapter = elderListAdapter
                }

            }
        )
        queue.add(jsonObjectRequest)
    }

    private fun addExampleData(): ArrayList<LinkedTreeMap<String, Any>> {
        val data: ArrayList<LinkedTreeMap<String, Any>> = ArrayList()
        val elder: LinkedTreeMap<String, Any> = LinkedTreeMap()
        elder["firstName"] = "Adam"
        elder["lastName"] = "Smith"
        elder["age"] = 23.0
        elder["deviceID"] = 1345695.0
        elder["lat"] = 13.025648
        elder["lng"] = 102.154896
        data.add(elder)
        return data
    }
}