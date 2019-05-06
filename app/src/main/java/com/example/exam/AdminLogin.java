package com.example.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    EditText email;
    EditText password;
    Firebase url;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Firebase.setAndroidContext(this);
        url = new Firebase("https://exam-9fa0a.firebaseio.com/admin");
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signin = (Button)findViewById(R.id.email_sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stringForEmail=email.getText().toString();
                final String stringForPassword=password.getText().toString();
                url.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child("email").getValue().toString().equals(stringForEmail) && dataSnapshot.child("password").getValue().toString().equals(stringForPassword)){
                            Intent i = new Intent(AdminLogin.this,Admin.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(AdminLogin.this,"wrong information",Toast.LENGTH_SHORT).show();
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
