package com.polytechnic.astra.ac.id.internak.API.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("data")
    @Expose
    private T data;

    @SerializedName("message")
    @Expose
    private String message;

    // Getter dan Setter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
