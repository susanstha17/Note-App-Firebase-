package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDataActivity extends AppCompatActivity {
    EditText name, address, phone;
    Button add;
    FirebaseDatabase fd;
    DatabaseReference dr;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        name = findViewById(R.id.text_name);
        address = findViewById(R.id.text_address);
        phone = findViewById(R.id.text_phone);
        add = findViewById(R.id.add);
        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference("StudentInfo");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()) {


                    DataModel dm = new DataModel();
                    dm.setName(name.getText().toString());
                    dm.setAddress(address.getText().toString());
                    dm.setPhone(phone.getText().toString());

                    String key = dr.push().getKey();
                    dr.child(key).setValue(dm);

                }

            }
        });
    }

    private boolean valid() {
        boolean validation = true;
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("Name cannot be Empty");
            validation = false;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Address cannot be Empty");
            validation = false;
        }
        if (TextUtils.isEmpty(phone.getText().toString())) {
            phone.setError("Phone cannot be Empty");
            validation = false;
        }

        return validation;
    }
}