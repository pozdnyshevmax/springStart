package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id = 0L;

    @Column
    private  String login = "";

    @Column
    private  String pass = "";

   // @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   // @JoinColumn(name = "role_id", referencedColumnName = "role")
    @JoinTable(name = "Roles", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "role")})
    private Set<Role> role = Collections.emptySet();

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getPass() {
       return this.pass;
    }

    public Set getRole() {
        return role;
    }

    public void setRole(Set role) {
        this.role = role;
    }

}
