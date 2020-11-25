package com.example.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "role_name")
    private String role = "";

    public Role(String role) {
        this.role = role;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }

    public Role(){}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
