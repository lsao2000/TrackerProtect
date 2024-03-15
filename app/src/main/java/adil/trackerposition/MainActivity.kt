package adil.trackerposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(){
    lateinit var latitude: TextView
    lateinit var longitude:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        val button: Button = findViewById(R.id.btnGetLocation)
        var trackerLocation:TrackerLocation = TrackerLocation(this@MainActivity)
        button.setOnClickListener {

        }
    }

}

