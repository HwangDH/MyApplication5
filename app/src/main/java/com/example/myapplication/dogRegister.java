package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;
import com.example.*;

import org.w3c.dom.Text;

public class dogRegister extends Activity {
    AlertDialog alertDialog;
    EditText dogname, dogage, dogweight, dogbirth, testcheck;
    RadioGroup radioGroup;
    TextView dogregister, doggender, userid;
    SharedPreferences shared;

    String url = "http://scv0319.cafe24.com/man/dogRegister.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_register);
        dogname = (EditText) findViewById(R.id.dogname);
        dogage = (EditText) findViewById(R.id.dogage);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        dogweight = (EditText) findViewById(R.id.dogweight);
        dogbirth = (EditText) findViewById(R.id.dogbirth);
        testcheck = (EditText) findViewById(R.id.testcheck);
        dogregister = (TextView) findViewById(R.id.dogregister);
        doggender = (TextView)findViewById(R.id.doggender);
        userid = (TextView)findViewById(R.id.userid);
        shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.M)
                    doggender.setText("M");
                else
                    doggender.setText("F");
            }
        });

        dogregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int dogid = dogsex.getCheckedRadioButtonId();
                //RadioButton doggender = (RadioButton) findViewById(dogid);
                String dog_name = dogname.getText().toString();
                String dog_age = dogage.getText().toString();
                String dog_gender = doggender.getText().toString();
                String dog_weight = dogweight.getText().toString();
                String dog_birth = dogbirth.getText().toString();
                String test_check = testcheck.getText().toString();
                String user_id = shared.getString("userid", "");
                System.out.println(user_id);
                if (dog_name.isEmpty() || dog_age.isEmpty() || dog_gender.isEmpty() || dog_weight.isEmpty() || dog_birth.isEmpty() || test_check.isEmpty()) {
                    Toast.makeText(dogRegister.this, "빈칸없이 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                if(dog_birth.length()!=8){
                    AlertDialog.Builder builder = new AlertDialog.Builder(dogRegister.this);
                    alertDialog = builder.setMessage("생년월일을 8자리로 입력해주세요.")
                            .setPositiveButton("OK", null)
                            .create();
                    alertDialog.show();
                }
                else {
                    dogregister(user_id, dog_name, dog_age, dog_gender, dog_weight, dog_birth, test_check);
                    SharedPreferences preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.putString("userid", user_id);
                    editor.putString("dogname", dog_name);
                    editor.putString("dogage", dog_age);
                    editor.putString("doggender", dog_gender);
                    editor.putString("dogweight", dog_weight);
                    editor.putString("dogbirth", dog_birth);
                    editor.putString("testcheck", test_check);
                    userid.setText(user_id+"님 환영합니다!");
                    editor.commit();
                    show();
                }
            }
        });
    }

    public void show(){
        Intent intent = new Intent(dogRegister.this,mainpage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void dogregister(final String userid, final String dogname, final String dogage,final String doggender, final String dogweight, final String dogbirth, final String testcheck){

        RequestQueue requestQueue = Volley.newRequestQueue(dogRegister.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh",""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh",""+error);
                Toast.makeText(dogRegister.this, ""+error, Toast.LENGTH_SHORT).show();

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
                stringMap.put("testcheck",testcheck);
                return stringMap;
    }
};
        requestQueue.add(stringRequest);
    }
}