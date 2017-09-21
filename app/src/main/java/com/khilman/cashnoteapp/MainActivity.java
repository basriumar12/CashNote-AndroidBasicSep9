package com.khilman.cashnoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imgTambah)
    ImageView imgTambah;
    @BindView(R.id.imgLihatCatatan)
    ImageView imgLihatCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imgTambah, R.id.imgLihatCatatan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgTambah: // kalau imgTambah tambah
                startActivity(new Intent(MainActivity.this, TambahCatatanActivity.class)); // pindah activity
                break;
            case R.id.imgLihatCatatan:
                startActivity(new Intent(MainActivity.this, TampilCatatanActivity.class));
                break;
        }
    }
}
