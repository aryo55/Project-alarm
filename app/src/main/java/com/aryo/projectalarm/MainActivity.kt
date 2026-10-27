package com.aryo.projectalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("pairing", Context.MODE_PRIVATE)
        var code = prefs.getString("code", null)
        if (code == null) {
            code = UUID.randomUUID().toString().take(8).uppercase()
            prefs.edit().putString("code", code).apply()
        }
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(48, 48, 48, 48) }
        val pairingText = TextView(this).apply { text = "Kode pairing perangkat ini: $code\nBagikan kode ini ke Muse lewat chat untuk mengaitkan perangkat."; textSize = 18f }
        val picker = TimePicker(this).apply { setIs24HourView(true) }
        val button = Button(this).apply { text = "Set alarm" }
        val status = TextView(this).apply { text = "Belum ada alarm"; textSize = 16f }
        button.setOnClickListener {
            val cal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, picker.hour)
                set(Calendar.MINUTE, picker.minute)
                set(Calendar.SECOND, 0)
                if (timeInMillis <= System.currentTimeMillis()) add(Calendar.DAY_OF_YEAR, 1)
            }
            val intent = Intent(this, AlarmReceiver::class.java)
            val pending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pending)
            status.text = "Alarm disetel jam %02d:%02d".format(picker.hour, picker.minute)
        }
        layout.addView(pairingText)
        layout.addView(picker)
        layout.addView(button)
        layout.addView(status)
        setContentView(layout)
    }
}
