package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.example.myapplication.MyFirebaseMessagingService;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

public class MainActivity extends Activity {

    EditText userid, userpassword;
    TextView signup,login;
    String user_password, user_id;
    String url;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signup = (TextView) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.login);
        userid = (EditText) findViewById(R.id.userid);
        userpassword = (EditText) findViewById(R.id.userpassword);
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        String token = shared.getString( "refreshedToken", "" );
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = userid.getText().toString();
                user_password = userpassword.getText().toString();

                if (user_id.isEmpty() || user_password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "fill details", Toast.LENGTH_SHORT).show();

                }
                else {
                    login(user_id, user_password);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    public void login(final String user, final String pass){
        url = "https://scv0319.cafe24.com/man/login.php?userid="+user+"&userpassword="+pass+"";
        Log.i("Hiteshurl",""+url);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String userid2 = jsonObject1.getString("userid");
                    String userpassword2 = jsonObject1.getString("userpassword");
                    SharedPreferences shared = getSharedPreferences("Mypref",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("userid",userid2);
                    editor.putString("userpassword",userpassword2);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,Nothing2.class);
                    startActivity(intent);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HiteshURLerror",""+error);
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed(){

    }
}
