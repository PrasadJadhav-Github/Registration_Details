package com.example.registration_details.Activities;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.registration_details.R;
import com.example.registration_details.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView tvRegisterMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home");
        }

        tvRegisterMessage = findViewById(R.id.tvRegisterMessage);

        // Set up Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                if (item.getItemId() == R.id.registerNewMember) {
                    tvRegisterMessage.setVisibility(View.GONE);
                    Intent registerIntent = new Intent(MainActivity.this, MemberRegistrationActivity.class);
                    startActivity(registerIntent);
                    return true;
                } else if (item.getItemId() == R.id.displayMemberList) {
                    tvRegisterMessage.setVisibility(View.GONE);
                    Intent displayIntent = new Intent(MainActivity.this, RegistrationDetailsActivity.class);
                    startActivity(displayIntent);
                    return true;
                }
                return false;
            }
        });
    }
}

