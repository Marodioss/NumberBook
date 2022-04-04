package com.example.numberbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.numberbook.controller.AddContact;
import com.example.numberbook.controller.ShowNumber;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button call, sms, add_Contact,show_Contact;
    CountryCodePicker ccp;
    EditText edittext;
    TextView name;

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccp = findViewById(R.id.ccp);
        edittext =findViewById(R.id.edittext);
        call = findViewById(R.id.call);
        sms = findViewById(R.id.sms);
        add_Contact = findViewById(R.id.add_Contact);
        show_Contact= findViewById(R.id.show_Contact);
        name = findViewById(R.id.name);

        call.setOnClickListener(this);
        sms.setOnClickListener(this);
        add_Contact.setOnClickListener(this);
        show_Contact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String code = ccp.getSelectedCountryCodeWithPlus();
        String number = edittext.getText().toString().trim();
        String phoneNumber = code + number;

        String name1 = name.getText().toString().trim();


        if(v == call){

            if (name.getText().toString().isEmpty() && !edittext.getText().toString().isEmpty()){
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(call);
            }
            if(!name.getText().toString().isEmpty() && edittext.getText().toString().isEmpty())
            {
                String a=getPhoneNumber(name1,this);
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+a));
                startActivity(call);
            }
            if (name.getText().toString().isEmpty() && edittext.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "hey hey ,fill one of the fields", Toast.LENGTH_SHORT).show();

            }

        }

        if(v == sms){

            if (name.getText().toString().isEmpty() && !edittext.getText().toString().isEmpty()){
                Intent msg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
                msg.putExtra("sms_body", "salut ....");
                startActivity(msg);
            }
            if(!name.getText().toString().isEmpty() && edittext.getText().toString().isEmpty())
            {
                String a=getPhoneNumber(name1,this);
                Intent msg = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+a));
                msg.putExtra("sms_body", "salut ....");
                startActivity(msg);
            }
            if (name.getText().toString().isEmpty() && edittext.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "hey hey ,fill one of the fields", Toast.LENGTH_SHORT).show();

            }
        }

        if(v == add_Contact){
            Intent intent  = new Intent(MainActivity.this, AddContact.class);
            startActivity(intent);

        }
        if(v == show_Contact){
            Intent intent  = new Intent(MainActivity.this, ShowNumber.class);
            startActivity(intent);

        }
    }


    public String getPhoneNumber(String name, Context context) {
        String ret = null;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + name +"%'";
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            ret = c.getString(0);
        }
        c.close();
        if(ret==null)
            ret = "Unsaved";

        return ret;
    }

}