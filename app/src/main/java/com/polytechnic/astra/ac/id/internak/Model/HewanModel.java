package com.polytechnic.astra.ac.id.internak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HewanModel {
    @SerializedName("hwnId")
    @Expose
    private Integer hwnId;
    @SerializedName("kdgId")
    @Expose
    private Integer kdgId;
    @SerializedName("hwnNama")
    @Expose
    private String hwnNama;
    @SerializedName("hwnUsia")
    @Expose
    private Integer hwnUsia;
    @SerializedName("hwnBerat")
    @Expose
    private Integer hwnBerat;
    @SerializedName("hwnMasuk")
    @Expose
    private String hwnMasuk;
    @SerializedName("hwnStatus")
    @Expose
    private String hwnStatus;
    public HewanModel() {
        this.hwnId = 0;
        this.kdgId = 0;
        this.hwnNama = "";
        this.hwnUsia = 0;
        this.hwnBerat = 0;
        this.hwnMasuk = "";
        this.hwnStatus = "";
    }

    public HewanModel(Integer hwnId, Integer kdgId, String hwnNama, Integer hwnUsia, Integer hwnBerat, String hwnMasuk, String hwnStatus) {
        this.hwnId = hwnId;
        this.kdgId = kdgId;
        this.hwnNama = hwnNama;
        this.hwnUsia = hwnUsia;
        this.hwnBerat = hwnBerat;
        this.hwnMasuk = hwnMasuk;
        this.hwnStatus = hwnStatus;
    }

    public Integer getHwnId() {
        return hwnId;
    }

    public void setHwnId(Integer hwnId) {
        this.hwnId = hwnId;
    }

    public Integer getKdgId() {
        return kdgId;
    }

    public void setKdgId(Integer kdgId) {
        this.kdgId = kdgId;
    }

    public String getHwnNama() {
        return hwnNama;
    }

    public void setHwnNama(String hwnNama) {
        this.hwnNama = hwnNama;
    }

    public Integer getHwnUsia() {
        return hwnUsia;
    }

    public void setHwnUsia(Integer hwnUsia) {
        this.hwnUsia = hwnUsia;
    }

    public Integer getHwnBerat() {
        return hwnBerat;
    }

    public void setHwnBerat(Integer hwnBerat) {
        this.hwnBerat = hwnBerat;
    }

    public String getHwnMasuk() {
        return hwnMasuk;
    }

    public void setHwnMasuk(String hwnMasuk) {
        this.hwnMasuk = hwnMasuk;
    }

    public String getHwnStatus() {
        return hwnStatus;
    }

    public void setHwnStatus(String hwnStatus) {
        this.hwnStatus = hwnStatus;
    }
}
