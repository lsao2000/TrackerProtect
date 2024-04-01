package adil.trackerposition.viewModelConnector

import adil.trackerposition.ui.SplashScreen
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.getSystemService

class CheckNetworkConnection (var context:Context){
    lateinit var type: String
    constructor(context: Context, type:String):this(context){
        this.type = type
    }
    private val connectivityManager: ConnectivityManager = (context.getSystemService() as ConnectivityManager?)!!

    private fun NetworkCapabilities?.isNetworkCapabilitiesValid(): Boolean = when {
        this == null -> false
        hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> true
        else -> false
    }
    val isNetworkConnected: Boolean
        get() = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            .isNetworkCapabilitiesValid()
    private val networkCallback = object :ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            // Called when network is ready to use

        }

        override fun onLost(network: Network) {
            // Called when network disconnects
            Toast.makeText(context, "No available connection", Toast.LENGTH_LONG).show()
        }

        override fun onUnavailable() {
            // Called if the requested network request cannot be fulfilled
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            // Called when the network corresponding to this request changes capabilities
            // For example, "NetworkCapabilities.NET_CAPABILITY_VALIDATED" might appear with a delay.
        }

        override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
            // Called when access to the specified network is blocked or unblocked.
            // This function can be called, for example, when the device goes to the sleep mode.
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            // Called when the network is about to be lost
        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            // Called when the network corresponding to this request changes LinkProperties
        }

    }
    fun startListening(){
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
    fun stopListenNetworkState() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
    fun checkNetwork():Boolean = isNetworkConnected
}