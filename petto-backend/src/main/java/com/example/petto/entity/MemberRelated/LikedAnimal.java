package com.example.petto.entity.MemberRelated;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="liked_animal")
public class LikedAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_animal_no")
    private Long likedAnimalNo;

    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    @Column(length = 20, nullable = false)
    private Long noticeNo;

}
