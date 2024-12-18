package com.example.bmpsvu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MndobComActivity extends AppCompatActivity {
    CardView cardView;
    Button saleButton,commButton;
    MyDbClass myDbClass;
    String empNameString,regionStr,registerDateStr,year,month;
    Spinner empSpinner,spi3,spi2;
    TextView regionTextView;
    EditText northSaleEditText,westSaleEditText,estSaleEditText,southSaleEditText,lebanonSaleEditText;
    ImageView EmpImageIV;
    int UserId=1;
    LinearLayout InsertSalesLV,commissionLV,regis;

    Double northSale,westSale,eastSale,southSale,lebanonSale;

    String northSaleStr,westSaleStr,eastSaleStr,southSaleStr,lebanonSaleStr;
    DecimalFormatSymbols decimalFormatSymbols =new DecimalFormatSymbols(Locale.ENGLISH);
    DecimalFormat decimalFormat=new DecimalFormat("###,###,###",decimalFormatSymbols);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission);
        decimalFormat.setMaximumFractionDigits(2);
        myDbClass =new MyDbClass(getApplicationContext());
        cardView=(CardView) findViewById(R.id.view2);
        northSaleEditText=(EditText) findViewById(R.id.nothID);
        southSaleEditText=(EditText) findViewById(R.id.southID);
        lebanonSaleEditText=(EditText) findViewById(R.id.lebanonID);
        estSaleEditText=(EditText) findViewById(R.id.eastID);
        westSaleEditText=(EditText) findViewById(R.id.westID);
        regis=(LinearLayout) findViewById(R.id.imageRegionLnID);
        regionTextView=(TextView) findViewById(R.id.regionTvID);
        EmpImageIV=(ImageView) findViewById(R.id.advPhotoID);
        empSpinner=(Spinner) findViewById(R.id.chooseAdvSpinnID);
        saleButton=(Button) findViewById(R.id.salescomBTNID);
        commButton=(Button) findViewById(R.id.salecommBtnID2);
        InsertSalesLV=(LinearLayout) findViewById(R.id.salesLnID);
        commissionLV=(LinearLayout) findViewById(R.id.comSallLV);
        spi3=(Spinner) findViewById(R.id.monthyearID);
        spi2=(Spinner) findViewById(R.id.saleYearID);
        ArrayList<String> months = new ArrayList<String>();
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
        ArrayAdapter<String> spinnerAdapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
        spi3.setAdapter(spinnerAdapterMonth);
        spi3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.monthyearID) {
                    month = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayList<String> years = new ArrayList<String>();
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
        ArrayAdapter<String> spinnerAdapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spi2.setAdapter(spinnerAdapterYear);
        spi2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.saleYearID) {
                    year = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        empSpinner.setAdapter(spinnerAdapter);
        empSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.chooseAdvSpinnID) {
                    empNameString = adapterView.getItemAtPosition(i).toString();
                    UserId = hashMap.get(empNameString);
                    if(UserId!=0){
                    registerDateStr= myDbClass.getSingleMndob(UserId).registerDate;
                    regionStr= myDbClass.getSingleMndob(UserId).region;
                    regionTextView.setText(regionStr);
                    regis.setVisibility(View.VISIBLE);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(myDbClass.getSingleMndob(UserId).photo, 0, (myDbClass.getSingleMndob(UserId).photo).length);
                    EmpImageIV.setImageBitmap(bitmap);
                    EmpImageIV.setVisibility(View.VISIBLE);
                    InsertSalesLV.setVisibility(View.VISIBLE);
                        cardView.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                southSaleStr =southSaleEditText.getText().toString();
                westSaleStr=(westSaleEditText.getText().toString());
                northSaleStr=northSaleEditText.getText().toString();
                eastSaleStr=estSaleEditText.getText().toString();
                lebanonSaleStr=lebanonSaleEditText.getText().toString();
                if(UserId==0|| southSaleStr.isEmpty()|| westSaleStr.isEmpty()|| northSaleStr.isEmpty()|| eastSaleStr.isEmpty()|| lebanonSaleStr.isEmpty()){
                    Toast toast=Toast.makeText(MndobComActivity.this, "يجب إدخال", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                southSale=Double.valueOf(southSaleStr);
                westSale=Double.valueOf(westSaleStr);
                northSale=Double.valueOf(northSaleStr);
                eastSale=Double.valueOf(eastSaleStr);
                lebanonSale=Double.valueOf(lebanonSaleStr);
                myDbClass.saveSales(northSale,southSale,westSale,eastSale,lebanonSale,year,month,UserId);
                TextView res1ID=findViewById(R.id.res1ID);
                TextView res2ID=findViewById(R.id.res2ID);
                TextView res3ID=findViewById(R.id.res3ID);
                TextView res4ID=findViewById(R.id.res4ID);
                TextView res5ID=findViewById(R.id.res5ID);
                TextView res6ID=findViewById(R.id.res6ID);
                TextView res7ID=findViewById(R.id.res7ID);
                TextView res8ID=findViewById(R.id.res8ID);
                TextView res9ID=findViewById(R.id.res9ID);
                TextView tv10=findViewById(R.id.res10ID);
                TextView res11ID=findViewById(R.id.res11ID);
                res1ID.setText("رقم المندوب: "+ myDbClass.getSingleMndob(UserId).phone);
                res2ID.setText("اسم المندوب: "+ empNameString);
                res3ID.setText("الشهر: "+ month);
                res4ID.setText("السنة: "+ year);
                res5ID.setText("تاريخ التسجيل: "+ registerDateStr);
                boolean isUserRegionS=regionStr.equals("الجنوبية")?true:false;
                double cs= calculate(southSale,isUserRegionS);
                boolean isUserRegionW=regionStr.equals("الساحلية")?true:false;
                double cw= calculate(westSale,isUserRegionW);
                boolean isUserRegionN=regionStr.equals("الشمالية")?true:false;
                double cn= calculate(northSale,isUserRegionN);
                boolean isUserRegionE=regionStr.equals("الشرقية")?true:false;
                double ce= calculate(eastSale,isUserRegionE);
                boolean isUserRegionL=regionStr.equals("لبنان")?true:false;
                double cl= calculate(lebanonSale,isUserRegionL);
                double allCommession=cl+ce+cw+cn+cs;
                res6ID.setText("المنطقة الجنوبية: "+ decimalFormat.format(cs)+"ل.س");
                res7ID.setText("المنطقة الساحلية: "+ decimalFormat.format(cw)+"ل.س");
                res8ID.setText("المنطقة الشمالية: "+ decimalFormat.format(cn)+"ل.س");
                res9ID.setText("المنطقة الشرقية: "+ decimalFormat.format(ce)+"ل.س");
                tv10.setText("لبنان: "+ decimalFormat.format(cl)+"ل.س");
                res11ID.setText("العمولة الشهرية: "+ decimalFormat.format(allCommession)+"ل.س");
                commButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(myDbClass.isCommission(UserId,month,year)){
                            Dialog dialog = new Dialog(MndobComActivity.this);
                            dialog.setContentView(R.layout.dialog);
                            final Button replaceBtn=(Button) dialog.findViewById(R.id.replaceBtn);
                            final Button cancelBtn=(Button) dialog.findViewById(R.id.cancelBtn);
                            cancelBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }});
                            replaceBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    myDbClass.updateCommission(cn,cs,cw,ce,cl,UserId);
                                    Toast toast=Toast.makeText(MndobComActivity.this, "استبدال", Toast.LENGTH_LONG);
                                    toast.show();
                                    dialog.dismiss();
                                }});
                            dialog.show();
                        }else{
                            myDbClass.InsertCommissions(cn,cs,cw,ce,cl,year,month,UserId);
                            Toast toast=Toast.makeText(MndobComActivity.this, "حفظ", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });

               commissionLV.setVisibility(View.VISIBLE);

            }
        });
    }

    public static double calculate(double sales, boolean isUserRegion) {
        if (sales > 100_000_000) {
            double remain = sales - 100_000_000;
            double per1 = isUserRegion ? 0.05 : 0.03;
            double per2 = isUserRegion ? 0.07 : 0.04;
            return (per1*100_000_000) + (remain * per2);
        } else {
            double  per = isUserRegion ? 0.05 : 0.03;
            return sales * per;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}