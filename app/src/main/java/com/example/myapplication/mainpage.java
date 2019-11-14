package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

//import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mainpage extends Activity {
    SharedPreferences shared;
    String userid2;
    AlertDialog alertDialog;
    TextView testresult, userchange, dogchange, logout;
    TextView changecheckdate;
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_DOGNAME = "dogname";
    private static final String TAG_DOGAGE = "dogage";
    private static final String TAG_DOGGENDER = "doggender";
    private static final String TAG_TESTCHECK = "testcheck";
    String dogname, dogage, doggender, testcheck;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    ListView list;

    Button graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        testresult = (TextView) findViewById(R.id.testresult);
        userchange = (TextView) findViewById(R.id.userchange);
        dogchange = (TextView) findViewById(R.id.dogchange);
        logout = (TextView) findViewById(R.id.logout);
        changecheckdate = (TextView)findViewById(R.id.changecheckdate);
        list = (ListView)findViewById(R.id.listView);
       //SS graph = (Button)findViewById( R.id.graph );
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        final String user_id = shared.getString("userid", "");
        final String user_pw = shared.getString("userpassword", "");

        //System.out.println(user_id);
        personList = new ArrayList<HashMap<String, String>>();
        getData("https://scv0319.cafe24.com/man/dogprofile2.php?userid="+user_id+"");

        userid2 = user_id;

        //graph.setOn

        userchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(mainpage.this).create();

                alertDialog.setTitle("회원정보 수정");
                alertDialog.setMessage("회원정보를 수정하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = shared.edit();
                        editor.clear();
                        editor.putString("userid",user_id);
                        editor.putString("userpassword",user_pw);
                        editor.putString("testcheck", testcheck);
                        editor.commit();
                        Intent intent = new Intent(mainpage.this, userchange.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });



        dogchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(mainpage.this).create();

                alertDialog.setTitle("반려견 정보 수정");
                alertDialog.setMessage("반려견 정보를 수정하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = shared.edit();
                        editor.clear();
                        editor.putString("userid",user_id);
                        editor.putString("testcheck", testcheck);
                        editor.commit();
                        Intent intent = new Intent(mainpage.this, dogchange.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        });



        changecheckdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(mainpage.this).create();

                alertDialog.setTitle("소변검사 날짜 수정");
                alertDialog.setMessage("소변검사 날짜를 수정하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mainpage.this, changecheckdate.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        });





        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(mainpage.this).create();

                alertDialog.setTitle("Logout");
                alertDialog.setMessage("Are you sure ! logout ?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = shared.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(mainpage.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        });


        testresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(mainpage.this).create();

                alertDialog.setTitle("테스트 결과 확인");
                alertDialog.setMessage("테스트 결과를 확인하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = shared.edit();
                        editor.clear();
                        editor.putString("userid",user_id);
                        editor.putString("testcheck", testcheck);
                        editor.commit();
                        Intent intent = new Intent(mainpage.this, TestResult.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        });
    }

    public void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            System.out.println(peoples.length());
            for(int i = 0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                dogname = c.getString(TAG_DOGNAME);
                dogage = c.getString(TAG_DOGAGE);
                doggender = c.getString(TAG_DOGGENDER);
                testcheck = c.getString(TAG_TESTCHECK);

                HashMap<String, String> persons = new HashMap<>();

                persons.put(TAG_DOGNAME, dogname);
                persons.put(TAG_DOGAGE, dogage);
                persons.put(TAG_DOGGENDER, doggender);
                persons.put(TAG_TESTCHECK, testcheck);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    mainpage.this, personList, R.layout.list_item,
                    new String[]{TAG_DOGNAME, TAG_DOGAGE, TAG_DOGGENDER, TAG_TESTCHECK},
                    new int[]{R.id.dogname, R.id.dogage, R.id.doggender, R.id.testcheck}
            );

            list.setAdapter(adapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    @Override
    public void onBackPressed(){

    }
}