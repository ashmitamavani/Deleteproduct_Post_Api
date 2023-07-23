package com.example.deleteproduct_post_api;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        String Api="https://amiparaandroid.000webhostapp.com/Myapp/deleteproduct.php";

        StringRequest request=new StringRequest(Request.Method.POST, Api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            int connection=object.getInt("connection");
                            int result=object.getInt("result");
                            if (connection==1&&result==1)
                            {
                                Toast.makeText(MainActivity.this, "successfully delete", Toast.LENGTH_SHORT).show();
                            } else if (result==2) {
                                Toast.makeText(MainActivity.this, "already delete", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>params=new HashMap<String,String>();

               params.put("id","88");
                return params;
            }


        };
        queue.add(request);

    }
}