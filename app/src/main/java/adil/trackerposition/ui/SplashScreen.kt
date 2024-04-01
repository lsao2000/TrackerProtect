package adil.trackerposition.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import adil.trackerposition.R
import adil.trackerposition.viewModelConnector.CheckNetworkConnection
import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.core.content.withStyledAttributes
import com.bumptech.glide.load.engine.executor.GlideExecutor

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var brand:TextView
    lateinit var imageView:ImageView
    lateinit var errorView:ImageView
    lateinit var testNetwork:CheckNetworkConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imageView = findViewById(R.id.imageview)
        errorView = findViewById(R.id.error_connection)
        brand = findViewById(R.id.brand)
        Glide.with(this).load(R.drawable.error_connection).into(errorView)
        Glide.with(this).load(R.drawable.logo).into(imageView)
        testNetwork = CheckNetworkConnection(this@SplashScreen, "splash")
        if(testNetwork.checkNetwork()){
            addAnimation()
        }else{
            connectionFailed()
        }
    }
    // INFO: calling this method if the network connection available
    fun addAnimation(){
        val animation:Animation = AnimationUtils.loadAnimation(this@SplashScreen, R.anim.splash_anim)
        imageView.alpha = 1F
        brand.alpha = 1F
        errorView.alpha = 0F
        brand.startAnimation(animation)
        try{
            animation.setAnimationListener(object :Animation.AnimationListener{
                override fun onAnimationEnd(animation: Animation?) {
                    try {
                        switchActivity()
                    }catch (ex:Exception){
                        Toast.makeText(this@SplashScreen, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
                override fun onAnimationStart(animation: Animation?) {
                }
                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }catch (ex:Exception){
            Log.i("animationError", ex.message.toString())
        }

    }
    // INFO: we call this function if the network isn't available
    fun connectionFailed(){
        imageView.alpha = 0F
        brand.alpha = 0F
        errorView.visibility = View.VISIBLE
        errorView.alpha = 1F
        Toast.makeText(this@SplashScreen, "No available connection", Toast.LENGTH_LONG).show()
        val connectivityManager: ConnectivityManager = (this.getSystemService() as ConnectivityManager?)!!
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Toast.makeText(this@SplashScreen,"connection available", Toast.LENGTH_LONG).show()
                addAnimation()
            }

            override fun onLost(network: Network) {
                // Called when network disconnects
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
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
    fun switchActivity(){
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}