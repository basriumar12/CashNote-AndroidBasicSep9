package com.khilman.cashnoteapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khilman.cashnoteapp.InitRetrofit.ApiServices;
import com.khilman.cashnoteapp.InitRetrofit.InitLibrary;
import com.khilman.cashnoteapp.Response.ResponseTambah;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahCatatanActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.edtPengeluaran)
    EditText edtPengeluaran;
    @BindView(R.id.edtCatatan)
    EditText edtCatatan;
    @BindView(R.id.edtTanggal)
    TextView edtTanggal;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
        //metohd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.edtTanggal, R.id.btnSimpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edtTanggal:
                showDatePicker(); // menampilkan date picker
                break;
            case R.id.btnSimpan:
                simpanCatatan(); // simpan catatan
                break;
        }
    }

    private void simpanCatatan() {
        //
        ApiServices api = InitLibrary.getInstance();

        String pengeluaran = edtPengeluaran.getText().toString();
        String keterangan = edtCatatan.getText().toString();
        String tanggal = edtTanggal.getText().toString();

        Call<ResponseTambah> call = api.request_tambah(pengeluaran, keterangan, tanggal);
        // kirim / submit
        call.enqueue(new Callback<ResponseTambah>() {
            @Override
            public void onResponse(Call<ResponseTambah> call, Response<ResponseTambah> response) {
                if (response.isSuccessful()){
                    String result = response.body().getResult();
                    String message = response.body().getMsg();
                    if (result.equals("true")){
                        // kalau datanya berhasil ditambahkan
                        Toast.makeText(TambahCatatanActivity.this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahCatatanActivity.this, TampilCatatanActivity.class));
                        finish();
                    } else {
                        // tapi kalau gagal == false
                        Toast.makeText(TambahCatatanActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTambah> call, Throwable t) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePicker() {
        int tahun = Calendar.getInstance().get(Calendar.YEAR);
        int bulan = Calendar.getInstance().get(Calendar.MONTH);
        int tanggal = Calendar.getInstance().get(Calendar.DATE);
        DatePickerDialog datePicker = new DatePickerDialog(this, TambahCatatanActivity.this, tahun, bulan, tanggal);
        datePicker.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int tahun, int bulan, int tanggal) {
        edtTanggal.setText("" + tahun + "-" + bulan + "-" + tanggal);
    }
}
