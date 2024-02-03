package com.alfin.daftar_pasien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alfin.daftar_pasien.R;
import java.util.ArrayList;
public class Adaptor extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<GetData> model;
    public Adaptor(Context context, ArrayList<GetData> model){
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.model=model;
    }
    @Override
    public int getCount() {
        return model.size();
    }
    @Override
    public Object getItem(int position) {
        return model.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView nama,jk,alamat,keluhan,nomor_handphone;
        View view1=inflater.inflate(R.layout.list_pasien,null);
        if (view1!=null){
            nama=view1.findViewById(R.id.nama);
            jk=view1.findViewById(R.id.jk);
            alamat=view1.findViewById(R.id.alamat);
            keluhan=view1.findViewById(R.id.keluhan);
            nomor_handphone=view1.findViewById(R.id.nomor_handphone);
            nama.setText(model.get(position).getNama());
            jk.setText(model.get(position).getJk());
            alamat.setText(model.get(position).getAlamat());
            keluhan.setText(model.get(position).getKeluhan());
            nomor_handphone.setText(model.get(position).getNomor_handphone());
        }
        return view1;
    }
}