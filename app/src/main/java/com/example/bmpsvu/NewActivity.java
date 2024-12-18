package com.example.bmpsvu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {
    MyDbClass myDbClass;
    String region;
    String photoPath;
    private EditText phoneEt;
    private EditText nameEt;
    private Button chhoseImgBtn;
    private Button addButton;
    private RadioGroup radioGroup;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDbClass =new MyDbClass(getApplicationContext());
        setContentView(R.layout.activity_new);
        chhoseImgBtn =   (Button) findViewById(R.id.chooseImageID);
        phoneEt =   (EditText) findViewById(R.id.addPhoneID);
        nameEt =   (EditText) findViewById(R.id.addnameID);
        addButton=(Button) findViewById(R.id.addBtnID);
        Spinner regSpin =(Spinner) findViewById(R.id.regSpin);
        ArrayList<String> regionListSpinner = new ArrayList<String>();
        regionListSpinner.add("المنطقة");
        regionListSpinner.add("الجنوبية");
        regionListSpinner.add("الشمالية");
        regionListSpinner.add("الشرقية");
        regionListSpinner.add("الساحلية");
        regionListSpinner.add("لبنان");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, regionListSpinner);
        regSpin.setAdapter(spinnerAdapter);
        regSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getId() == R.id.regSpin) {
                    region = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        chhoseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectEmpImage();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String numETText = phoneEt.getText().toString();
                String nameETText = nameEt.getText().toString();


                if (regSpin.equals("المنطقة")||nameETText.isEmpty()||numETText.isEmpty()||photoPath.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "يجب ملء الحقول", Toast.LENGTH_SHORT).show();
                    return;
                }

                

                Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                myDbClass.newmndob(numETText,nameETText,byteArray,region, (LocalDate.now()).toString());
                Toast.makeText(NewActivity.this, "تمت الإضافة", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewActivity.this, MndobActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void selectEmpImage() {
        Intent photoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(photoIntent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                photoPath = cursor.getString(columnIndex);
                cursor.close();
            }
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