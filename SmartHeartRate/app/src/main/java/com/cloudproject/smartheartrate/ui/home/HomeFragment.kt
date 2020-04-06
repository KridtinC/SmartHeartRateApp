package com.cloudproject.smartheartrate.ui.home

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng


class HomeFragment : Fragment(), OnMapReadyCallback {

    private var MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1
    private var mLocation: Location? = null
    private lateinit var homeViewModel: HomeViewModel
    private var currentLocation: Location? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //            textView.text = it
        })

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        getLatestLocation()
        return root
    }

    private fun getLatestLocation() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_ACCESS_LOCATION
            )
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    mLocation = location
                    Toast.makeText(
                        context!!,
                        mLocation!!.latitude.toString() + "," + mLocation!!.longitude.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    val mapView: MapView = view!!.findViewById<View>(R.id.map) as MapView
                    mapView.onCreate(null)
                    mapView.onResume()
                    mapView.getMapAsync(this)
                }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLatestLocation()
                } else {
                    AlertDialog.Builder(context!!)
                        .setTitle("Permission denied")
                        .setMessage("Please enable location permission to use this feature.")
                        .setNeutralButton("OK") { _: DialogInterface, _: Int -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
                return
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.isMyLocationEnabled = true;
        if (mLocation != null) {
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        mLocation!!.latitude,
                        mLocation!!.longitude
                    ),
                    15F
                )
            )
        }
    }

}
