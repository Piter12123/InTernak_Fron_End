package com.polytechnic.astra.ac.id.internak.API.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserVO {
    @SerializedName("usrId")
    @Expose
    private Integer usrId;
    @SerializedName("usrNamaDepan")
    @Expose
    private String usrNamaDepan;
    @SerializedName("usrNamaBlkg")
    @Expose
    private String usrNamaBlkg;
    @SerializedName("kodePos")
    @Expose
    private String kodePos;
    @SerializedName("noTelp")
    @Expose
    private String noTelp;
    @SerializedName("usrEmail")
    @Expose
    private String usrEmail;
    @SerializedName("usrNama")
    @Expose
    private String usrNama;
    @SerializedName("usrPassword")
    @Expose
    private String usrPassword;
    @SerializedName("usrStatus")
    @Expose
    private String usrStatus;

    public UserVO() {
        this.usrId = 0;
        this.usrNamaDepan="";
        this.usrNamaBlkg = "";
        this.kodePos = "";
        this.noTelp = "";
        this.usrEmail = "";
        this.usrNama = "";
        this.usrPassword = "";
        this.usrStatus = "";
    }

    public UserVO(Integer usrId, String usrNamaDepan, String usrNamaBlkg, String kodePos, String noTelp, String usrEmail, String usrNama, String usrPassword, String usrStatus) {
        this.usrId = usrId;
        this.usrNamaDepan = usrNamaDepan;
        this.usrNamaBlkg = usrNamaBlkg;
        this.kodePos = kodePos;
        this.noTelp = noTelp;
        this.usrEmail = usrEmail;
        this.usrNama = usrNama;
        this.usrPassword = usrPassword;
        this.usrStatus = usrStatus;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrNamaDepan() {
        return usrNamaDepan;
    }

    public void setUsrNamaDepan(String usrNamaDepan) {
        this.usrNamaDepan = usrNamaDepan;
    }

    public String getUsrNamaBlkg() {
        return usrNamaBlkg;
    }

    public void setUsrNamaBlkg(String usrNamaBlkg) {
        this.usrNamaBlkg = usrNamaBlkg;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrNama() {
        return usrNama;
    }

    public void setUsrNama(String usrNama) {
        this.usrNama = usrNama;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(String usrStatus) {
        this.usrStatus = usrStatus;
    }
}
