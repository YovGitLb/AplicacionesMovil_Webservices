package com.example.aplicacionesmovil_webservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RegisterLogin extends AppCompatActivity {

    TextInputLayout txtusername;
    TextInputLayout txtemail;
    TextInputLayout txtpass;
    TextInputLayout txt_conf_pass;


    EditText etusername, etemail,etpass,et_conf_pass;

    AppCompatButton btn_registrado;
    AppCompatTextView txt_regresar_login;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        txtusername=findViewById(R.id.lay_Name);
        txtemail=findViewById(R.id.lay_Email);
        txtpass=findViewById(R.id.lay_Password);
        txt_conf_pass=findViewById(R.id.lay_ConfPassword);

        etusername=findViewById(R.id.et_Name);
        etemail=findViewById(R.id.et_Email);
        etpass=findViewById(R.id.et_ConfPassword);
        et_conf_pass=findViewById(R.id.et_ConfPassword);

        btn_registrado=findViewById(R.id.appCompatButtonRegister);
        txt_regresar_login=findViewById(R.id.appCompatTextViewLoginLink);
        btn_registrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });




    }



    private void RegisterUser(final String username, final String email, final String pass, final String repeatpass){
        String ip=getString(R.string.ip);
        String url=ip+"/dbcorporacion_webservices/registro_login.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Registro exitoso")) {
                    Toast.makeText(RegisterLogin.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterLogin.this, Login.class));
                    finish();

                } else {
                    Toast.makeText(RegisterLogin.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterLogin.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){

            //get =ctr + espacio para generar el Map
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<>();
                param.put("username",username);
                param.put("email",email);
                param.put("pass",pass);
                param.put("repeatpass",repeatpass);


                return param;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(RegisterLogin.this).addToRequestQueue(request);

    }

}