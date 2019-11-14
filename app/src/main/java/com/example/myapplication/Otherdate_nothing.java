package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Otherdate_nothing extends Activity {
    SharedPreferences shared;
    private Spinner spinner;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    String myJSON;
    JSONArray peoples = null;
    String [] weeks = new String[7];
    ArrayList<HashMap<String, String>> personList;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_WEEKEND = "weekended";
    String weekended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherdate_nothing);

        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);

        final String user_id = shared.getString("userid","");
        SharedPreferences.Editor editor = shared.edit();
        System.out.println(user_id);
        personList = new ArrayList<HashMap<String, String>>();
        getData("https://scv0319.cafe24.com/man/pastdata.php?userid="+user_id+"");
    }

    public void show(){
        Intent intent = new Intent(Otherdate_nothing.this,Otherdate.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i = 0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                weekended = c.getString(TAG_WEEKEND);
                weeks[i] = weekended;
                HashMap<String, String> persons = new HashMap<>();
                persons.put(TAG_WEEKEND, weekended);
                personList.add(persons);
            }

            SharedPreferences.Editor editor = shared.edit();
            for(int j =0; j<peoples.length(); j++)
            editor.putString("weeks"+j,weeks[j]);
            editor.putInt("counts",peoples.length());
            editor.commit();
            show();
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
}
