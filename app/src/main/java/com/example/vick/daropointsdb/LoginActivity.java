package com.example.vick.daropointsdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtRegister;
    EditText edtUsername,edtPassword;
    private prefConfig PrefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PrefConfig = new prefConfig(this);

        if (PrefConfig.readLoginStatus()){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }else {

        }

        edtUsername = findViewById(R.id.edt_Username);
        edtPassword = findViewById(R.id.edt_Password);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });

        txtRegister = findViewById(R.id.tvRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginProcess() {

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        Api_Interface api_interface = ApiClient.getClient().create(Api_Interface.class);
        Call<ServerResponse> call = api_interface.loginUser(username,password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                if (response.body().getResponse().equals("ok")){

                    PrefConfig.displayToast("Login successful");
                    PrefConfig.writeLoginstatus(true);
                    PrefConfig.writeUsername(response.body().getUsername());

                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
