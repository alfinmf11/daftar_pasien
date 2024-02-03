package com.alfin.daftar_pasien;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class Simpan_data extends AppCompatActivity {
    Toolbar toolbar;
    Button simpan_data;
    TextView label;
    TextInputEditText nama,jk,alamat,keluhan,nomor_handphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_data);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        label=findViewById(R.id.label);
        simpan_data=findViewById(R.id.simpan_data);
        if (getIntent().hasExtra("edit_data")){
            label.setText("Edit Data");
            getData();
            simpan_data.setText("Update Data");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        nama=findViewById(R.id.nama);
        jk=findViewById(R.id.jk);
        alamat=findViewById(R.id.alamat);
        keluhan=findViewById(R.id.keluhan);
        nomor_handphone=findViewById(R.id.nomor_handphone);
        simpan_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().length()==0){
                    nama.setError("Nama tdk blh ksg");
                }
                if (jk.getText().toString().length()==0){
                    jk.setError("JK tdk blh ksg");
                }
                if (alamat.getText().toString().length()==0){
                    alamat.setError("Alamat tdk blh ksg");
                }
                if (keluhan.getText().toString().length()==0){
                    keluhan.setError("Keluhan tdk blh ksg");
                }
                if (nomor_handphone.getText().toString().length()==0){
                    nomor_handphone.setError("No HP tdk blh ksg");
                } else {
                    String url = new Configurasi().baseUrl()+"simpan.php";
                    StringRequest stringRequest = new StringRequest(
                            1, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String status=jsonObject.getString("status");
                                if(status.equals("data_tersimpan")){
                                    Boolean cekIntent=getIntent().hasExtra("edit_data");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Simpan_data.this);
                                    builder.setTitle("SUKSES");
                                    builder.setMessage(cekIntent?"Data Berhasil Diubah!":"Data Berhasil Disimpan!");
                                    builder.setPositiveButton("Klik OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                } else {
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Simpan_data.this, "SALAHjava", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> form=new HashMap<String,String>();
                            form.put("nama",nama.getText().toString());
                            form.put("jk",jk.getText().toString());
                            form.put("alamat",alamat.getText().toString());
                            form.put("keluhan",keluhan.getText().toString());
                            form.put("nomor_handphone",nomor_handphone.getText().toString());
                            if (getIntent().hasExtra("edit_data")){
                                form.put("id_pasien",getIntent().getStringExtra("edit_data"));
                            }
                            return form;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
    void getData(){
        String url=new Configurasi().baseUrl()+"get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response).getJSONObject("data");
                            String gnama=jsonObject.getString("nama");
                            String gjk=jsonObject.getString("jk");
                            String galamat=jsonObject.getString("alamat");
                            String gkeluhan=jsonObject.getString("keluhan");
                            String gnomor_handphone=jsonObject.getString("nomor_handphone");
                            nama.setText(gnama);
                            jk.setText(gjk);
                            alamat.setText(galamat);
                            keluhan.setText(gkeluhan);
                            nomor_handphone.setText(gnomor_handphone);
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
                HashMap<String,String> form=new HashMap<String,String>();
                form.put("id_pasien", getIntent().getStringExtra("edit_data"));
                return form;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}