package tutorialspoint.example.com.smsproject

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sms.*

import org.w3c.dom.Text
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

;
            }
        }



}


}
