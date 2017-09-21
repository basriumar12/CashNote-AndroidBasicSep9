package com.khilman.cashnoteapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khilman.cashnoteapp.InitRetrofit.ApiServices;
import com.khilman.cashnoteapp.InitRetrofit.InitLibrary;
import com.khilman.cashnoteapp.Response.ResponseTambah;
import com.khilman.cashnoteapp.Response.ResponseUpdate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.edtPengeluaran)
    EditText edtPengeluaran;
    @BindView(R.id.edtCatatan)
    EditText edtCatatan;
    @BindView(R.id.edtTanggal)
    TextView edtTanggal;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    // id catatan
    String id_catatan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        ButterKnife.bind(this);

        id_catatan = getIntent().getStringExtra("id");
        String keterangan = getIntent().getStringExtra("ct");
        String pengeluaran = getIntent().getStringExtra("pl");
        String tanggal = getIntent().getStringExtra("tgl");

        // set ke widget
        edtPengeluaran.setText(pengeluaran);
        edtCatatan.setText(keterangan);
        edtTanggal.setText(tanggal);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.edtTanggal, R.id.btnSimpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edtTanggal:
                showDatePicker();
                break;
            case R.id.btnSimpan:
                simpanCatatan();
                break;
        }
    }

    private void simpanCatatan() {
        ApiServices api = InitLibrary.getInstance();

        String pengeluaran = edtPengeluaran.getText().toString();
        String keterangan = edtCatatan.getText().toString();
        String tanggal = edtTanggal.getText().toString();

        Call<ResponseUpdate> call = api.request_ubah(id_catatan, pengeluaran, keterangan, tanggal);
        // kirim / submit
        call.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                if (response.isSuccessful()){
                    String result = response.body().getResult();
                    String message = response.body().getMsg();
                    if (result.equals("true")){
                        // kalau datanya berhasil ditambahkan
                        Toast.makeText(UbahActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UbahActivity.this, TampilCatatanActivity.class));
                        finish();
                    } else {
                        // tapi kalau gagal == false
                        Toast.makeText(UbahActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePicker() {
        int tahun = Calendar.getInstance().get(Calendar.YEAR);
        int bulan = Calendar.getInstance().get(Calendar.MONTH);
        int tanggal = Calendar.getInstance().get(Calendar.DATE);
        DatePickerDialog datePicker = new DatePickerDialog(this, UbahActivity.this, tahun, bulan, tanggal);
        datePicker.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int tahun, int bulan, int tanggal) {
        edtTanggal.setText("" + tahun + "-" + bulan + "-" + tanggal);
    }
}
