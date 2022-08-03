package com.example.coffeeboard.dto;

import com.example.coffeeboard.entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString

public class CoffeeDTO {
    private Long no;
    private String menu;
    private Long price;

    public Board toEntity() {
        return new Board(no, menu, price);
    }
}
