package com.example.myapplication;

import android.app.Activity;
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
import android.widget.RadioGroup;
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

public class dogchange extends Activity {
    //AlertDialog alertDialog;
    EditText dogname, dogage, dogweight, dogbirth, testcheck;
    RadioGroup radioGroup;
    TextView dogchange, cancel, userid, doggender;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogchange);
        userid = (TextView) findViewById(R.id.userid);
        dogname = (EditText) findViewById(R.id.dogname);
        dogage = (EditText) findViewById(R.id.dogage);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        dogweight = (EditText) findViewById(R.id.dogweight);
        dogbirth = (EditText) findViewById(R.id.dogbirth);
        testcheck = (EditText) findViewById(R.id.testcheck);
        dogchange = (TextView) findViewById(R.id.dogchange);
        cancel = (TextView)findViewById(R.id.cancel);
        doggender = (TextView)findViewById(R.id.doggender);
        shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        String user_id = shared.getString("userid", "");
        userid.setText(user_id);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.M)
                    doggender.setText("M");
                else
                    doggender.setText("F");
            }
        });

        dogchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dog_name = dogname.getText().toString();
                String dog_age = dogage.getText().toString();
                String dog_gender = doggender.getText().toString();
                String dog_weight = dogweight.getText().toString();
                String dog_birth = dogbirth.getText().toString();
                //String test_check = testcheck.getText().toString();
                String user_id = userid.getText().toString();
                shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                //editor.putString("test_check", test_check);
                editor.commit();
                if (dog_name.isEmpty() || dog_age.isEmpty() || dog_gender.isEmpty() || dog_weight.isEmpty() || dog_birth.isEmpty()) {
                    Toast.makeText(dogchange.this, "빈칸없이 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    dogchange(user_id, dog_name, dog_age, dog_gender, dog_weight, dog_birth);
                    show();
                }
            }
        });
    }


    public void show(){
        Intent intent = new Intent(dogchange.this,Nothing.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void dogchange(final String userid, final String dogname, final String dogage,final String doggender, final String dogweight, final String dogbirth){
        String url = "https://scv0319.cafe24.com/man/dogchange.php";

        RequestQueue requestQueue = Volley.newRequestQueue(dogchange.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh",""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh",""+error);
                Toast.makeText(dogchange.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userid", userid);
                stringMap.put("dogname",dogname);
                stringMap.put("dogage",dogage);
                stringMap.put("doggender",doggender);
                stringMap.put("dogweight",dogweight);
                stringMap.put("dogbirth",dogbirth);
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
