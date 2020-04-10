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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.ui.SharedViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class HomeFragment : Fragment(), OnMapReadyCallback {

    private var MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1
    private var mLocation: Location? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mGoogleMap: GoogleMap

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
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
                    if (location == null) {
                        Toast.makeText(
                            context!!,
                            "Please turn on your GPS.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else {
                        mLocation = location
                        Toast.makeText(
                            context!!,
                            mLocation!!.latitude.toString() + "," + mLocation!!.longitude.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    val mapView: MapView = view!!.findViewById<View>(R.id.map) as MapView
                    mapView.onCreate(null)
                    mapView.onResume()
                    mapView.getMapAsync(this)
                }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
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
        mGoogleMap = googleMap
        mGoogleMap.isMyLocationEnabled = true;
        if (mLocation != null) {
            mGoogleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        mLocation!!.latitude,
                        mLocation!!.longitude
                    ),
                    15F
                )
            )
        }
        mGoogleMap.clear()
        model.getElderList(false).observe(viewLifecycleOwner, Observer { item ->
            if (item != null){
                for (i in item){
                    val markerOptions = MarkerOptions()
                    val latLng = LatLng(i.lat, i.lng)
                    markerOptions.position(latLng)
                    markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude.toString())
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    mGoogleMap.addMarker(markerOptions)
                }
            }
        })
    }

}
