package com.example.newbie.dto;

public class CreateDTO {

    private String title;
    private String content;

    public CreateDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CreateDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
