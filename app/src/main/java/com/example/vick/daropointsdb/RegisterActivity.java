package com.example.vick.daropointsdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    TextView txtLogin;
    EditText edtFirstname,edtLastname,edtUsername,edtIdnumber,edtPassword;
    private prefConfig PrefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstname = findViewById(R.id.edtFirstname);
        edtLastname = findViewById(R.id.edtLastname);
        edtUsername = findViewById(R.id.edtUsername);
        edtIdnumber = findViewById(R.id.edtId_number);
        edtPassword = findViewById(R.id.edtPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProcess();
            }
        });

        txtLogin = findViewById(R.id.tvLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        PrefConfig = new prefConfig(getApplicationContext());


    }

//    public static boolean isEmailValid(String email){
//        Pattern pattern = Patterns.EMAIL_ADDRESS;
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }

    private void registerProcess() {

        String firstname  = edtFirstname.getText().toString();
        String lastname = edtLastname.getText().toString();
        String username = edtUsername.getText().toString();
        String id_number = edtIdnumber.getText().toString();
        String password = edtPassword.getText().toString();

        if (firstname.equals("")){
            edtFirstname.setError("Field cannot be left epmty");
        }
        else if (lastname.equals("")){
            edtLastname.setError("Field cannot be left empty");
        }
        else if (username.equals("")){
            edtUsername.setError("Field cannot be left empty");
        }
        else if (id_number.equals("")){
            edtIdnumber.setError("Field cannot be left empty");
        }
        else if (password.equals("")){
            edtPassword.setError("Field cannot be left empty");
        }
        else if (password.length()>=6){

            Api_Interface api_interface = ApiClient.getClient().create(Api_Interface.class);
            Call<ServerResponse> call = api_interface.registerUser(firstname,lastname,username,password,id_number);
            call.enqueue(new Callback<ServerResponse>()
            {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                    if (response.body().getResponse().equals("ok"))
                    {
                        PrefConfig.displayToast("Registration successful");
                    }
                    else if(response.body().getResponse().equals("exist"))
                    {
                        PrefConfig.displayToast("Username already exists...");
                    }
                    else if (response.body().getResponse().equals("error"))
                    {
                        PrefConfig.displayToast("Something went wrong...");
                    }

                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "connection failed", Toast.LENGTH_LONG).show();

                }
            });


        }else {
            edtPassword.setError("password must be more than 6 characters..");
        }
    }
}
