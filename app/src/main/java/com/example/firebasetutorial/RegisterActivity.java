package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    FirebaseAuth auth;

EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.text_email2);
        password = findViewById(R.id.text_pw2);

        register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (valid()){
               auth.createUserWithEmailAndPassword(email.getText().toString(),
                       password.getText().toString()).
                       addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {
                       Toast.makeText(RegisterActivity.this, "User Registration Successful",
                               Toast.LENGTH_SHORT).show();
                       Intent i =new Intent(RegisterActivity.this,MainActivity.class);
                       startActivity(i);
                   }
               }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(RegisterActivity.this, "User Registration Failed",
                                       Toast.LENGTH_SHORT).show();
                           }
                       });


               }
            }

            private boolean valid(){
            boolean validation= true;
            if(TextUtils.isEmpty(email.getText().toString())){
                email.setError("Email cannot be Empty");
                validation = false;
            }
                if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Password cannot be Empty");
                    validation = false;
                }

                return validation;
            }
        });
    }
}