package com.example.app_firebase.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app_firebase.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Adm_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_adm);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_agenda:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case R.id.menu_produtos:
                        startActivity(new Intent(getApplicationContext(),Produtos_activity.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }
}