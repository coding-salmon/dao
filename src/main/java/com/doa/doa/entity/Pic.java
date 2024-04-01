package com.doa.doa.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    private String hint; // 사진에 대한 힌트
    private String category; // 사진의 분류 (한식, 양식, 중식 등)
    private String storeName; // 가게 이름
    private Double zoomLevel; // 사진의 줌 레벨 (선택적)

    @Lob
    private byte[] imageData; // 사진 파일 데이터, 큰 데이터를 저장하기 위해 @Lob 사용

    // GPS 위치 데이터 (선택적)
    private Double latitude; // 위도
    private Double longitude; // 경도


}
