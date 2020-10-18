package com.example.aplicacionesmovil_webservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputLayout txtemail;
    TextInputLayout txtpass;
    EditText et_email;
    EditText et_pass;
    AppCompatButton btn_ingresar;
    AppCompatTextView txtregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtemail=findViewById(R.id.lay_Email);
        txtpass=findViewById(R.id.lay_Password);

        et_email=findViewById(R.id.et_Email);
        et_pass=findViewById(R.id.et_Password);

        btn_ingresar=findViewById(R.id.appCompatButtonLogin);
        txtregister=findViewById(R.id.textViewLinkRegister);




    }
}