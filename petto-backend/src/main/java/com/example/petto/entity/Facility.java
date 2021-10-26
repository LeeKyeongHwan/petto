package com.example.petto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@Entity
@Table(name="petto_facility")
@Transactional
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_no")
    private Long facilityNo;

    @Column(length = 100, nullable = false)
    private String facilityAddr;

    @Column(length = 50, nullable = false)
    private String facilityName;

    @Column(length = 20, nullable = false)
    private String facilityTel;

    @Column(length = 20, nullable = false)
    private String orgName;

    @Column(length = 20, nullable = false)
    private String saveTrgtAnimal;
}
