package com.melo.inventory.model;

public class AppUserResponse {
    private Long id;
    private String email;

    public AppUserResponse(Long id, String email){
        this.id = id;
        this.email = email;
    }

    public Long getId() {return id;}
    public String getEmail() {return email;}

    public void setId(Long id) {this.id = id;}
    public void setEmail(String email) {this.email = email;}
}
