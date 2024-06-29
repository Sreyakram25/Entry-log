package com.example.entrylog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Logentry extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    AppCompatButton b1, b2;
    String apiUrl = "http://10.0.4.16:3000/api/students";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logentry);

        ed1 =(EditText) findViewById(R.id.studname);
        ed2 =(EditText) findViewById(R.id.admnno);
        ed4 = (EditText)findViewById(R.id.deptname);
        ed3 = (EditText)findViewById(R.id.sysno);
        b1 = (AppCompatButton) findViewById(R.id.addbtn);
        b2 =(AppCompatButton) findViewById(R.id.logoutbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = ed1.getText().toString();
                String getAdNo = ed2.getText().toString();
                String getDeptName = ed4.getText().toString();
                String getSysNo = ed3.getText().toString();

                // JSON OBJECT CREATION
                JSONObject student = new JSONObject();
                try {
                    student.put("name", getName);
                    student.put("admission_number", getAdNo);
                    student.put("system_number", getSysNo);
                    student.put("department", getDeptName);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                // JSON OBJECT REQUEST creation
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, apiUrl, student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                // request queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefer = getSharedPreferences("entrylog", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefer.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}