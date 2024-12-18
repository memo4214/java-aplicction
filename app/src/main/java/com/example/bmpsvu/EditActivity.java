package com.example.bmpsvu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditActivity extends AppCompatActivity {
  
    MyDbClass myDbClass;
    Mndob mndob;
    private static final int PICK_IMAGE_REQUEST = 1;
    EditText nameEDt,phoneEdt;
    Button editButton;
    Button myGalleryButton;
    String imagePath;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        myDbClass = new MyDbClass(EditActivity.this);

        userID=getIntent().getIntExtra("userId",0);
        mndob = myDbClass.getSingleMndob(userID);
        nameEDt=(EditText) findViewById(R.id.editnameID);
        phoneEdt=(EditText) findViewById(R.id.editPhoneID);
        editButton=(Button) findViewById(R.id.editIDBtn);
        myGalleryButton=(Button) findViewById(R.id.chooseIngIDEdt);
        nameEDt.setText(mndob.name);
        phoneEdt.setText(mndob.phone);

        myGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myGallery();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = nameEDt.getText().toString();
                String phoneStr = phoneEdt.getText().toString();
                if(nameStr.isEmpty()||phoneStr.isEmpty()){
                    Toast.makeText(getApplicationContext(),"الحقول مطلوبة الإدخال",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (imagePath!=null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    mndob.photo = byteArray;
                }

                MyDbClass sqliteDBM = new MyDbClass(EditActivity.this);


                boolean success = sqliteDBM.editmndob(userID, phoneStr, nameStr, mndob.photo);


                if (success) {
                    Toast.makeText(EditActivity.this, "تم تعديل", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(EditActivity.this, "فشل التعديل", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void myGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
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
                imagePath = cursor.getString(columnIndex);
                cursor.close();


            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MndobActivity.class);
        startActivity(intent);
        finish();
    }
}