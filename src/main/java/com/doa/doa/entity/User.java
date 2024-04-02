package com.doa.doa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String password;
    private String birth;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Pic> pics; // 사용자가 업로드한 사진 목록


}