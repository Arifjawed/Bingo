
package com.example.tasksample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Myuser {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("appkey")
    @Expose
    private String appkey;
    @SerializedName("applink")
    @Expose
    private String applink;
    @SerializedName("jdate")
    @Expose
    private String jdate;
    @SerializedName("logourl")
    @Expose
    private String logourl;
    @SerializedName("bgurl")
    @Expose
    private String bgurl;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("ph")
    @Expose
    private String ph;
    @SerializedName("map")
    @Expose
    private String map;
    @SerializedName("wap")
    @Expose
    private String wap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getApplink() {
        return applink;
    }

    public void setApplink(String applink) {
        this.applink = applink;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getBgurl() {
        return bgurl;
    }

    public void setBgurl(String bgurl) {
        this.bgurl = bgurl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getWap() {
        return wap;
    }

    public void setWap(String wap) {
        this.wap = wap;
    }

}
