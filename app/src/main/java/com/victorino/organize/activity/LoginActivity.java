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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.victorino.organize.R;
import com.victorino.organize.config.FirebaseConfig;
import com.victorino.organize.model.UserInformation;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextEmailLog);
        password = findViewById(R.id.editTextPassLog);
    }

    public void signInUser(View view){
        String fieldPassword = password.getText().toString();
        String fieldEmail = email.getText().toString();
        if(fieldPassword.isEmpty() || fieldEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.empty,Toast.LENGTH_LONG).show();
        }else {
            UserInformation userToAuth = new UserInformation();
            userToAuth.setEmail(fieldEmail);
            userToAuth.setPassword(fieldPassword);
            FirebaseAuth authInFirebase = FirebaseConfig.getAuth();
            authInFirebase.signInWithEmailAndPassword(
                    userToAuth.getEmail(), userToAuth.getPassword()
            ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), R.string.login, Toast.LENGTH_LONG).show();
                    } else {
                        String authException = "";
                        try {
                            throw task.getException();
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            authException = getString(R.string.emailInvalid);
                        }
                        catch (FirebaseAuthInvalidUserException e){
                            authException = getString(R.string.userNotFound);
                        }
                        catch (Exception e) {
                            authException = R.string.error + e.getMessage();
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), authException, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}