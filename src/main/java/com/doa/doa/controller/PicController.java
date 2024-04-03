package com.doa.doa.controller;

import com.doa.doa.entity.Pic;
import com.doa.doa.service.PicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PicController {

    private final PicService picService;
    private static final Logger logger = LoggerFactory.getLogger(PicController.class);

    @Autowired
    public PicController(PicService picService) {
        this.picService = picService;
    }

    @GetMapping("/random-pic")
    public ResponseEntity<?> getRandomPic() {
        logger.info("랜덤 Pic 가져오기 시도");
        Optional<Pic> picOptional = picService.getRandomPic();
        if (!picOptional.isPresent()) {
            logger.warn("Pic을 찾을 수 없음");
            return ResponseEntity.notFound().build();
        }

        Pic pic = picOptional.get();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("title", pic.getTitle());
        responseData.put("category", pic.getCategory());
        responseData.put("storeName", pic.getStoreName());
        // 이미지 데이터를 Base64로 인코딩하여 JSON 응답에 포함
        responseData.put("originalImage", Base64.getEncoder().encodeToString(pic.getOriginalImageData()));
        responseData.put("croppedImage", Base64.getEncoder().encodeToString(pic.getCroppedImageData()));

        return ResponseEntity.ok(responseData);
    }
}