package adil.trackerposition.ui

import adil.trackerposition.R
import androidx.core.content.getSystemService
import adil.trackerposition.viewModelConnector.HandleButtonAction
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
//import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(){
    lateinit var latitude: TextView
    lateinit var longitude:TextView
    lateinit var bottomNavigation:BottomNavigationView
    lateinit var frameLayout:FragmentContainerView
    lateinit var alert: Dialog
    lateinit var connectivityManager: ConnectivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // start listenning for network connection and changed in the network
        connectivityManager = (this@MainActivity.getSystemService() as ConnectivityManager?)!!
        alert = Dialog(this@MainActivity)
        alert.setContentView(R.layout.error_connection_alert_dialog)
        alert.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alert.setCancelable(false)
        val image:ImageView = alert.findViewById(R.id.img_error)
        Glide.with(this@MainActivity).load(R.drawable.error_connection).into(image)
        fun NetworkCapabilities?.isNetworkCapabilitiesValid(): Boolean = when {
            this == null -> false
            hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                    (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> true
            else -> false
        }
        val isNetworkConnected: Boolean = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            .isNetworkCapabilitiesValid()
        val networkCallback = object :ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                runOnUiThread {
                    if(alert.isShowing){
                        alert.dismiss()
                    }
                }
                Toast.makeText(this@MainActivity, "network available", Toast.LENGTH_LONG).show()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                runOnUiThread {
                    if(!alert.isShowing){
                        alert.show()
                    }
                }


            }

            override fun onUnavailable() {
                super.onUnavailable()

            }

            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities)

            }

            override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
                super.onBlockedStatusChanged(network, blocked)

            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)

            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                super.onLinkPropertiesChanged(network, linkProperties)

            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        var getIntent = intent
        var user_id:Int=0
        try {
             user_id = getIntent.getIntExtra("user_id", 0)
        }catch (ex:Exception){
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
//        var user_id =
        // INFO: the code below for changing fragment and handle button actions for fragment
        supportFragmentManager.beginTransaction().add(R.id.fram, Home(this@MainActivity, user_id)).commit()
        frameLayout = findViewById(R.id.fram)
        bottomNavigation = findViewById(R.id.nav)
        val support = supportFragmentManager
        bottomNavigation.setOnItemSelectedListener {menu ->
            when(menu.itemId){
                R.id.home -> {
                    HandleButtonAction.changeFragment("home", R.id.fram, support, this@MainActivity, user_id)
                    true
                }
                R.id.all_devices ->{
                    HandleButtonAction.changeFragment("tracking", R.id.fram, support, this@MainActivity,user_id )
                    true
                }
                R.id.profile -> {
                    HandleButtonAction.changeFragment("profile", R.id.fram, support, this@MainActivity, user_id)
                    true
                }
                R.id.add_device -> {
                    HandleButtonAction.changeFragment("addDevice", R.id.fram, support, this@MainActivity, user_id)
                    true
                }
                else -> false
            }
        }

//        latitude = findViewById(R.id.latitude)
//        longitude = findViewById(R.id.longitude)
//        val button: Button = findViewById(R.id.btnGetLocation)
//        var trackerLocation: TrackerLocation = TrackerLocation(this@MainActivity)
//        button.setOnClickListener {
//            try {
//                trackerLocation.getLocation(latitude, longitude)
//            }catch (ex:Exception){
//                Toast.makeText(this@MainActivity, ex.message,Toast.LENGTH_LONG).show()
//            }
//            startService(Intent(applicationContext, RunServiceInBackground::class.java))
//        }
    }

    override fun onResume() {
        super.onResume()

    }
    fun cancelAlert(){
        alert.cancel()
    }


}

