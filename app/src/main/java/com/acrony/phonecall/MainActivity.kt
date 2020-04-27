package com.acrony.phonecall

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var context:Context
    lateinit var etMobile:EditText
    lateinit var btCall:Button
    lateinit var btView:Button

    lateinit var etSMS:EditText
    lateinit var btSMS:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            context=this

            etMobile=findViewById(R.id.et_Mobile) as EditText
            btCall=findViewById(R.id.btn_Call) as Button
            btView=findViewById(R.id.btn_ViewCallLog) as Button

            etSMS=findViewById(R.id.etSMS) as EditText
            btSMS=findViewById(R.id.btnSendSMS) as Button


            btCall.setOnClickListener {
            if(etMobile.text!=null && etMobile.text.toString().trim{it<=' ' }.length>0)
            {
                val callIntent=Intent(Intent.ACTION_CALL)

                callIntent.data= Uri.parse("tel:"+etMobile.text.toString())

                startActivity(callIntent)

            }
                else
            {
                Toast.makeText(applicationContext,"Please enter mobile number",Toast.LENGTH_LONG).show()
            }

             btView.setOnClickListener {
                 if(etMobile.text!=null && etMobile.text.toString().trim{it<=' ' }.length>0) {
                     val callIntent = Intent(Intent.ACTION_VIEW)

                     callIntent.data = Uri.parse("tel:" + etMobile.text.toString())

                     startActivity(callIntent)

                 }
             }


              btnSendSMS.setOnClickListener {

                  val smsManager=SmsManager.getDefault() as SmsManager

                  smsManager.sendTextMessage(etMobile.text!!.toString(),null,etSMS.text!!.toString(),null,null)

              }

            }


        }
        catch (ex:Exception){
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }

    }
}
