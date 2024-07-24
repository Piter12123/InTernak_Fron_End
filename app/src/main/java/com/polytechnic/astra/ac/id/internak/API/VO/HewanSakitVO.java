package com.polytechnic.astra.ac.id.internak.API.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class HewanSakitVO {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<HewanSakitVO> data;
    @SerializedName("message")
    @Expose
    private String message;
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

    public HewanSakitVO() {
        this.tmoId = 0;
        this.usrId = 0;
        this.tmoKeluhan="";
        this.tmoDeskripsi = "";
        this.tmoPeriode = "";
        this.tmoTanggalMulai = new Date();
        this.tmoTanggalAkhir = new Date();
        this.tmoStatus = "";
        this.hwnId=0;
    }


    public HewanSakitVO(Integer tmoId, Integer usrId, String tmoKeluhan, String tmoDeskripsi, String tmoPeriode, Date tmoTanggalMulai, Date tmoTanggalAkhir, String tmoStatus,Integer hwnId) {
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

    public HewanSakitVO(int status, List<HewanSakitVO> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public HewanSakitVO(Object o, Integer usrId, String keluhan, String deskripsi, String periode, Object o1, Object o2, String status) {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<HewanSakitVO> getData() {
        return data;
    }

    public void setData(List<HewanSakitVO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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