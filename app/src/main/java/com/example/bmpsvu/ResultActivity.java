package com.example.bmpsvu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private SalesAdapter salesAdapter;
    private ListView listView;
    private MyDbClass myDbClass;
    private ArrayList<Sale> arrayListSales;
    private ArrayList<Commission> arrayListComm;
    int userId;
    String smonth,syear,month,year,activity,name;
    CommissionsAdapter commissionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        myDbClass = new MyDbClass(ResultActivity.this);
        arrayListSales = new ArrayList<Sale>();
        arrayListComm = new ArrayList<Commission>();
        TextView title =(TextView)findViewById(R.id.title);
        listView = findViewById(R.id.earchResultLN);
        userId=getIntent().getIntExtra("userId",0);
        smonth=getIntent().getStringExtra("month");
        syear=getIntent().getStringExtra("year");
        activity=getIntent().getStringExtra("activity");
        name=getIntent().getStringExtra("name");
        month = smonth.equals(" ")?" ":" AND month='"+smonth+"' ";
        year=syear.equals(" ")?" ":" AND year='"+syear+"' ";
        if(activity.equals("sale")){
            arrayListSales = myDbClass.MndobSales(userId,month,year);
            salesAdapter = new SalesAdapter(this,arrayListSales);
            listView.setAdapter(salesAdapter);
            title.setText("مبيعات المندوب: "+name);
        }
        else {
            arrayListComm = myDbClass.mndobCommissions(userId,month,year);
            commissionsAdapter = new CommissionsAdapter(this,arrayListComm);
            listView.setAdapter(commissionsAdapter);
            title.setText("عمولة المندوب: "+name);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
       if(activity.equals("sale")){
            intent = new Intent(getApplicationContext(), FindSalesActivity.class);
        }else{
            intent = new Intent(getApplicationContext(), FindCommissionActivity.class);
        }
        startActivity(intent);
        finish();
    }
}