package com.example.tecnobank.extension

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.LocaleList
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.tecnobank.R
import com.example.tecnobank.intro.activity.IntroActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random


class MyFirebaseServiceMessage : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        notificatioReceived(applicationContext, title, body)
        if(body?.contains("pix",true)==true) {
            val broadcast = LocalBroadcastManager.getInstance(applicationContext)
            broadcast.sendBroadcast(Intent("UPDATE_BALANCE"))
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    fun notificatioReceived(context: Context, title: String?, message: String?) {

        val notificationChannel: NotificationChannel
        val builder: NotificationCompat.Builder
        val channelId = "com.example.tecnobank"
        val notificationManager: NotificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = Random.nextInt(1, 100)
        val intent = Intent(this, IntroActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, message, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = NotificationCompat.Builder(this, channelId)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_custom_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                        .setSummaryText("Notificação"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
        } else {
            builder = NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_custom_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(id, builder.build())
    }

}