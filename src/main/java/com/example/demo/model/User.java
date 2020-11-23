package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id = 0L;

    @Column
    private   String login = "";

    @Column
    private   String pass = "";

    @Column
    private Integer active = 0;

    @ManyToMany
    @JoinColumn(name="role_id", referencedColumnName = "role_id")
    private Set<Role> roles;

    public User(long id, String login, String pass, Set<Role> roles) {
        this.id = id;
        this.setPass(pass);
        this.setLogin(login);
        this.setRoles(roles);
    }
    public User(){};

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

}
