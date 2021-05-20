
package com.example.tasksample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeshboardUser implements Serializable {

    @SerializedName("nid")
    @Expose
    private String nid;
    @SerializedName("appkey")
    @Expose
    private String appkey;
    @SerializedName("ntext")
    @Expose
    private String ntext;
    @SerializedName("ncd")
    @Expose
    private String ncd;
    @SerializedName("ned")
    @Expose
    private String ned;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getNtext() {
        return ntext;
    }

    public void setNtext(String ntext) {
        this.ntext = ntext;
    }

    public String getNcd() {
        return ncd;
    }

    public void setNcd(String ncd) {
        this.ncd = ncd;
    }

    public String getNed() {
        return ned;
    }

    public void setNed(String ned) {
        this.ned = ned;
    }

}
