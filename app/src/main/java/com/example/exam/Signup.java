package com.example.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Signup extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText number;
    Button signup;
    Firebase url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Firebase.setAndroidContext(this);
        url = new Firebase("https://exam-9fa0a.firebaseio.com/users");

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.mobile);
        signup=(Button)findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stringForEmail = email.getText().toString().replaceAll("\\.",",");
                final String stringForPassword = password.getText().toString();
                final String stringForName= name.getText().toString();
                final String stringForNumber= number.getText().toString();
                url.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.child(stringForEmail).exists()){
                           url.child(stringForEmail).child("name").setValue(stringForName);
                           url.child(stringForEmail).child("password").setValue(stringForPassword);
                           url.child(stringForEmail).child("number").setValue(stringForNumber);

                            Intent i = new Intent(Signup.this,LoginActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(Signup.this,"already registered",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

    }
}
