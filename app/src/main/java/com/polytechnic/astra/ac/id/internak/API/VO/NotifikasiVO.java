package com.polytechnic.astra.ac.id.internak.API.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifikasiVO {
    @SerializedName("ntfId")
    @Expose
    private Integer ntfId;

    @SerializedName("kdgId")
    @Expose
    private Integer kdgId;

    @SerializedName("ntfJudul")
    @Expose
    private String ntfJudul;

    @SerializedName("ntfDeskripsi")
    @Expose
    private String ntfDeskripsi;

    @SerializedName("ntfTipe")
    @Expose
    private String ntfTipe;

    @SerializedName("ntfStatus")
    @Expose
    private String ntfStatus;

    @SerializedName("ntfTimestamp")
    @Expose
    private String ntfTimestamp; // Change to String

    // Getters and Setters
    public Integer getNtfId() {
        return ntfId;
    }

    public void setNtfId(Integer ntfId) {
        this.ntfId = ntfId;
    }

    public Integer getKdgId() {
        return kdgId;
    }

    public void setKdgId(Integer kdgId) {
        this.kdgId = kdgId;
    }

    public String getNtfJudul() {
        return ntfJudul;
    }

    public void setNtfJudul(String ntfJudul) {
        this.ntfJudul = ntfJudul;
    }

    public String getNtfDeskripsi() {
        return ntfDeskripsi;
    }

    public void setNtfDeskripsi(String ntfDeskripsi) {
        this.ntfDeskripsi = ntfDeskripsi;
    }

    public String getNtfTipe() {
        return ntfTipe;
    }

    public void setNtfTipe(String ntfTipe) {
        this.ntfTipe = ntfTipe;
    }

    public String getNtfStatus() {
        return ntfStatus;
    }

    public void setNtfStatus(String ntfStatus) {
        this.ntfStatus = ntfStatus;
    }

    public String getNtfTimestamp() {
        return ntfTimestamp;
    }

    public void setNtfTimestamp(String ntfTimestamp) {
        this.ntfTimestamp = ntfTimestamp;
    }
}
