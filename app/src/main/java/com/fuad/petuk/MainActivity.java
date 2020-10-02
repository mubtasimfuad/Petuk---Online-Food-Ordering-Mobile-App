package com.fuad.petuk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button buttonSignup, buttonSignIn;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    ImageView imgLogo;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        imgLogo=(ImageView)findViewById(R.id.imgLogo) ;

        Animation animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
        imgLogo.startAnimation(animation);


        buttonSignup = (Button) findViewById(R.id.btnSignUP);
        buttonSignIn = (Button) findViewById(R.id.btnLogIn);
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextPassword = (EditText) findViewById(R.id.editPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Intent intent2 = new Intent(MainActivity.this,Register.class);
                startActivity(intent2);            }
        });
        buttonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError(("Enter a valid number"));
                    editTextEmail.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    editTextPassword.setError("Password must have atleast 6 digits");
                    editTextPassword.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);


                        if (task.isSuccessful()) {


                            Intent HomeIntent;
                            HomeIntent  = new Intent(MainActivity.this, Home.class);
                            HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(HomeIntent);


                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }


        });


    }
}




