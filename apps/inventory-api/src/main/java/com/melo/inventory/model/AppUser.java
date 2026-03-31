package com.melo.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String role;

    public AppUser(){}

    public Long getId(){return id;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public String getRole(){return role;}

    public void setId(Long id){this.id = id;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setRole(String role){this.role = role;}
}
