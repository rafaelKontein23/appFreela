package com.freela.freelancer.presentation.bank.dto;

public class BancoDTO {

    private String ispb;
    private String name;
    private int code;
    private String fullName;

    public BancoDTO(String fullName, int code, String name, String ispb) {
        this.fullName = fullName;
        this.code = code;
        this.name = name;
        this.ispb = ispb;
    }

    public BancoDTO() {
    }


    public String getIspb() {
        return ispb;
    }

    public void setIspb(String ispb) {
        this.ispb = ispb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
