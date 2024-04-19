package adil.trackerposition.viewModelConnector

import adil.trackerposition.data.Api.ApiDatabase
import adil.trackerposition.data.Api.GetDatabaseApi
import adil.trackerposition.data.Api.User
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


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
                }else{
                    try {
                        var latitudeText = "latitude: ${locationValue.latitude.toString()}"
                        var longitudeText = "longitude: ${locationValue.longitude.toString()}"
                        latitude.text = latitudeText
                        longitude.text = longitudeText
                        runBlocking {
                            while (true) {
                                var latitudeNumber: Double = locationValue.latitude.toDouble()
                                var longitudeNumber: Double = locationValue.longitude.toDouble()
                                updateLocationInDatabase(latitudeNumber, longitudeNumber, "lah34")
//                                Toast.makeText(context, "run ${count} time", Toast.LENGTH_LONG).show()
                            }
                        }
                    }catch (Ex:Exception){
                        Log.i("apiError", Ex.message.toString())
                        Toast.makeText(context, Ex.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
            return value
        }
    }

    suspend fun updateLocationInDatabase(latitude: Double, longitude: Double, user_token:String) {

        var retrofit: Retrofit? = GetDatabaseApi.getRetrofit()
        var apiDatabase: ApiDatabase = retrofit!!.create(ApiDatabase::class.java)
        var user: User = User()
        user.user_token = user_token;
        user.latitude = latitude
        user.longitude = longitude
        var result = apiDatabase.updateLocation(user.latitude, user.longitude, user.user_token)
        result.enqueue(object : Callback<User> {
            override fun onResponse(p0: Call<User>, p1: Response<User>) {
                if (p1.isSuccessful) {
                    Toast.makeText(context, p1.body()?.username.toString(), Toast.LENGTH_LONG).show()
                } else {
                    Log.i("failureApi", p1.code().toString())
//                    Toast.makeText(context, "error sending ${p1.errorBody()}", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(p0: Call<User>, p1: Throwable) {
//                Log.i("failureApi", p1.message.toString())
            }
        })
        delay(10000L)
    }
}
