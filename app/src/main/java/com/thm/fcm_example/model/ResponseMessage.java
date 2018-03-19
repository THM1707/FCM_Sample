package com.thm.fcm_example.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thm on 15/03/2018.
 */
public class ResponseMessage {
    @SerializedName("status_code")
    private String mStatusCode;

    public String getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(String statusCode) {
        mStatusCode = statusCode;
    }
}
