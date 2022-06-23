package com.example.newbie.entity;


import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Article {
@Id
@GeneratedValue
    private long id;
@Column
private String title;
@Column

private String content;
public Article(long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
}
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

