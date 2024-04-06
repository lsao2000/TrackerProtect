package adil.trackerposition.viewModelConnector

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class CheckNetworkConnection (var context:Context) {

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
            super.onAvailable(network)

        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Toast.makeText(context, "No available connection", Toast.LENGTH_LONG).show()
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

    fun startListening(){
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
    fun stopListenNetworkState() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
    fun checkNetwork():Boolean = isNetworkConnected

}
