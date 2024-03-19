package adil.trackerposition.ui

import adil.trackerposition.R
import adil.trackerposition.viewModelConnector.TrackerLocation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(){
    lateinit var latitude: TextView
    lateinit var longitude:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        val button: Button = findViewById(R.id.btnGetLocation)
        var trackerLocation: TrackerLocation = TrackerLocation(this@MainActivity)
        button.setOnClickListener {
            try {
                trackerLocation.getLocation(latitude, longitude)
            }catch (ex:Exception){
                Toast.makeText(this@MainActivity, ex.message,Toast.LENGTH_LONG).show()
            }
        }
    }

}

