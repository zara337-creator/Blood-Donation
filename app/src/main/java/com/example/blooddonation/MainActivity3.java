package com.example.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    private EditText editTextPhoneNumber;
    private Button buttonSubmit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(MainActivity3.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                } else {
                    // Assuming phone number based reset, this might involve sending OTP etc.
                    mAuth.sendPasswordResetEmail(phoneNumber) // or send signIn link etc. based on use case
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity3.this, "Password reset instructions sent", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity3.this, "Failed to send reset instructions", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
