package com.example.coffeeboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // // 값들의 순서를 정해줌
    private Long no;

    @Column
    private String menu;

    @Column
    private Long price;

}
