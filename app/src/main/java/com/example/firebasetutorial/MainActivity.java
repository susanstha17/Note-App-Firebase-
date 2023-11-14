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

public class MainActivity extends AppCompatActivity {

    Button login, signup;
    EditText email, pw;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        signup = findViewById(R.id.btn_signup);
        login = findViewById(R.id.btn_login);
        email = findViewById(R.id.text_email);
        pw = findViewById(R.id.text_pw);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
                    auth.signInWithEmailAndPassword(email.getText().toString(),
                            pw.getText().toString()).addOnSuccessListener
                            (new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this, "Successfully Login",
                                    Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Login Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            private boolean valid() {
                boolean validation = true;
                if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Email cannot be Empty");
                    validation = false;
                }
                if (TextUtils.isEmpty(pw.getText().toString())) {
                    pw.setError("Password cannot be Empty");
                    validation = false;
                }

                return validation;
            }
        });


    }
}