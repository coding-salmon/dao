package com.doa.doa.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //사진 제목

    private String category; // 사진의 분류 (한식, 양식, 중식 등)
    private String storeName; // 가게 이름
    private Double zoomLevel; // 사진의 줌 레벨 (선택적)

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] originalImageData; // 사진 파일 데이터, 큰 데이터를 저장하기 위해 @Lob 사용

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] croppedImageData;

    // GPS 위치 데이터 (선택적)
    private Double latitude; // 위도
    private Double longitude; // 경도

    @ManyToOne //다대일 관계 설정
    private User user; //픽과 사용자 간의 관계


    }



