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












## Sms Class
´´´java
package tutorialspoint.example.com.smsproject;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;



public class Sms extends AppCompatActivity implements Llamaface {

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
´´´ 
