package com.doa.doa.service;

import com.doa.doa.entity.Pic;

import java.util.Optional;

public interface PicService {


    /**
     * @param pic
     * @return
     */
    Pic savePic(Pic pic); // 사진저장

    Optional<Pic> getRandomPic();


}
