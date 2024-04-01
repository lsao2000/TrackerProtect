package adil.trackerposition.ui

import adil.trackerposition.R
import adil.trackerposition.viewModelConnector.HandleButtonAction
import adil.trackerposition.viewModelConnector.RunServiceInBackground
import adil.trackerposition.viewModelConnector.TrackerLocation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity(){
    lateinit var latitude: TextView
    lateinit var longitude:TextView
    lateinit var bottomNavigation:BottomNavigationView
    lateinit var frameLayout:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fram, Home(this@MainActivity)).commit()
        frameLayout = findViewById(R.id.fram)
        bottomNavigation = findViewById(R.id.nav)
        val support = supportFragmentManager
        bottomNavigation.setOnItemSelectedListener {menu ->
            when(menu.itemId){
                R.id.home -> {
                    HandleButtonAction.changeFragment("home", R.id.fram, support, this@MainActivity)
                    true
                }
                R.id.all_devices ->{
                    HandleButtonAction.changeFragment("tracking", R.id.fram, support, this@MainActivity)
                    true
                }
                R.id.profile -> {
                    HandleButtonAction.changeFragment("profile", R.id.fram, support, this@MainActivity)
                    true
                }
                R.id.add_device -> {
                    HandleButtonAction.changeFragment("addDevice", R.id.fram, support, this@MainActivity)
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
}

