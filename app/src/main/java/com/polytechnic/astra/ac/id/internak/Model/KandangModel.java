package com.polytechnic.astra.ac.id.internak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KandangModel {
    @SerializedName("kdgId")
    @Expose
    private Integer kdgId;
    @SerializedName("usrId")
    @Expose
    private Integer usrId;
    @SerializedName("kdgNama")
    @Expose
    private String kdgNama;
    @SerializedName("kdgJenis")
    @Expose
    private String kdgJenis;
    @SerializedName("kdgKapasitas")
    @Expose
    private Integer kdgKapasitas;
    @SerializedName("kdgLuas")
    @Expose
    private Integer kdgLuas;
    @SerializedName("kdgLattitude")
    @Expose
    private double kdgLattitude;
    @SerializedName("kdgLongtitude")
    @Expose
    private double kdgLongtitude;
    @SerializedName("kdgAlamat")
    @Expose
    private String kdgAlamat;
    @SerializedName("kdgSuhu")
    @Expose
    private Integer kdgSuhu;
    @SerializedName("kdgStatusMonitor")
    @Expose
    private String kdgStatusMonitor;
    @SerializedName("kdgStatus")
    @Expose
    private String kdgStatus;

    public KandangModel() {
        this.kdgId = 0;
        this.usrId = 0;
        this.kdgNama = "";
        this.kdgJenis = "";
        this.kdgKapasitas = 0;
        this.kdgLuas = 0;
        this.kdgAlamat = "";
        this.kdgLattitude = 0;
        this.kdgLongtitude = 0;
        this.kdgSuhu = 0;
        this.kdgStatusMonitor = "";
        this.kdgStatus = "";
    }

    public KandangModel(Integer kdgId, Integer usrId, String kdgNama, String kdgJenis, Integer kdgKapasitas, Integer kdgLuas, String kdgAlamat, double kdgLattitude, double kdgLongtitude, Integer kdgSuhu, String kdgStatusMonitor, String kdgStatus) {
        this.kdgId = kdgId;
        this.usrId = usrId;
        this.kdgNama = kdgNama;
        this.kdgJenis = kdgJenis;
        this.kdgKapasitas = kdgKapasitas;
        this.kdgLuas = kdgLuas;
        this.kdgAlamat = kdgAlamat;
        this.kdgLattitude = kdgLattitude;
        this.kdgLongtitude = kdgLongtitude;
        this.kdgSuhu = kdgSuhu;
        this.kdgStatusMonitor = kdgStatusMonitor;
        this.kdgStatus = kdgStatus;
    }

    public Integer getKdgId() {
        return kdgId;
    }

    public void setKdgId(Integer kdgId) {
        this.kdgId = kdgId;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getKdgNama() {
        return kdgNama;
    }

    public void setKdgNama(String kdgNama) {
        this.kdgNama = kdgNama;
    }

    public String getKdgJenis() {
        return kdgJenis;
    }

    public void setKdgJenis(String kdgJenis) {
        this.kdgJenis = kdgJenis;
    }

    public Integer getKdgKapasitas() {
        return kdgKapasitas;
    }

    public void setKdgKapasitas(Integer kdgKapasitas) {
        this.kdgKapasitas = kdgKapasitas;
    }

    public Integer getKdgLuas() {
        return kdgLuas;
    }

    public void setKdgLuas(Integer kdgLuas) {
        this.kdgLuas = kdgLuas;
    }

    public String getKdgAlamat() {
        return kdgAlamat;
    }

    public void setKdgAlamat(String kdgAlamat) {
        this.kdgAlamat = kdgAlamat;
    }

    public double getKdgLattitude() {
        return kdgLattitude;
    }

    public void setKdgLattitude(double kdgLattitude) {
        this.kdgLattitude = kdgLattitude;
    }

    public double getKdgLongtitude() {
        return kdgLongtitude;
    }

    public void setKdgLongtitude(double kdgLongtitude) {
        this.kdgLongtitude = kdgLongtitude;
    }

    public Integer getKdgSuhu() {
        return kdgSuhu;
    }

    public void setKdgSuhu(Integer kdgSuhu) {
        this.kdgSuhu = kdgSuhu;
    }

    public String getKdgStatusMonitor() {
        return kdgStatusMonitor;
    }

    public void setKdgStatusMonitor(String kdgStatusMonitor) {
        this.kdgStatusMonitor = kdgStatusMonitor;
    }

    public String getKdgStatus() {
        return kdgStatus;
    }

    public void setKdgStatus(String kdgStatus) {
        this.kdgStatus = kdgStatus;
    }
}