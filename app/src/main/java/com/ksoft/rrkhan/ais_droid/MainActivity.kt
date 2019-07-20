package com.ksoft.rrkhan.ais_droid

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.ksoft.rrkhan.ais_droid.ui.login.LoginActivity

abstract class MainActivity : AppCompatActivity() {

    var instance:MainActivity=?
    abstract var locationRequest: LocationRequest
    abstract var fusedLocationProviderClient:FusedLocationProviderClient
    fun getInstance(): MainActivity{
        return instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance=this
        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(object :
            PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                updateLocation()
            }

            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {

            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {

            }
        }).check()

        var clickIntent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(clickIntent)
    }

    private fun updateLocation() {
        buildLocationRequest()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,getPendingIntent())
    }

    private fun buildLocationRequest() {
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setInterval(5000)
        locationRequest.setFastestInterval(3000)
        locationRequest.setSmallestDisplacement(10f)
    }
}
