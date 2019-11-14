package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class PastData extends Activity {
    SharedPreferences shared;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_BIL = "BIL";
    private static final String TAG_GLU = "GLU";
    private static final String TAG_KET = "KET";
    private static final String TAG_LEU = "LEU";
    private static final String TAG_PH = "PH";
    private static final String TAG_PRO = "PRO";
    private static final String TAG_URO = "URO";

    String myJSON;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    String BIL, GLU, KET, LEU, PH, PRO, URO;
    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        final String user_id = shared.getString("userid","");
        final String test_check3 = shared.getString("test_check3","");
        String url = "https://scv0319.cafe24.com/man/testResult.php?userid="+user_id+"&testcheck="+test_check3+"";
        getData(url);
        show();
    }


    protected void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i = 0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                BIL = c.getString(TAG_BIL);
                GLU = c.getString(TAG_GLU);
                KET = c.getString(TAG_KET);
                LEU = c.getString(TAG_LEU);
                PH = c.getString(TAG_PH);
                PRO = c.getString(TAG_PRO);
                URO = c.getString(TAG_URO);
                SharedPreferences.Editor editor = shared.edit();
                if(BIL.equals( "10")){
                    BIL = "검사오류";
                    editor.putString("BIL","검사오류");
                }
                else{
                    editor.putString("BIL",BIL);
                }

                if(GLU.equals( "10")){
                    GLU = "검사오류";
                    editor.putString("GLU","검사오류");
                }
                else{
                    editor.putString("GLU",GLU);
                }

                if(KET.equals( "10")){
                    KET = "검사오류";
                    editor.putString("KET","검사오류");
                }
                else{
                    editor.putString("KET",KET);
                }

                if(LEU.equals( "10")){
                    LEU = "검사오류";
                    editor.putString("LEU","검사오류");
                }
                else{
                    editor.putString("LEU",LEU);
                }

                if(PH.equals( "10")){
                    PH = "검사오류";
                    editor.putString("PH","검사오류");
                    System.out.println(PH);
                }
                else{
                    editor.putString("PH",PH);
                }

                if(PRO.equals( "10")){
                    PRO = "검사오류";
                    editor.putString("PRO","검사오류");
                }
                else{
                    editor.putString("PRO",PRO);
                }

                if(URO.equals( "10")){
                    URO = "검사오류";
                    editor.putString("URO","검사오류");
                }
                else{
                    editor.putString("URO",URO);
                }
                editor.commit();
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void show(){
        Intent intent = new Intent(PastData.this,Otherdate.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
