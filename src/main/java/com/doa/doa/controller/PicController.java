package com.doa.doa.controller;

import com.doa.doa.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PicController {

    private final PicService picService;
    @Autowired
    public PicController(PicService picService) {
        this.picService = picService;

    }

    public ResponseEntity<byte[]> getRandomCroppedPic(){

    }
}
