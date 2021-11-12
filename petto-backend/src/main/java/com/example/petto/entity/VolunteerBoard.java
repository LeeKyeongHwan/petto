package com.example.petto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@NoArgsConstructor
@Entity
@Table(name="petto_volunteer")
@Transactional
public class VolunteerBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_no")
    private Long volunteerNo;

    @Column(length = 200, nullable = false)
    private String volTitle;

    @Column(length = 100, nullable = false)
    private String fileName;

    @Column(length = 100, nullable = false)
    private String contentFileName;


}
