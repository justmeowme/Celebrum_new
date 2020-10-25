package com.iisdf.celebrum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView mToRegister;
    Button mLogin;
    EditText mEmail, mPassword;
    String sEmail, sPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //SIGN IN
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mToRegister = findViewById(R.id.to_reg);

        mLogin = findViewById(R.id.button_enter);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sEmail = mEmail.getText().toString().trim();
                sPassword = mPassword.getText().toString().trim();
                
                if (sEmail.length()==0 || sPassword.length()==0){
                    Toast.makeText(LoginActivity.this, "You should fill all fields", Toast.LENGTH_SHORT).show();
                } else{
                    mAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                MainActivity.setDefaults("login", String.valueOf(sEmail), LoginActivity.this);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else{
                                Toast.makeText(LoginActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        mToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }
}