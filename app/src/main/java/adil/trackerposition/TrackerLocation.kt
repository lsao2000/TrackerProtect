package adil.trackerposition

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class TrackerLocation(val context: Context)  {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private  val  requestCode = 2
    fun getLocation(latitude:TextView, longitude:TextView){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        // this while is for checking if the permission granted and the last location is available.
        while (checkPermissions(latitude, longitude) == null){
            checkPermissions(latitude, longitude)
        }
    }
    private fun checkPermissions(latitude: TextView, longitude: TextView):String? {
        if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
           ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestCode
            )
           return null
       }
        else{
            var value:String?="work"
            val location = fusedLocationProviderClient.lastLocation
            location.addOnSuccessListener {locationValue ->
               if (locationValue == null){
                   value = null
               }
                var latitudeText = "latitude: ${locationValue.latitude.toString()}"
                var longitudeText = "longitude: ${locationValue.longitude.toString()}"
                latitude.text = latitudeText
                longitude.text = longitudeText
            }
            return value
       }

    }
}
