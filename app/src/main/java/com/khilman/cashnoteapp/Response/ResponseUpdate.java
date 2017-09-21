
package com.khilman.cashnoteapp.Response;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdate {

    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private String mResult;

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
