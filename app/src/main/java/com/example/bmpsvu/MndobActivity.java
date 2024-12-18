package com.example.bmpsvu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MndobActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView listView;
    private MyDbClass myDbClass;
    private ArrayList<Mndob> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mndob);

        myDbClass = new MyDbClass(MndobActivity.this);
        arrayList = new ArrayList<Mndob>();
        arrayList = myDbClass.getMndobs();
        myAdapter = new MyAdapter(this,arrayList);
        listView = findViewById(R.id.empLVId);
        listView.setAdapter(myAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}