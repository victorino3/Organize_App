package com.victorino.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.victorino.organize.R;
import com.victorino.organize.config.FirebaseConfig;
import com.victorino.organize.model.UserInformation;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

    }
    public void providerSingUp(View view){
        String fieldName = name.getText().toString();
        String fieldPassword = password.getText().toString();
        String fieldEmail = email.getText().toString();
        if(fieldName.isEmpty() || fieldPassword.isEmpty() || fieldEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.empty,Toast.LENGTH_LONG).show();
        }else {
            UserInformation userToAuth = new UserInformation();
            userToAuth.setEmail(fieldEmail);
            userToAuth.setPassword(fieldPassword);
            FirebaseAuth authInFirebase = FirebaseConfig.getAuth();
            authInFirebase.createUserWithEmailAndPassword(
                    userToAuth.getEmail(), userToAuth.getPassword()
            ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        }
    }