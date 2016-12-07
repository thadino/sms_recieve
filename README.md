# SMS


How to send and receive sms on android.

## Authors
Group G7
* Sebastian Ryberg
* Steffen Lefort


## The feature
Denne feature sørger for at applikationer kan sende og/eller modtage sms'er.

*  Krav
    *  Du skal kunne modtage & sende sms på mobilen.
    *  Hvis du vil teste på emulator skal android SDK være nr. 22 eller under, da 23 og op kræver tilladelse fra brugeren til adgang.
    
Denne feature er meget brugbar og bliver brugt af indtil flere af de mest populære firmaer. Det bliver blandt andet brugt til security (tænk handel på nettet gennem NETS hvor du får en midlertidig gokendelses kode) - altså verifikation af brugeren gennem mere end 1 medie. 



## Example of recieving an sms in an android application

### manifest (XML) In Incoming Sms Class
```xml
        <receiver android:name=".IncomingSms"> 
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>
    <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
    <uses-permission android:name="android.permission.READ_SMS" />
```

### Incoming Sms Class
```kotlin
class IncomingSms:BroadcastReceiver() {
  internal val sms = SmsManager.getDefault()
  fun onReceive(context:Context, intent:Intent) {
    // Retrieves a map of extended data from the intent.
    val bundle = intent.getExtras()
    try
    {
      if (bundle != null)
      {
        val pdusObj = bundle.get("pdus") as Array<Any>
        for (i in pdusObj.indices)
        {
          val currentMessage = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
          val phoneNumber = currentMessage.getDisplayOriginatingAddress()
          val senderNum = phoneNumber
          val message = currentMessage.getDisplayMessageBody()
          Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message)
          // Show Alert
          val duration = Toast.LENGTH_LONG
          val toast = Toast.makeText(context,
           "senderNum: " + senderNum + ", message: " + message, duration)
          toast.show()
        } // end for loop
      } // bundle is null
    }
    catch (e:Exception) {
      Log.e("SmsReceiver", "Exception smsReceiver" + e)
    }
  }
}
```


### Incoming Sms (Sms Class)

```kotlin
fun Context.toast(message: String)
{
  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
class Sms : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        if (intent.extras != null) {
            val value = intent.extras.getString("Message")
            //The key argument here must match that used in the other activity
//            toast(value)
            if(value != null) {
                val newline = System.getProperty("line.separator")
                textView.text = textView.getText().toString() + newline + newline    +  value
            }
        }
}
}
```


### Send Sms Class
```kotlin
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
```
## Manifest XML Send Sms Class
```xml
 <uses-permission android:name="android.permission.SEND_SMS">
    </uses-permission>
```

## Pitfalls 
Hvis man bruger emulator så husk at android SDK er under 23.


## List Of Sources

* good sources
    * https://www.tutorialspoint.com/android/android_sending_sms.htm
    * http://androidexample.com/Incomming_SMS_Broadcast_Receiver_-_Android_Example/index.php?view=article_discription&aid=62
 
* dead end sources
    * https://www.tutorialspoint.com/android/android_broadcast_receivers.htm





