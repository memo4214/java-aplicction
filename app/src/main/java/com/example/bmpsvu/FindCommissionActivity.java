package com.example.bmpsvu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class FindCommissionActivity extends AppCompatActivity {
    Spinner repSpinner;
    Spinner monthSpinner;
    Spinner yearSpinner;
    Button finfButton;

    MyDbClass myDbClass;
    String year,month,name;
    int userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_commission);
        myDbClass =new MyDbClass(getApplicationContext());
        repSpinner=(Spinner) findViewById(R.id.fCommSpinn);
        monthSpinner=(Spinner) findViewById(R.id.fCommSpinMonth);
        yearSpinner=(Spinner) findViewById(R.id.fCommSpinYear);
        finfButton=(Button) findViewById(R.id.fCommButton);


        HashMap<String, Integer> representativeHashMap = new HashMap<String, Integer>();
        ArrayList<String> representatives = new ArrayList<String>();
        representatives.add(" ");
        representativeHashMap.put(" ", 0);
        for (Mndob mndob : myDbClass.getMndobs()
        ) {
            representatives.add(mndob.name);
            representativeHashMap.put(mndob.name, mndob.id);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, representatives);
        repSpinner.setAdapter(spinnerAdapter);
        repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.fCommSpinn) {
                    name = adapterView.getItemAtPosition(i).toString();
                    userId = representativeHashMap.get(name);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> years = new ArrayList<String>();
        years.add(" ");
        years.add(String.valueOf("2024"));
        years.add(String.valueOf("2023"));
        years.add(String.valueOf("2022"));
        years.add(String.valueOf("2021"));
        years.add(String.valueOf("2020"));
        years.add(String.valueOf("2019"));
        years.add(String.valueOf("2018"));
        years.add(String.valueOf("2017"));
        years.add(String.valueOf("2016"));
        years.add(String.valueOf("2015"));
        ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearSpinner.setAdapter(spinnerAdapter1);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.fCommSpinYear) {
                    year = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayList<String> monthList = new ArrayList<String>();
        monthList.add(" ");
        monthList.add("كانون الثاني");
        monthList.add("شباط");
        monthList.add("آذار");
        monthList.add("نيسان");
        monthList.add("آيار");
        monthList.add("حزيران");
        monthList.add("تموز");
        monthList.add("آب");
        monthList.add("أيلول");
        monthList.add("تشرين الأول");
        monthList.add("تشرين الثاني");
        monthList.add("كانون الأول");
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, monthList);
        monthSpinner.setAdapter(spinnerAdapter2);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.fCommSpinMonth) {
                    month = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        finfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userId==0){
                    Toast.makeText(FindCommissionActivity.this, "حدد مندوب", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(FindCommissionActivity.this, ResultActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("month",month);
                intent.putExtra("year",year);
                intent.putExtra("name",name);
                intent.putExtra( "activity","commission");
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}