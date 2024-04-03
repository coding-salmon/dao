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
    public ResponseEntity<?> getRandomCroppedPic() {
        Pic randomPic = picService.getRandomPic();
        if (randomPic != null || randomPic.getCroppedImageData() != null) {
            // 랜덤 pic이 존재하고 크롭된 이미지 데이터가 있을 경우, 데이터 반환
            byte[] croppedImageData = randomPic.getCroppedImageData();
            // 크롭된 이미지 데이터를 바이너리 형태로 클라이언트에 반환
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // 이 부분은 실제 이미지 타입에 따라 변경될 수 있음
                    .body(croppedImageData);
        }else{
            // 모든 이미지가 사용되었거나 크롭된 이미지 데이터가 없을 경우 메시지 반환

            String message = picService.getMessageForAllUsed();
            if (!message.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("message", message);
                return ResponseEntity.ok(response);
            } else {
                // 만약 모든 이미지를 아직 사용하지 않았으나 특정 이유로 이미지를 불러올 수 없을 때
                logger.error("Failed to load random picture");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }
}