package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtPhone = findViewById(R.id.phone);
        EditText edtPassword = findViewById(R.id.password);
        EditText edtEmail = findViewById(R.id.email);
        Button btn = findViewById(R.id.signup);

        btn.setOnClickListener(view -> {
            String phone = edtPhone.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            database = FirebaseDatabase.getInstance("https://fir-36c46-default-rtdb.firebaseio.com/")
                    .getReference().child("Users");

            User user = new User(phone,email,password);
            database.child(phone).setValue(user).addOnSuccessListener(new OnSuccessListener(){
                @Override
                public void onSuccess(Object o) {
                    edtPhone.getText().clear();
                    edtEmail.getText().clear();
                    edtPassword.getText().clear();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка",1);
                }
            });

        });
    }
}