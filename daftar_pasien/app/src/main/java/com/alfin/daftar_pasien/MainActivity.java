package com.alfin.daftar_pasien;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.alfin.daftar_pasien.Adapter.Adaptor;
import com.alfin.daftar_pasien.Adapter.GetData;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<GetData> model;
    Adaptor adaptor;

    Button tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        tambah_data=findViewById(R.id.tambah_data);
        tambah_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Simpan_data.class);
                startActivity(intent);
            }
        });

        load_data();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.edit){
                            Intent intent=new Intent(getApplicationContext(), Simpan_data.class);
                            intent.putExtra("edit_data",model.get(i).getId_pasien());
                            startActivity(intent);
                        } else if (menuItem.getItemId()==R.id.hapus) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Yakin Ingin Menghapusnya?");
                            builder.setPositiveButton("ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int position) {
                                    _hapus(model.get(i).getId_pasien());
                                }
                            });
                            builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            AlertDialog alertDialog=builder.create();
                            alertDialog.show();
                        }
                        return false;
                    }
                });
            }
        });
    }
    void load_data(){
        String url=new Configurasi().baseUrl()+"tampil_data.php";
        StringRequest request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            model = new ArrayList<>();
                            for (int i=0; i<=jsonArray.length(); i++){
                                JSONObject getData=jsonArray.getJSONObject(i);
                                model.add(new GetData(
                                        getData.getString("id_pasien"),
                                        getData.getString("nama"),
                                        getData.getString("jk"),
                                        getData.getString("alamat"),
                                        getData.getString("keluhan"),
                                        getData.getString("nomor_handphone")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adaptor = new Adaptor(getApplicationContext(),model);
                        listView.setAdapter(adaptor);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

//    String getInisial(String nama){
//        int i=nama.length();
//        String namax= String.valueOf(nama.charAt(0));
//        return namax;
//    }

    void _hapus(String id_pasien){
        String url=new Configurasi().baseUrl()+"hapus.php";
        StringRequest request=new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equals("data_berhasil_di_hapus")){
                                Toast.makeText(MainActivity.this, "Data Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
                                load_data();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> form=new HashMap<String,String >();
                form.put("id_pasien",id_pasien);
                return form;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    @Override
    protected void onResume() {
        load_data();
        super.onResume();
    }
}