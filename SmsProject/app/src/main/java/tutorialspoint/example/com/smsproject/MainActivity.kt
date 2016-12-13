package tutorialspoint.example.com.smsproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {


    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSendSMS.setOnClickListener {
            val phoneNo = txtPhoneNo.text.toString()
            val message = txtMessage.text.toString()
            if (phoneNo.length > 0 && message.length > 0)
                sendSMS(phoneNo, message)
            else
                Toast.makeText(baseContext,
                        "Please enter both phone number and message.",
                        Toast.LENGTH_SHORT).show()
        }
    }

    //---sends an SMS message to another device---
    private fun sendSMS(phoneNumber: String, message: String) {
        val SENT = "SMS_SENT"
        val DELIVERED = "SMS_DELIVERED"
        val sentPI = PendingIntent.getBroadcast(this, 0,
                Intent(SENT), 0)
        val deliveredPI = PendingIntent.getBroadcast(this, 0,
                Intent(DELIVERED), 0)


        //---when the SMS has been sent---
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(arg0: Context, arg1: Intent) {
                when (resultCode) {
                    Activity.RESULT_OK -> Toast.makeText(baseContext, "SMS sent",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_GENERIC_FAILURE -> Toast.makeText(baseContext, "Generic failure",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_NO_SERVICE -> Toast.makeText(baseContext, "No service",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_NULL_PDU -> Toast.makeText(baseContext, "Null PDU",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_RADIO_OFF -> Toast.makeText(baseContext, "Radio off",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }, IntentFilter(SENT))

        //---when the SMS has been delivered---
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(arg0: Context, arg1: Intent) {
                when (resultCode) {
                    Activity.RESULT_OK -> Toast.makeText(baseContext, "SMS delivered",
                            Toast.LENGTH_SHORT).show()
                    Activity.RESULT_CANCELED -> Toast.makeText(baseContext, "SMS not delivered",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }, IntentFilter(DELIVERED))

        val sms = SmsManager.getDefault()
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI)
    }
}
