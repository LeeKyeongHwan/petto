package com.example.petto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="animals")
public class Animals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String notice_no;

    @Column(length = 50, nullable = false)
    private String notice_sd;

    @Column(length = 50, nullable = false)
    private String notice_ed;

    @Column(length = 200, nullable = false)
    private String image;

    @Column(length = 100, nullable = false)
    private String kind;

    @Column(length = 100, nullable = false)
    private String color;

    @Column(length = 30, nullable = false)
    private String age;

    @Column(length = 30, nullable = false)
    private String weight;

    @Column(length = 30, nullable = false)
    private String state;

    @Column(length = 30, nullable = false)
    private String gender;

    @Column(length = 30, nullable = false)
    private String neutral;

    @Column(length = 100, nullable = false)
    private String feature;

    @Column(length = 50, nullable = false)
    private String happened;

    @Column(length = 250, nullable = false)
    private String happenplace;

    @Column(length = 100, nullable = false)
    private String carenm;

    @Column(length = 100, nullable = false)
    private String caretel;

    @Column(length = 300, nullable = false)
    private String careaddr;

    @Column(columnDefinition = "Integer default 0")
    private Integer numberOfLiked;
}
