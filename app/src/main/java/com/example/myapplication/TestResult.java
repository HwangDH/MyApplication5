package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class TestResult extends Activity {
    SharedPreferences shared;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_BIL = "BIL";
    private static final String TAG_GLU = "GLU";
    private static final String TAG_KET = "KET";
    private static final String TAG_LEU = "LEU";
    private static final String TAG_PH = "PH";
    private static final String TAG_PRO = "PRO";
    private static final String TAG_URO = "URO";

    String BIL, GLU, KET, LEU, PH, PRO, URO;
    String myJSON;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    ListView list;
    TextView txtResult;
    TextView checkdate;
    Button check, graph1, graph2;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        list = (ListView)findViewById(R.id.listView2);
        check = (Button)findViewById(R.id.check);
        checkdate = (TextView)findViewById(R.id.checkdate);
        graph1 = (Button)findViewById( R.id.grapgh1 );
        //graph2 = (Button)findViewById( R.id.grapgh2 );
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        final String user_id = shared.getString("userid", "");
        final String test_check = shared.getString("testcheck", "");
        checkdate.setText(test_check);
        String test_check2 = test_check.replace("-","");
        personList = new ArrayList<HashMap<String, String>>();
        getData("https://scv0319.cafe24.com/man/testResult.php?userid="+user_id+"&testcheck="+test_check2+"");
        txtResult= (TextView)findViewById(R.id.txtResult);


        graph1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(TestResult.this).create();

                alertDialog.setTitle("건강상태 비교 그래프");
                alertDialog.setMessage("건강상태의 변화를 그래프로 보여줍니다.");
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
                        Intent intent = new Intent(TestResult.this, BILGraph.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        } );

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(TestResult.this).create();

                alertDialog.setTitle("다른 검사 날짜");
                alertDialog.setMessage("이전의 검사 결과를 확인하시겠습니까?");
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
                        Intent intent = new Intent(TestResult.this, Otherdate_nothing.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });
    }

    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, PopupActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void showList(){
        SharedPreferences.Editor editor = shared.edit();
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
                HashMap<String, String> persons = new HashMap<>();
                persons.put(TAG_BIL, BIL);
                persons.put(TAG_GLU, GLU);
                persons.put(TAG_KET, KET);
                persons.put(TAG_LEU, LEU);
                persons.put(TAG_PH, PH);
                persons.put(TAG_PRO, PRO);
                persons.put(TAG_URO, URO);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                            TestResult.this, personList, R.layout.test_list,
                            new String[]{TAG_BIL, TAG_GLU, TAG_KET, TAG_LEU, TAG_PH, TAG_PRO, TAG_URO},
                    new int[]{R.id.BIL, R.id.GLU, R.id.KET, R.id.LEU, R.id.PH, R.id.PRO, R.id.URO}
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
}
