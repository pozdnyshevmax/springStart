package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0l;

    @Column(unique = true)
    private String title = "";

    @Column
    private Date creationDate = new Date();

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User autor = new User();

    @Column
    private String htmlText = "";

    public Post(){};

    public Post(String title, Date creationDate, User autor, String htmlText) {
        this.title = title;
        this.creationDate = creationDate;
        this.autor = autor;
        this.htmlText = htmlText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
