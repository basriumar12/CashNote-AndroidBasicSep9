package com.khilman.cashnoteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.khilman.cashnoteapp.Adapter.CatatanAdapter;
import com.khilman.cashnoteapp.InitRetrofit.ApiServices;
import com.khilman.cashnoteapp.InitRetrofit.InitLibrary;
import com.khilman.cashnoteapp.Response.Datum;
import com.khilman.cashnoteapp.Response.ResponseTampil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilCatatanActivity extends AppCompatActivity {

    @BindView(R.id.listCatatan)
    RecyclerView listCatatan;


    // Deklarasi
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_catatan);
        ButterKnife.bind(this);
        //tambah
        // inisialisasi
        layoutManager = new LinearLayoutManager(this);
        // set LM ke recyclerview
        listCatatan.setLayoutManager(layoutManager);

        // ambil data dari web service
        getCatatan();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getCatatan();
    }
    private void getCatatan() {
        // init retrofitnya
        ApiServices api = InitLibrary.getInstance();
        // siapkan request
        Call<ResponseTampil> call = api.request_tampil_catatan();
        // kirim / submit request ke web server
        call.enqueue(new Callback<ResponseTampil>() {
            @Override
            public void onResponse(Call<ResponseTampil> call, Response<ResponseTampil> response) {
                // cek apakah responsenya normal / ngga ada error
                if (response.isSuccessful()){
                    // jalankan perintah
                    String result = response.body().getResult();
                    String msg = response.body().getMsg();
                    // simpan data ke class Datum

                    Log.d("result", "" + result);
                    List<Datum> data_catatan = response.body().getData();
                    // siapkan adapter untuk RecyclerView
                    CatatanAdapter adapter = new CatatanAdapter(TampilCatatanActivity.this, data_catatan);
                    // set adapter ke widget recycler view
                    listCatatan.setAdapter(adapter);

                    // resultnya true?
                    if (result.equals("true")){

                    } else {
                        Toast.makeText(TampilCatatanActivity.this, "" + msg , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTampil> call, Throwable t) {

            }
        });
    }
}
