package com.example.aplicacionesmovil_webservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrudProducto extends AppCompatActivity {


    TextInputLayout codigo, producto, precio, fabricante;

    EditText etxtcodigo, etxtproducto, etxtprecio, etxtfabricante;


    AppCompatButton btnregistrar;

    AppCompatButton btnconsultar;

    AppCompatButton btnupdate;

    AppCompatButton btndelete;

    AppCompatTextView txtregresar;

    RequestQueue requestQueue;

    FragmentTransaction transaccion;

    Fragment fra_consultar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        codigo = findViewById(R.id.codigo_producto);
        producto = findViewById(R.id.nombre_producto);
        precio = findViewById(R.id.precio_producto);
        fabricante = findViewById(R.id.fabricante_producto);

        txtregresar = findViewById(R.id.appCompatTextViewLoginLink);

        etxtcodigo = findViewById(R.id.txtcodigo_producto);
        etxtproducto = findViewById(R.id.txtproducto);
        etxtprecio = findViewById(R.id.txtprecio);
        etxtfabricante = findViewById(R.id.txtfabricante);
        btnconsultar = findViewById(R.id.appCompatButtonConsultar);
        btnupdate=findViewById(R.id.appCompatButtonUpdate);
        btndelete=findViewById(R.id.appCompatButtonDelete);
        btnregistrar = findViewById(R.id.appCompatButtonRegister);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip=getString(R.string.ip);
                Ejecutar_Servicio(ip+"/dbcorporacion_webservices/insertar.php");

            }
        });


        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip=getString(R.string.ip);
                BuscarProducto(ip+"/dbcorporacion_webservices/mostrar.php?codigo=" + etxtcodigo.getText() + "");
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip=getString(R.string.ip);
                Ejecutar_Servicio(ip+"/dbcorporacion_webservices/editar.php");
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip=getString(R.string.ip);
                Eliminar_Producto(ip+"/dbcorporacion_webservices/eliminar.php");
            }
        });



      /*  fra_consultar=new ConsultarFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.consultarfragment,fra_consultar).commit();*/


    }

    //Creando el metodo de peticion para poder guardar
    private void Ejecutar_Servicio(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tu operacion fue exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("codigo", etxtcodigo.getText().toString());
                parametros.put("producto", etxtproducto.getText().toString());
                parametros.put("precio", etxtprecio.getText().toString());
                parametros.put("fabricante", etxtfabricante.getText().toString());

                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    private void BuscarProducto(String URL) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =  null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etxtproducto.setText(jsonObject.getString("producto"));
                        etxtprecio.setText(jsonObject.getString("precio"));
                        etxtfabricante.setText(jsonObject.getString("fabricante"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    private void Eliminar_Producto(String URL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tu producto fue eliminado", Toast.LENGTH_SHORT).show();
                ClearText();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("codigo", etxtcodigo.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void ClearText(){
        etxtcodigo.setText("");
        etxtproducto.setText("");
        etxtprecio.setText("");
        etxtfabricante.setText("");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();

        if(id==R.id.action_settings){
            return true;
        }

        if(id==R.id.action_listar){
            Intent intentRegister = new Intent(getApplicationContext(), CrudProducto.class);
            startActivity(intentRegister);

        }

        if(id==R.id.action_buscar){
            // Intent intentRegister = new Intent(getApplicationContext(),MainActivity.class);
            //  startActivity(intentRegister);

            //   transaccion.replace(R.id.consultarfragment,fra_consultar).commit();


        }

        return super.onOptionsItemSelected(item);
    }




}