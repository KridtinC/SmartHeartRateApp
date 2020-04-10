package com.cloudproject.smartheartrate.ui.elderList

import android.app.Dialog
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
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.SharedViewModel
import kotlinx.android.synthetic.main.fragment_elder_list.*


class ElderListFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()
    private lateinit var elderListAdapter: ElderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_elder_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getElderList(false)

        buttonAddElder.setOnClickListener {
            onCreateDialog()
        }
        swipeContainer.setOnRefreshListener {
            progressBarElderList.visibility = View.VISIBLE
            elderListAdapter.clear()
            getElderList(true)
            swipeContainer.isRefreshing = false
        }
    }

    private fun getElderList(isRefresh: Boolean) {
        model.getElderList(isRefresh).observe(viewLifecycleOwner, Observer { item ->
            if (item == null) {
                elderListAdapter = ElderListAdapter(addExampleData())
                Toast.makeText(context, "Cannot fetch data from server. Please check your internet connection.", Toast.LENGTH_SHORT).show()
            }
            else{
                if (item.size == 0){
                    recyclerElders.visibility = View.GONE
                    noElderDesc.visibility = View.VISIBLE
                }
                else{
                    recyclerElders.visibility = View.VISIBLE
                    noElderDesc.visibility = View.GONE
                }
                Log.d("ElderListFragment", item.toString())
                elderListAdapter = ElderListAdapter(item)
            }
            recyclerElders.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = elderListAdapter
            }
            progressBarElderList.visibility = View.INVISIBLE
        })
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
                    val firstName = view.findViewById<EditText>(R.id.addElderFirstName).text.toString()
                    val lastName = view.findViewById<EditText>(R.id.addElderLastName).text.toString()
                    val age = view.findViewById<EditText>(R.id.addElderAge).text.toString().toInt()
                    val lat = view.findViewById<EditText>(R.id.addElderLat).text.toString().toDouble()
                    val lng = view.findViewById<EditText>(R.id.addElderLng).text.toString().toDouble()
                    val elder = Elder(-1, firstName, lastName, age, lat , lng)
                    model.addElder(elder).observe(viewLifecycleOwner, Observer { item ->
                        if (item != null){
                            if (item == true)
                                Toast.makeText(context, "Add successful", Toast.LENGTH_SHORT).show()
                            else
                                Toast.makeText(context, "Add fail", Toast.LENGTH_SHORT).show()
                        }
                    })
                    getElderList(true)
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, _ ->
                    dialog.cancel()
                }
            builder.show()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun addExampleData(): ArrayList<Elder> {
        val data: ArrayList<Elder> = ArrayList()
        data.add(Elder(156487, "Adam", "Smith", 22, 13.145678, 120.147896))
        return data
    }
}

