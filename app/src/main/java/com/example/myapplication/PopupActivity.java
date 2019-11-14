package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class PopupActivity extends Activity {
    SharedPreferences shared;
    TextView txtText;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_BIL = "BIL_result";
    String BIL_result;
    String myJSON;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    String BIL_result2="";
    String BIL, GLU, KET, LEU, PH, PRO, URO;
    int [] count = new int [] {0,0,0,0,0,0,0};
    String BIL_Text, GLU_Text, KET_Text, LEU_Text, PH_Text, PRO_Text, URO_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
         BIL = shared.getString("BIL", "");
         GLU = shared.getString("GLU", "");
         KET = shared.getString("KET", "");
         LEU = shared.getString("LEU", "");
         PH = shared.getString("PH", "");
         PRO = shared.getString("PRO", "");
         URO = shared.getString("URO", "");
         if(BIL.equals( "검사오류" )){
             BIL = "0";
             BIL_result2 += "BIL: 검사 오류"+"\n";
         }
        if(GLU.equals( "검사오류" )){
            GLU = "0";
            BIL_result2 += "GLU: 검사 오류"+"\n";

        }
        if(KET.equals( "검사오류" )){
            KET = "0";
            BIL_result2 += "KET: 검사 오류"+"\n";

        }
        if(LEU.equals( "검사오류" )){
            LEU = "0";
            BIL_result2 += "LEU: 검사 오류"+"\n";

        }
        if(PH.equals( "검사오류" )){
            PH = "0";
            BIL_result2 += "PH: 검사 오류"+"\n";
            System.out.println(BIL_result2);
        }
        if(PRO.equals( "검사오류" )){
            PRO = "0";
            BIL_result2 += "PRO: 검사 오류"+"\n";

        }
        if(URO.equals( "검사오류" )){
            URO = "0";
            BIL_result2 += "BIL: 검사 오류"+"\n";
        }

        //System.out.println(BIL+GLU+KET+LEU+PH+PRO+URO);
        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);
        personList = new ArrayList<HashMap<String, String>>();
        //데이터 가져오기
        getData("https://scv0319.cafe24.com/man/health_result.php?BIL="+BIL+"&GLU="+GLU+"&KET="+KET+"&LEU="+LEU+"&PH="+PH+"&PRO="+PRO+"&URO="+URO+"");

    }


    public void showList(){

        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i = 0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                BIL_result = c.getString(TAG_BIL);
                BIL_result2 +=BIL_result+"\n";
                System.out.println(BIL_result2);
            }

            HashMap<String, String> persons = new HashMap<>();
            persons.put(TAG_BIL, BIL_result2);
            personList.add(persons);
            txtText.setText(BIL_result2);
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

    public void mOnClose(View v){
            finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
