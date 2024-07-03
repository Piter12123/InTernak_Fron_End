package com.polytechnic.astra.ac.id.internak;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.polytechnic.astra.ac.id.internak.Fragment.HalamUtamaFragment;
import com.polytechnic.astra.ac.id.internak.Fragment.LoginFragment;
import com.polytechnic.astra.ac.id.internak.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_login, new HalamUtamaFragment())
                    .commit();
        }
    }
}
