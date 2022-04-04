package com.example.numberbook.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.numberbook.R;

import java.util.ArrayList;

public class ShowNumber extends Activity {

    String phoneNumber;
    ListView lv;
    ArrayList<String> aa= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shownumbers);
        lv= (ListView) findViewById(R.id.lv);

        getNumber(this.getContentResolver());
    }



    @SuppressLint("Range")
    public void getNumber(ContentResolver cr)
    {
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            System.out.println(".................."+phoneNumber);
            aa.add(name + " : " + phoneNumber );
            //aa.add(phoneNumber);
        }
        phones.close();// close cursor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,aa);
        lv.setAdapter(adapter);
        //display contact numbers in the list
    }
}