package com.example.entrylog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Logentry extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logentry);
    ed1=(EditText) findViewById(R.id.studname);
    ed2=(EditText) findViewById(R.id.admnno);
    ed3=(EditText) findViewById(R.id.deptname);
    ed4=(EditText) findViewById(R.id.sysno);
    b1=(AppCompatButton) findViewById(R.id.addbtn);
    b2=(AppCompatButton) findViewById(R.id.logoutbtn);

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String getName=ed1.getText().toString();
            String getAdNo=ed2.getText().toString();
            String getDeptName=ed3.getText().toString();
            String getSysNo=ed4.getText().toString();

            Toast.makeText(getApplicationContext(),getName+" "+getAdNo+" "+getDeptName+" "+getSysNo,Toast.LENGTH_LONG).show();
        }
    });

    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    });
    }
}