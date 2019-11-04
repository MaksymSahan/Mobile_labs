package com.nulp.youtubetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    private Button btnLogout;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        TextView welcomeText = findViewById(R.id.welcome);
        if (user != null) {
            final String username = user.getDisplayName();
            if (username != null) {
                welcomeText.setText(String.format("Welcome, %s", username));
            }
        }
        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intToMain = new Intent(Welcome.this, SignUp.class);
            startActivity(intToMain);
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
