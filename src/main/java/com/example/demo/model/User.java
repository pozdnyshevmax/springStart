package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    public Long id = 0L;

    @Column
    public   String login = "";

    @Column
    public   String pass = "";

   // @ManyToMany(cascade = CascadeType.ALL)
   // @JoinColumn(name = "role_id")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Roles", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<Role> role;

}
