package com.melo.inventory.controller;

public class ApiInfo {
    private String status;
    private String version;

    public ApiInfo(String status, String version){
        this.status = status;
        this.version = version;
    }

    public String getStatus(){return status;}
    public String getVersion(){return version;}
}