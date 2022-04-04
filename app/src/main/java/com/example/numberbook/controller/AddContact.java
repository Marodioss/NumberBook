package com.example.numberbook.controller;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.numberbook.R;

public class AddContact extends AppCompatActivity {

    EditText name;
    EditText phone,email;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !phone.getText().toString().isEmpty())
                {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,name.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL,email.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE,phone.getText().toString());
                    startActivity(intent);

                }
                else {
                    Toast.makeText(AddContact.this, "hey hey ,fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}

