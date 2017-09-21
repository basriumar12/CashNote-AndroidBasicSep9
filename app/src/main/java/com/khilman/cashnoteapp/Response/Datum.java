
package com.khilman.cashnoteapp.Response;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    private String mId;
    @SerializedName("keterangan")
    private String mKeterangan;
    @SerializedName("pengeluaran")
    private String mPengeluaran;
    @SerializedName("tanggal")
    private String mTanggal;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKeterangan() {
        return mKeterangan;
    }

    public void setKeterangan(String keterangan) {
        mKeterangan = keterangan;
    }

    public String getPengeluaran() {
        return mPengeluaran;
    }

    public void setPengeluaran(String pengeluaran) {
        mPengeluaran = pengeluaran;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public void setTanggal(String tanggal) {
        mTanggal = tanggal;
    }

}
