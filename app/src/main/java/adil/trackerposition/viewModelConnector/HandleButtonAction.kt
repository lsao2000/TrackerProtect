package adil.trackerposition.viewModelConnector

import adil.trackerposition.ui.AddDevice
import adil.trackerposition.ui.Home
import adil.trackerposition.ui.Profile
import adil.trackerposition.ui.Tracking
import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
class HandleButtonAction {
    companion object {
        fun changeFragment(button: String, id: Int, supportFragement: FragmentManager, context:Activity) {
            when (button) {
                "home" -> {
                    val home = Home(context)
                    supportFragement.beginTransaction().replace(id, home).commit()
                }
                "tracking" -> {
                    var tracking = Tracking()
                    supportFragement.beginTransaction().replace(id, tracking).commit()
                }
                "profile" -> {
                    var profile = Profile()
                    supportFragement.beginTransaction().replace(id, profile).commit()
                }
                else -> {
                    var addDevice = AddDevice()
                    supportFragement.beginTransaction().replace(id, addDevice).commit()
                }
            }
        }
    }
}
