package com.polytechnic.astra.ac.id.internak.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.util.Log;

import com.polytechnic.astra.ac.id.internak.R;

public class RegisterBerhasilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_berhasil);

        // Retrieve registered user's name
        Intent intent = getIntent();
        String registeredName = intent.getStringExtra("REGISTERED_NAME");

        // Set the title TextView with the registered user's name
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("Selamat datang, " + registeredName);

        // Handle btn_next click
        ImageButton btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to login page
                Intent loginIntent = new Intent(RegisterBerhasilActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}
