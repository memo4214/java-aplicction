package com.example.bmpsvu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Mndob> {


    public MyAdapter(@NonNull Context context, ArrayList<Mndob> arrayList) {
        super(context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.mndob_item, parent, false);
        }


        Mndob modal = getItem(position);
        CardView cardView=(CardView) listitemView.findViewById(R.id.cardvRep);
        TextView rnumid = listitemView.findViewById(R.id.rnumid);
        TextView rnameid = listitemView.findViewById(R.id.rnameid);
        ImageView clientImage = listitemView.findViewById(R.id.repImageId);
        TextView rregionid = listitemView.findViewById(R.id.rregionid);
        ImageView imageView=(ImageView)listitemView.findViewById(R.id.rdeletId);

        ImageView imageView1=(ImageView)listitemView.findViewById(R.id.rEditId);


        rnumid.setText(modal.phone);
        rnameid.setText(modal.name);
        rregionid.setText(modal.region);


        Bitmap bitmap = BitmapFactory.decodeByteArray(modal.photo, 0, (modal.photo).length);
        clientImage.setImageBitmap(bitmap);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), EditActivity.class);
                intent.putExtra("userId",modal.id);
                getContext().startActivity(intent);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MyDbClass myDbClass =new MyDbClass(getContext());
                boolean success = myDbClass.deletemndob(modal.id,"mndob");
                if(success){
                    Toast.makeText(getContext(), "تمت الإزالة", Toast.LENGTH_SHORT).show();
                    cardView.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getContext(), "لم تنجح الإزالة", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return listitemView;
    }



}
