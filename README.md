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

### manifest (XML)
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="tutorialspoint.example.com.smsproject">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".Sms">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


        <receiver android:name="IncomingCall"> // 
            <intent-filter>
                <action android:name="android.intent.action.PHONE_STATE" />
            </intent-filter>
        </receiver>


        <receiver android:name="MyReceiver" >
            <intent-filter>
                <action android:name="ax.androidexample.mybroadcast" />
            </intent-filter>
        </receiver>

        <receiver android:name=".IncomingSms"> 
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>

    </application>
    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="17" />

    <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>

</manifest>
```

### Incoming Sms Class (Java)
```java


public class IncomingSms extends BroadcastReceiver {



    final SmsManager sms = SmsManager.getDefault();




    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);


                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();



                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }

}
```

### Incoming Sms Class (Kotlin)
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


### Sms Class

```java



public class Sms extends AppCompatActivity  {

    private static TextView text1;
    static String Yolo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
//        IncomingSms incSms = new IncomingSms(this);


        final TextView textview = (TextView) findViewById(R.id.textView);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("yolo", "" + R.id.textView);
                textview.setText(Yolo);
            }
        });


    }

    static void settext(String Text) {
//        Log.d("abekat", Text);
//        Log.d("yolo", "" + R.id.textView);
//        final TextView textview = (TextView) findViewById(R.id.textView);
//        textview.setText("yolo");
        Yolo = Text;
        Log.d("yolo", "" + Yolo);



    }



    @Override
    public void textSetter(String msg) {
        Log.d("jeg er fundet !!! ", "" + R.id.textView);
        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText(msg);
    }
    


}
```


### Sms Class (Kotlin)
```kotlin
class Sms:AppCompatActivity() {
  protected fun onCreate(savedInstanceState:Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sms)
    // IncomingSms incSms = new IncomingSms(this);
    val textview = findViewById(R.id.textView) as TextView
    val button = findViewById(R.id.button) as Button
    button.setOnClickListener(object:View.OnClickListener() {
      fun onClick(v:View) {
        Log.d("yolo", "" + R.id.textView)
        textview.setText(Yolo)
      }
    })
  }
  fun textSetter(msg:String) {
    Log.d("jeg er fundet !!! ", "" + R.id.textView)
    val textview = findViewById(R.id.textView) as TextView
    textview.setText(msg)
  }
  companion object {
    private val text1:TextView
    internal var Yolo:String
    internal fun settext(Text:String) {
      // Log.d("abekat", Text);
      // Log.d("yolo", "" + R.id.textView);
      // final TextView textview = (TextView) findViewById(R.id.textView);
      // textview.setText("yolo");
      Yolo = Text
      Log.d("yolo", "" + Yolo)
    }
  }
}
```

## Example of sending an sms in an android application


## Manifest XML
```xml
 <uses-permission android:name="android.permission.SEND_SMS">
    </uses-permission>
```

## Main Activity (Java)
```Java
public class MainActivity extends Activity {

        Button btnSendSMS;
        EditText txtPhoneNo;
        EditText txtMessage;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
            txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
            txtMessage = (EditText) findViewById(R.id.txtMessage);

            btnSendSMS.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    String phoneNo = txtPhoneNo.getText().toString();
                    String message = txtMessage.getText().toString();
                    if (phoneNo.length()>0 && message.length()>0)
                        sendSMS(phoneNo, message);
                    else
                        Toast.makeText(getBaseContext(),
                                "Please enter both phone number and message.",
                                Toast.LENGTH_SHORT).show();
                }
            });
        }

    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message)
    {String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);



        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));


        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

}
```

## Main Activity (Kotlin)
```kotlin
class MainActivity:Activity() {
  internal var btnSendSMS:Button
  internal var txtPhoneNo:EditText
  internal var txtMessage:EditText
  /** Called when the activity is first created. */
  fun onCreate(savedInstanceState:Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    btnSendSMS = findViewById(R.id.btnSendSMS) as Button
    txtPhoneNo = findViewById(R.id.txtPhoneNo) as EditText
    txtMessage = findViewById(R.id.txtMessage) as EditText
    btnSendSMS.setOnClickListener(object:View.OnClickListener() {
      fun onClick(v:View) {
        val phoneNo = txtPhoneNo.getText().toString()
        val message = txtMessage.getText().toString()
        if (phoneNo.length > 0 && message.length > 0)
        sendSMS(phoneNo, message)
        else
        Toast.makeText(getBaseContext(),
                       "Please enter both phone number and message.",
                       Toast.LENGTH_SHORT).show()
      }
    })
  }
  //---sends an SMS message to another device---
  private fun sendSMS(phoneNumber:String, message:String) {
    val SENT = "SMS_SENT"
    val DELIVERED = "SMS_DELIVERED"
    val sentPI = PendingIntent.getBroadcast(this, 0,
                                            Intent(SENT), 0)
    val deliveredPI = PendingIntent.getBroadcast(this, 0,
                                                 Intent(DELIVERED), 0)
    //---when the SMS has been sent---
    registerReceiver(object:BroadcastReceiver() {
      fun onReceive(arg0:Context, arg1:Intent) {
        when (getResultCode()) {
          Activity.RESULT_OK -> Toast.makeText(getBaseContext(), "SMS sent",
                                               Toast.LENGTH_SHORT).show()
          SmsManager.RESULT_ERROR_GENERIC_FAILURE -> Toast.makeText(getBaseContext(), "Generic failure",
                                                                    Toast.LENGTH_SHORT).show()
          SmsManager.RESULT_ERROR_NO_SERVICE -> Toast.makeText(getBaseContext(), "No service",
                                                               Toast.LENGTH_SHORT).show()
          SmsManager.RESULT_ERROR_NULL_PDU -> Toast.makeText(getBaseContext(), "Null PDU",
                                                             Toast.LENGTH_SHORT).show()
          SmsManager.RESULT_ERROR_RADIO_OFF -> Toast.makeText(getBaseContext(), "Radio off",
                                                              Toast.LENGTH_SHORT).show()
        }
      }
    }, IntentFilter(SENT))
    //---when the SMS has been delivered---
    registerReceiver(object:BroadcastReceiver() {
      fun onReceive(arg0:Context, arg1:Intent) {
        when (getResultCode()) {
          Activity.RESULT_OK -> Toast.makeText(getBaseContext(), "SMS delivered",
                                               Toast.LENGTH_SHORT).show()
          Activity.RESULT_CANCELED -> Toast.makeText(getBaseContext(), "SMS not delivered",
                                                     Toast.LENGTH_SHORT).show()
        }
      }
    }, IntentFilter(DELIVERED))
    val sms = SmsManager.getDefault()
    sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI)
  }
}
```


## Pitfalls 



## List Of Sources

* good sources
    * https://www.tutorialspoint.com/android/android_sending_sms.htm
    * http://androidexample.com/Incomming_SMS_Broadcast_Receiver_-_Android_Example/index.php?view=article_discription&aid=62
 
* dead end sources
    * https://www.tutorialspoint.com/android/android_broadcast_receivers.htm








# sms_recieve

##### Table of Contents  
[Headers](#headers)  
[Java](#Java)
[Javascript](#Javascript)
## Headers
 
<a name="headers"/>

<h2>Example of code</h2>

<pre>
    <div class="container">
        <div class="block two first">
            <h2>Your title</h2>
            <div class="wrap" style="width: 480px; height:480px; border: 1px solid black;">
            
            <div class="title_box">
            
            hello world 
            </div>
     
            </div>
        </div>
    </div>
</pre>

## Javascript


<a name="Javascript"/>
```javascript
if (isAwesome){
  return true
}
```

## Java


<a name="Java"/>
```java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inherit;

/**
 *
 * @author Dino
 */
public class Inherit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
                car car = new car();
                System.out.println(car.getname());
     
        System.out.println(car.getmærke() + ", " + car.getname());
        
    }
    
}

// du kan lave en ny bil uden at bruge constructor functionen i javascript
// men ikke i java

// du behøver ikke bruge constructor i 
// javascript's klasser som arver en konstructor fra classen den arver fra

```














First Header | Second Header
------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column





# This is an <h2> tag

1. Item 1
2. Item 2
3. Item 3
   * Item 3a
   * Item 3b
   
   
 ## This is an <h2> tag  
   * Item 1
* Item 2
  * Item 2a
  * Item 2b
  


As Kanye West said:

> We're living the future so
> the present is our past.













