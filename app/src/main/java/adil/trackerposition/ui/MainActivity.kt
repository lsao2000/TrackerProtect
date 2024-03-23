package adil.trackerposition.ui

import adil.trackerposition.R
import adil.trackerposition.viewModelConnector.RunServiceInBackground
import adil.trackerposition.viewModelConnector.TrackerLocation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity(){
    lateinit var latitude: TextView
    lateinit var longitude:TextView
    lateinit var bottomNavigation:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.nav)
        bottomNavigation.setOnItemSelectedListener {menu ->
            when(menu.itemId){
                R.id.home -> {
                    homeAction()
                    true
                }
                R.id.all_devices ->{
                    trackAction()
                    true
                }
                R.id.profile -> {
                    profile()
                    true
                }
                R.id.add_device -> {
                    addDevice()
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
    fun homeAction(){
        Toast.makeText(applicationContext, "home btn", Toast.LENGTH_LONG).show()
    }
    fun trackAction(){
        Toast.makeText(applicationContext, "track", Toast.LENGTH_LONG).show()
    }
    fun addDevice(){
        Toast.makeText(applicationContext, "add device", Toast.LENGTH_LONG).show()
    }
    fun profile(){
       Toast.makeText(applicationContext, "profile", Toast.LENGTH_LONG).show()
    }
}

