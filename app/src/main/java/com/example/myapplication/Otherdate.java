package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;

public class Otherdate extends Activity {
    SharedPreferences shared;
    private Spinner spinner;
    ListView list;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    String myJSON;
    JSONArray peoples = null;
    TextView txtResult2;
    Button checking;
    ArrayList<HashMap<String, String>> personList;
    String [] weeks = new String[7];
    int count = 0;
    TextView checkingbox;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_BIL = "BIL";
    private static final String TAG_GLU = "GLU";
    private static final String TAG_KET = "KET";
    private static final String TAG_LEU = "LEU";
    private static final String TAG_PH = "PH";
    private static final String TAG_PRO = "PRO";
    private static final String TAG_URO = "URO";

    String BIL, GLU, KET, LEU, PH, PRO, URO;
    String test_check2;
    String test_check3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherdate);
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        final String user_id = shared.getString("userid","");
        final int length = shared.getInt("counts", 0);
        list= (ListView)findViewById(R.id.listView3);
        checking = (Button)findViewById(R.id.checking);
        personList = new ArrayList<HashMap<String, String>>();
        checkingbox = (TextView)findViewById(R.id.checkingbox);
        arrayList = new ArrayList<>();
        for(int i = length-1; i>=0; i--){
            weeks[i] =shared.getString("weeks"+i, "");
            arrayList.add(weeks[i]);
        }

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setGravity(Gravity.CENTER);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                test_check2 = arrayList.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), test_check2+"가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                test_check3 = test_check2.replace("-","");
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("test_check3",test_check3);
                editor.commit();
                show();
            }
        });
        showList();
        final String test_check4 = shared.getString("test_check3","");
        checkingbox.setText(test_check4);
        txtResult2= (TextView)findViewById(R.id.txtResult2);
    }

    public void show(){
        Intent intent = new Intent(Otherdate.this,PastData.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, PopupActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void showList(){
        shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        BIL = shared.getString("BIL", "");
        GLU = shared.getString("GLU", "");
        KET = shared.getString("KET", "");
        LEU = shared.getString("LEU", "");
        PH = shared.getString("PH", "");
        System.out.println(PH+"As");
        PRO = shared.getString("PRO", "");
        URO = shared.getString("URO", "");

        System.out.println(BIL);

        HashMap<String, String> persons = new HashMap<>();

        persons.put(TAG_BIL, BIL);
        persons.put(TAG_GLU, GLU);
        persons.put(TAG_KET, KET);
        persons.put(TAG_LEU, LEU);
        persons.put(TAG_PH, PH);
        persons.put(TAG_PRO, PRO);
        persons.put(TAG_URO, URO);

        personList.add(persons);

        ListAdapter adapter = new SimpleAdapter(
                Otherdate.this, personList, R.layout.past_test_list,
                new String[]{TAG_BIL, TAG_GLU, TAG_KET, TAG_LEU, TAG_PH, TAG_PRO, TAG_URO},
                new int[]{R.id.BIL, R.id.GLU, R.id.KET, R.id.LEU, R.id.PH, R.id.PRO, R.id.URO}
                );

        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Otherdate.this, TestResult.class);
        startActivity(intent);
    }

}
