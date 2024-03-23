package adil.trackerposition.viewModelConnector

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
class RunServiceInBackground :Service(){

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        onTaskRemoved(intent)
        Toast.makeText(applicationContext, "service is started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

//    override fun onTaskRemoved(rootIntent: Intent?) {
//        val restartServiceIntent = Intent(applicationContext,this.javaClass)
//        restartServiceIntent.setPackage(packageName)
//        startService(restartServiceIntent)
//        super.onTaskRemoved(rootIntent)
//    }
}
