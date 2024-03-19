package adil.trackerposition.viewModelConnector

import adil.trackerposition.R
import adil.trackerposition.ui.NotifactionActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationOperations() {
    companion object {
        const val DEFAULT_CHANNEL_ID = "channelId"
        var NOTIFICATIN_ID: Int = 1;

        fun getNotify(context: Context) {
            createChannel(context)
            val intent: Intent = Intent(context, NotifactionActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val builder = NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID).apply {
                setSmallIcon(R.drawable.ic_launcher_background)
                setContentTitle("This is a test")
                setContentText("latitude: placeholder, longitude: placeholder")
                setContentIntent(pendingIntent)
                setAutoCancel(true).apply {
                    priority = NotificationCompat.PRIORITY_DEFAULT
                }
            }
            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                notify(NOTIFICATIN_ID, builder.build())
                try {
                } catch (ex: IllegalArgumentException) {
                    Log.e("notification", "Error posting notification: ${ex.message}")
                }
            }
        }
        fun createChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.channel_name)
                val descriptionText = context.getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(DEFAULT_CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

        }

    }
}
