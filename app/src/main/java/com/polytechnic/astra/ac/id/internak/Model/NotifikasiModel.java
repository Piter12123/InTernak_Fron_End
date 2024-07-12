package com.polytechnic.astra.ac.id.internak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;

public class NotifikasiModel {
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
    private Date ntfTimestamp;

    public NotifikasiModel() {
        this.ntfId = 0;
        this.kdgId = 0;
        this.ntfJudul = "";
        this.ntfDeskripsi = "";
        this.ntfTipe = "";
        this.ntfStatus = "";
        this.ntfTimestamp = new Date();
    }

    public NotifikasiModel(Integer ntfId, Integer kdgId, String ntfJudul, String ntfDeskripsi, String ntfTipe, String ntfStatus, Date ntfTimestamp) {
        this.ntfId = ntfId;
        this.kdgId = kdgId;
        this.ntfJudul = ntfJudul;
        this.ntfDeskripsi = ntfDeskripsi;
        this.ntfTipe = ntfTipe;
        this.ntfStatus = ntfStatus;
        this.ntfTimestamp = ntfTimestamp;
    }

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

    public Date getNtfTimestamp() {
        return ntfTimestamp;
    }

    public void setNtfTimestamp(Date ntfTimestamp) {
        this.ntfTimestamp = ntfTimestamp;
    }
}