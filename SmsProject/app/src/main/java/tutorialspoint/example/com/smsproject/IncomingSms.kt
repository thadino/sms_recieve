package tutorialspoint.example.com.smsproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log
import android.widget.TextView
import android.widget.Toast



class IncomingSms : BroadcastReceiver() {

    internal val sms = SmsManager.getDefault()



    override fun onReceive(context: Context, intent: Intent) {

        // Retrieves a map of extended data from the intent.
        val bundle = intent.extras

        try {

            if (bundle != null) {

//                val pdusObj = bundle.get("pdus") as Array<Any>
                val pdusObj = bundle["pdus"] as Array<Any>
                val format = bundle["format"] as String

                for (i in pdusObj.indices) {

                    val currentMessage = SmsMessage.createFromPdu(pdusObj[i] as ByteArray, format)
                    val phoneNumber = currentMessage.displayOriginatingAddress

                    val senderNum = phoneNumber
                    val message = currentMessage.displayMessageBody




                    Log.i("SmsReceiver", "senderNum: $senderNum; message: $message")
//                   context.toast("senderNum: $senderNum, message: $message")
                    // Show Alert



                    val intent = Intent(context, Sms::class.java)
                    intent.extras.putString("Message", "senderNum: $senderNum, message: $message")
                    context.startActivity(intent)

                    val i = Intent(context, Sms::class.java)
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                    i.putExtra("Message", "senderNum: $senderNum, message: $message")
                    context.startActivity(i)

                } // end for loop
            } // bundle is null

        } catch (e: Exception) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e)

        }

    }


}



