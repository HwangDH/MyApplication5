package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class changecheckdate extends Activity {

    TextView userid;

    TextView changecheckdate;
    SharedPreferences shared;
    TextView changecheckdate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changecheckdate);

        userid = (TextView)findViewById(R.id.userid);
        changecheckdate= (TextView) findViewById(R.id.changecheckdate);
        changecheckdate2 = (TextView)findViewById( R.id.changecheckdate2);
        shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        final String user_id = shared.getString("userid", "");
        userid.setText(user_id);

        changecheckdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String change_checkdate = changecheckdate2.getText().toString();
                String user__id = userid.getText().toString();

                if(change_checkdate.isEmpty()){
                    Toast.makeText(changecheckdate.this, "검사날짜를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    changecheckdate(user__id, change_checkdate);
                    show();
                }
            }
        });
    }

    public void show(){
        Intent intent = new Intent(changecheckdate.this,Nothing.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void changecheckdate(final String userid, final String changecheckdate){
        String url = "https://scv0319.cafe24.com/man/changecheckdate.php";

        RequestQueue requestQueue = Volley.newRequestQueue(changecheckdate.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh",""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh",""+error);
                Toast.makeText(changecheckdate.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userid", userid);
                stringMap.put("changecheckdate", changecheckdate);
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
