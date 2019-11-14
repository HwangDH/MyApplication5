package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class userchange extends Activity {
    private RequestQueue requestQueue;
    StringRequest stringRequest;
    SharedPreferences shared;
    TextView userid, userchange, cancel;
    EditText userpassword, username, userage;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchange);

        userid = (TextView) findViewById(R.id.userid);
        userpassword = (EditText) findViewById(R.id.userpassword);
        username = (EditText) findViewById(R.id.username);
        userchange = (TextView)findViewById(R.id.userchange);
        cancel = (TextView)findViewById(R.id.cancel);
        userage = (EditText) findViewById(R.id.userage);
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        String user_id = shared.getString("userid", "");
        userid.setText(user_id);


        userchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id = userid.getText().toString();
                final String user_pw= userpassword.getText().toString();
                final String user_name = username.getText().toString();
                final String user_age = userage.getText().toString();

                if(user_pw.isEmpty() || user_age.isEmpty() || user_name.isEmpty()){
                    Toast.makeText(userchange.this, "빈칸없이 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    userchange(user_id, user_pw, user_name, user_age);
                    show();
                }
            }
        });


    }

    public void show(){
        Intent intent = new Intent(userchange.this,mainpage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void userchange(final String userid, final String userpassword, final String username, final String userage){
        String url = "https://scv0319.cafe24.com/man/userchange.php";
        requestQueue = Volley.newRequestQueue(userchange.this);
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh",""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh",""+error);
                Toast.makeText(userchange.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userid", userid);
                stringMap.put("userpassword", userpassword);
                stringMap.put("username",username);
                stringMap.put("userage",userage);

                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}

