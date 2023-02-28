package com.victorino.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.victorino.organize.R;
import com.victorino.organize.config.FirebaseConfig;
import com.victorino.organize.helper.EncodeB64;
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
            userToAuth.setName(fieldName);
            userToAuth.setEmail(fieldEmail);
            userToAuth.setPassword(fieldPassword);
            FirebaseAuth authInFirebase = FirebaseConfig.getAuth();
            authInFirebase.createUserWithEmailAndPassword(
                    userToAuth.getEmail(), userToAuth.getPassword()
            ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String encode = EncodeB64.encodeMail(fieldEmail);
                        userToAuth.setId(encode);
                        userToAuth.save();
                        Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        String authException = "";
                        try {
                            throw task.getException();
                        }catch(FirebaseAuthWeakPasswordException e){
                            authException = getString(R.string.fraca);
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            authException = getString(R.string.emailInvalid);
                        }
                        catch (FirebaseAuthUserCollisionException e){
                            authException = getString(R.string.userExists);
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