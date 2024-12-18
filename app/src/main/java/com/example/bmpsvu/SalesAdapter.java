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

public class SalesAdapter extends ArrayAdapter<Sale> {

    DecimalFormatSymbols decimalFormatSymbols =new DecimalFormatSymbols(Locale.ENGLISH);
    DecimalFormat decimalFormat=new DecimalFormat("###,###,###",decimalFormatSymbols);
    public SalesAdapter(@NonNull Context context, ArrayList<Sale> arrayList) {
        super(context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.sale_item, parent, false);
        }


        Sale modal = getItem(position);
        TextView tvms = listitemView.findViewById(R.id.tvms);
        TextView tvss = listitemView.findViewById(R.id.tvss);
        TextView tvns = listitemView.findViewById(R.id.tvns);
        TextView tvws = listitemView.findViewById(R.id.tvws);

        TextView tves=listitemView.findViewById(R.id.tves);
        TextView tvls=listitemView.findViewById(R.id.tvls);
        tvms.setText(modal.month+" "+ modal.year);

            tvns.setText("الشمالية: "+String.valueOf(decimalFormat.format(modal.north))+" ل.س");
            tvss.setText("الجنوبية: "+String.valueOf(decimalFormat.format(modal.south))+" ل.س");
            tvws.setText("الساحلية: "+String.valueOf(decimalFormat.format(modal.west))+" ل.س");
            tves.setText("الشرقية: "+String.valueOf(decimalFormat.format(modal.east))+" ل.س");
            tvls.setText("لبنان: "+String.valueOf(decimalFormat.format(modal.lebanon))+" ل.س");
        return listitemView;
    }



}
