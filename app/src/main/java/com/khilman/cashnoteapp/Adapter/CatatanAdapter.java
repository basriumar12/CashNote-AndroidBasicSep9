package com.khilman.cashnoteapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.khilman.cashnoteapp.InitRetrofit.ApiServices;
import com.khilman.cashnoteapp.InitRetrofit.InitLibrary;
import com.khilman.cashnoteapp.R;
import com.khilman.cashnoteapp.Response.Datum;
import com.khilman.cashnoteapp.Response.ResponseDelete;
import com.khilman.cashnoteapp.Response.ResponseTambah;
import com.khilman.cashnoteapp.TambahCatatanActivity;
import com.khilman.cashnoteapp.TampilCatatanActivity;
import com.khilman.cashnoteapp.UbahActivity;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 9/17/17.
 */

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.MyViewHolder> {

    Context con;
    List<Datum> data_catatan;

    public CatatanAdapter(Context con, List<Datum> data_catatan) {
        this.con = con;
        this.data_catatan = data_catatan;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // definisikan layoutnya
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catan, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set data ke textview
        holder.txtPengeluaran.setText("Rp. " + data_catatan.get(position).getPengeluaran());
        holder.txtKeterangan.setText("Catatan : " + data_catatan.get(position).getKeterangan());
        holder.txtTanggal.setText("Tanggal : " + data_catatan.get(position).getTanggal());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(con);
                alert.setTitle("Konfirmasi");
                alert.setMessage("Hapus catatan ini ?");
                alert.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                        String id_catatan = data_catatan.get(position).getId();
                        hapusCatatan(id_catatan);
                    }
                });
                alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                });
                alert.show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, UbahActivity.class);
                intent.putExtra("ct", data_catatan.get(position).getKeterangan());
                intent.putExtra("pl", data_catatan.get(position).getPengeluaran());
                intent.putExtra("tgl", data_catatan.get(position).getTanggal());
                intent.putExtra("id", data_catatan.get(position).getId());
                con.startActivity(intent);
            }
        });
    }

    private void hapusCatatan(String id_catatan) {
        ApiServices api = InitLibrary.getInstance();

        Call<ResponseDelete> call = api.request_hapus(id_catatan);
        // kirim / submit
        call.enqueue(new Callback<ResponseDelete>() {
            @Override
            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                if (response.isSuccessful()){
                    String result = response.body().getResult();
                    String message = response.body().getMsg();
                    if (result.equals("true")){
                        // kalau datanya berhasil ditambahkan
                        Toast.makeText(con, "Berhasil di hapus", Toast.LENGTH_SHORT).show();
                        con.startActivity(new Intent(con, TampilCatatanActivity.class));
                    } else {
                        // tapi kalau gagal == false
                        Toast.makeText(con, "" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDelete> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data_catatan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // deklarasi
        TextView txtPengeluaran, txtTanggal, txtKeterangan;
        ImageButton btnDelete, btnEdit;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtPengeluaran = (TextView) itemView.findViewById(R.id.txtPengeluaran);
            txtTanggal = (TextView) itemView.findViewById(R.id.txtTanggal);
            txtKeterangan = (TextView) itemView.findViewById(R.id.txtKeterangan);

            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);
            btnEdit = (ImageButton) itemView.findViewById(R.id.btnEdit);
        }
    }
}
