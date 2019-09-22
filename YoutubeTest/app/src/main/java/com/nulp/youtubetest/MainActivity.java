package com.nulp.youtubetest;

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

public class MainActivity extends AppCompatActivity {
EditText emailId, password, nameField, phoneField ;
Button BtnSignUp;
TextView tvSignIn;
FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseAuth = mFirebaseAuth.getInstance();
        nameField = findViewById(R.id.input_name);
        phoneField = findViewById(R.id.Phone);
        emailId = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        tvSignIn = findViewById(R.id.textView2);
        BtnSignUp = findViewById(R.id.btnSignUp);
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String name = nameField.getText().toString();
                String phone = phoneField.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Enter your email!");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Enter your password!");
                    password.requestFocus();
                }
                else if (name.isEmpty()){
                    nameField.setError("Enter your name!");
                    nameField.requestFocus();
                }
                else if (phone.isEmpty()){
                    phoneField.setError("Enter your name!");
                    phoneField.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty() && name.isEmpty() && phone.isEmpty()){
                    Toast.makeText(MainActivity.this,"Empty fields",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up Unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
       tvSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this,LoginActivity.class);
               startActivity(i);
           }
       });
    }
}
