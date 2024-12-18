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

public class FindSalesActivity extends AppCompatActivity {
    Spinner fRepSpin;
    Spinner monthSpinner;
    Spinner yearSpinner;
    Button findSaleBTN;

    MyDbClass myDbClass;
    String year,month,name;
    int userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_sales);
        myDbClass =new MyDbClass(getApplicationContext());
        fRepSpin=(Spinner) findViewById(R.id.fRepSpin);
        monthSpinner=(Spinner) findViewById(R.id.fmonthSpin);
        yearSpinner=(Spinner) findViewById(R.id.fYearSpin);
        findSaleBTN=(Button) findViewById(R.id.fBtnSale);


        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        ArrayList<String> representatives = new ArrayList<String>();
        representatives.add(" ");
        hashMap.put(" ", 0);
        for (Mndob mndob : myDbClass.getMndobs()
        ) {
            representatives.add(mndob.name);
            hashMap.put(mndob.name, mndob.id);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, representatives);
        fRepSpin.setAdapter(spinnerAdapter);
        fRepSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.fRepSpin) {
                    name = adapterView.getItemAtPosition(i).toString();
                    userId = hashMap.get(name);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayList<String> years = new ArrayList<String>();
        years.add(String.valueOf(" "));
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
                if (adapterView.getId() == R.id.fYearSpin) {
                    year = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayList<String> months = new ArrayList<String>();
        months.add(" ");
        months.add("كانون الثاني");
        months.add("شباط");
        months.add("آذار");
        months.add("نيسان");
        months.add("آيار");
        months.add("حزيران");
        months.add("تموز");
        months.add("آب");
        months.add("أيلول");
        months.add("تشرين الأول");
        months.add("تشرين الثاني");
        months.add("كانون الأول");
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
        monthSpinner.setAdapter(spinnerAdapter2);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.fmonthSpin) {
                    month = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findSaleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userId==0){
                    Toast.makeText(FindSalesActivity.this, "حدد مندوب", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(FindSalesActivity.this, ResultActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("month",month);
                intent.putExtra("year",year);
                intent.putExtra("name",name);
                intent.putExtra( "activity","sale");
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