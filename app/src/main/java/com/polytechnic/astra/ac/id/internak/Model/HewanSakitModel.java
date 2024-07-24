package com.polytechnic.astra.ac.id.internak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HewanSakitModel {
    @SerializedName("tmoId")
    @Expose
    private Integer tmoId;
    @SerializedName("usrId")
    @Expose
    private Integer usrId;
    @SerializedName("tmoKeluhan")
    @Expose
    private String tmoKeluhan;
    @SerializedName("tmoDeskripsi")
    @Expose
    private String tmoDeskripsi;
    @SerializedName("tmoPeriode")
    @Expose
    private String tmoPeriode;
    @SerializedName("tmoTanggalMulai")
    @Expose
    private Date tmoTanggalMulai;
    @SerializedName("tmoTanggalAkhir")
    @Expose
    private Date tmoTanggalAkhir;
    @SerializedName("tmoStatus")
    @Expose
    private String tmoStatus;
    @SerializedName("hwnId")
    @Expose
    private Integer hwnId;

    public HewanSakitModel() {
        this.tmoId = 0;
        this.usrId = 0;
        this.tmoKeluhan = "";
        this.tmoDeskripsi = "";
        this.tmoPeriode = "";
        this.tmoTanggalMulai = new Date();
        this.tmoTanggalAkhir = new Date();
        this.tmoStatus = "";
        this.hwnId=0;
    }

    public HewanSakitModel(Integer tmoId, Integer usrId, String tmoKeluhan, String tmoDeskripsi, String tmoPeriode, Date tmoTanggalMulai, Date tmoTanggalAkhir, String tmoStatus,Integer hwnId) {
        this.tmoId = tmoId;
        this.usrId = usrId;
        this.tmoKeluhan = tmoKeluhan;
        this.tmoDeskripsi = tmoDeskripsi;
        this.tmoPeriode = tmoPeriode;
        this.tmoTanggalMulai = tmoTanggalMulai;
        this.tmoTanggalAkhir = tmoTanggalAkhir;
        this.tmoStatus = tmoStatus;
        this.hwnId=hwnId;
    }

    public Integer getTmoId() {
        return tmoId;
    }

    public void setTmoId(Integer tmoId) {
        this.tmoId = tmoId;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getTmoKeluhan() {
        return tmoKeluhan;
    }

    public void setTmoKeluhan(String tmoKeluhan) {
        this.tmoKeluhan = tmoKeluhan;
    }

    public String getTmoDeskripsi() {
        return tmoDeskripsi;
    }

    public void setTmoDeskripsi(String tmoDeskripsi) {
        this.tmoDeskripsi = tmoDeskripsi;
    }

    public String getTmoPeriode() {
        return tmoPeriode;
    }

    public void setTmoPeriode(String tmoPeriode) {
        this.tmoPeriode = tmoPeriode;
    }

    public Date getTmoTanggalMulai() {
        return tmoTanggalMulai;
    }

    public void setTmoTanggalMulai(Date tmoTanggalMulai) {
        this.tmoTanggalMulai = tmoTanggalMulai;
    }

    public Date getTmoTanggalAkhir() {
        return tmoTanggalAkhir;
    }

    public void setTmoTanggalAkhir(Date tmoTanggalAkhir) {
        this.tmoTanggalAkhir = tmoTanggalAkhir;
    }

    public String getTmoStatus() {
        return tmoStatus;
    }

    public void setTmoStatus(String tmoStatus) {
        this.tmoStatus = tmoStatus;
    }

    public Integer getHwnId() {
        return hwnId;
    }

    public void setHwnId(Integer hwnId) {
        this.hwnId = hwnId;
    }
}