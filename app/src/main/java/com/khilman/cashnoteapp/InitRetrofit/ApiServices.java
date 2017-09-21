package com.khilman.cashnoteapp.InitRetrofit;

import com.khilman.cashnoteapp.Response.ResponseDelete;
import com.khilman.cashnoteapp.Response.ResponseTambah;
import com.khilman.cashnoteapp.Response.ResponseTampil;
import com.khilman.cashnoteapp.Response.ResponseUpdate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by root on 9/17/17.
 */

public interface ApiServices {

    @GET("show_cash_note.php")
    Call<ResponseTampil> request_tampil_catatan();

    @FormUrlEncoded
    @POST("add_note.php")
    Call<ResponseTambah> request_tambah(
            @Field("pengeluaran") String pengeluaran,
            @Field("keterangan") String keterangan,
            @Field("tanggal") String tanggal
    );


    @FormUrlEncoded
    @POST("update_note.php")
    Call<ResponseUpdate> request_ubah (
            @Field("id") String id_catatan,
            @Field("pengeluaran") String pengeluaran,
            @Field("keterangan") String keterangan,
            @Field("tanggal") String tanggal
    );

    @FormUrlEncoded
    @POST("delete_note.php")
    Call<ResponseDelete> request_hapus (
            @Field("id") String id_catatan
    );
}
