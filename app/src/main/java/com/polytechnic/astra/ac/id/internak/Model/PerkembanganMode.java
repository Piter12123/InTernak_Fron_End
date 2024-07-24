package com.polytechnic.astra.ac.id.internak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PerkembanganMode {
    @SerializedName("dtpId")
    @Expose
    private Integer dtpId;
    @SerializedName("tmoId")
    @Expose
    private Integer tmoId;
    @SerializedName("dtpDeskripsi")
    @Expose
    private String dtpDeskripsi;
    @SerializedName("dtpFile")
    @Expose
    private String dtpFile;
    @SerializedName("dtpTimestamp")
    @Expose
    private Date dtpTimestamp;

    public PerkembanganMode() {
        this.dtpId = 0;
        this.tmoId = 0;
        this.dtpDeskripsi="";
        this.dtpFile = "";
        this.dtpTimestamp = new Date();
    }

    public PerkembanganMode(Integer dtpId, Integer tmoId, String dtpDeskripsi, String dtpFile, Date dtpTimestamp) {
        this.dtpId = dtpId;
        this.tmoId = tmoId;
        this.dtpDeskripsi = dtpDeskripsi;
        this.dtpFile = dtpFile;
        this.dtpTimestamp = dtpTimestamp;
    }

    public Integer getDtpId() {
        return dtpId;
    }

    public void setDtpId(Integer dtpId) {
        this.dtpId = dtpId;
    }

    public Integer getTmoId() {
        return tmoId;
    }

    public void setTmoId(Integer tmoId) {
        this.tmoId = tmoId;
    }

    public String getDtpDeskripsi() {
        return dtpDeskripsi;
    }

    public void setDtpDeskripsi(String dtpDeskripsi) {
        this.dtpDeskripsi = dtpDeskripsi;
    }

    public String getDtpFile() {
        return dtpFile;
    }

    public void setDtpFile(String dtpFile) {
        this.dtpFile = dtpFile;
    }

    public Date getDtpTimestamp() {
        return dtpTimestamp;
    }

    public void setDtpTimestamp(Date dtpTimestamp) {
        this.dtpTimestamp = dtpTimestamp;
    }
}
