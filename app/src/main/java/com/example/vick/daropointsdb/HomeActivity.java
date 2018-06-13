package com.example.vick.daropointsdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout;
    TextView tvWelcome;
    prefConfig pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pref = new prefConfig(this);

        btnLogout = findViewById(R.id.btnLogout);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("Welcome "+ pref.readUsername());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                pref.writeLoginstatus(false);
                pref.writeUsername("user");
            }
        });
    }

}
