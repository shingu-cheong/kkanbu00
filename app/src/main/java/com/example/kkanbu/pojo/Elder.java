package com.example.kkanbu.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString

public class Elder {
    @SerializedName(value = "id")
    @Expose
    private Integer id;

    @SerializedName(value = "elderName")
    @Expose
    private String elderName;

    @SerializedName(value = "elderPh")
    @Expose
    private String elderPh;

    @SerializedName(value = "elderAdr")
    @Expose
    private String elderAdr;

    @SerializedName(value = "mngPh")
    @Expose
    private String mngPh;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderPh() {
        return elderPh;
    }

    public void setElderPh(String elderPh) {
        this.elderPh = elderPh;
    }

    public String getElderAdr() {
        return elderAdr;
    }

    public void setElderAdr(String elderAdr) {
        this.elderAdr = elderAdr;
    }

    public String getMngPh() {
        return mngPh;
    }

    public void setMngPh(String mngPh) {
        this.mngPh = mngPh;
    }

    @Override
    public String toString() {
        return "Elder{" +
                "id=" + id +
                ", elderName='" + elderName + '\'' +
                ", elderPh='" + elderPh + '\'' +
                ", elderAdr='" + elderAdr + '\'' +
                ", mngPh='" + mngPh + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Elder() {
        super();
    }

}
