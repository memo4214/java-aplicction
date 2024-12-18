package com.example.bmpsvu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class CommissionsAdapter extends ArrayAdapter<Commission> {
    DecimalFormatSymbols decimalFormatSymbols =new DecimalFormatSymbols(Locale.ENGLISH);
    DecimalFormat decimalFormat=new DecimalFormat("###,###,###",decimalFormatSymbols);
    public CommissionsAdapter(@NonNull Context context, ArrayList<Commission> arrayList) {
        super(context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.commission_item, parent, false);
        }


        Commission modal = getItem(position);

        TextView tv444=listitemView.findViewById(R.id.tv444);
        TextView tv555=listitemView.findViewById(R.id.tv555);
        TextView tv666 = listitemView.findViewById(R.id.tv666);
        TextView tv333 = listitemView.findViewById(R.id.tv333);
        TextView tv111 = listitemView.findViewById(R.id.tv111);
        TextView tv222 = listitemView.findViewById(R.id.tv222);
        tv222.setText("الساحلية: "+String.valueOf(decimalFormat.format(modal.west))+" ل.س");
        tv444.setText("الشرقية: "+String.valueOf(decimalFormat.format(modal.east))+" ل.س");
        tv555.setText("لبنان: "+String.valueOf(decimalFormat.format(modal.lebanon))+" ل.س");
        tv666.setText(modal.month + " "+modal.year);
        tv111.setText("الشمالية: "+String.valueOf(decimalFormat.format(modal.north))+" ل.س");
        tv333.setText("الجنوبية: "+String.valueOf(decimalFormat.format(modal.south))+" ل.س");

        return listitemView;
    }



}

