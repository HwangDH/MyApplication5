package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BILGraph extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgraph);

        Button button = (Button) findViewById(R.id.GluActivity);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GLUGraph.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.UroActivity);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestResult.class);
                startActivity(intent);
            }
        });
        // json 연동
        String test = "https://scv0319.cafe24.com/man/getBIL.php";
        URLConnector task = new URLConnector(test);

        ArrayList<String> xAXES = new ArrayList<>();
        ArrayList<Entry> yAXES = new ArrayList<>();

        task.start();

        try {
            task.join();
            System.out.println("waiting... for result");
        } catch (InterruptedException e) {

        }

        String result = task.getResult();

        try {
            JSONObject root = new JSONObject(result);
            JSONArray ja = root.getJSONArray("webnautes");

            for (int i = 0; i < ja.length(); i++) {
                JSONObject week = ja.getJSONObject(i);
                String weekend = week.getString("weekend");
                int bil = week.getInt("BIL");
                yAXES.add(new Entry(bil, i));
                xAXES.add(i, weekend); //x축의 값을 저장합니다.
            }
        }  catch(JSONException e){
            e.printStackTrace();
        }
        String[] xaxes = new String[xAXES.size()];
        for(int i=0; i < xAXES.size(); i++){
            xaxes[i] = xAXES.get(i).toString(); // 아래그림의 동그란 부분에 표시되는 x축 값.
        }
        // 그래프
        lineChart = findViewById(R.id.chart);
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();

//아래 그림의 파란색 그래프
        LineDataSet lineDataSet = new LineDataSet(yAXES, "BIL");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(Color.BLACK);
        lineDataSets.add(lineDataSet);
        lineDataSet.setLineWidth(3);

        XAxis xAxis = lineChart.getXAxis(); // x 축 설정
        xAxis.setDrawLabels(true); //X축의 데이터를 최대 몇개 까지 나타낼지에 대한 설정 5개 force가 true 이면 반드시 보여줌
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x 축 표시에 대한 위치 설정

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMaxValue(6);
        yAxis.setAxisMinValue(0);

        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.getXAxis().setSpaceBetweenLabels(1);
        lineChart.setData(new LineData(xaxes,lineDataSets));
        lineChart.setDescription(null);
        lineChart.setVisibleXRangeMaximum(65f);
    }
}