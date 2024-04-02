package com.doa.doa.controller;


import com.doa.doa.entity.Pic;
import com.doa.doa.service.PicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PicMakeController {

    private static final Logger logger = LoggerFactory.getLogger(PicMakeController.class);

    private final PicService picService;

    @Autowired
    public PicMakeController(PicService picService) {
        this.picService = picService;
    }

    @PostMapping("/picMake")
    public String handleFileUpload(@RequestParam("pic") MultipartFile originalImage,
                                   @RequestParam("croppedImage") MultipartFile croppedImage,
                                   @RequestParam("picTitle") String picTitle,
                                   @RequestParam("picHint") String picHint,
                                   @RequestParam("picCategory") String picCategory,
                                   Model model){

        logger.info("Handling file upload...");

        String message = ""; // 메시지 초기화
        try {
            if (!originalImage.isEmpty() && !croppedImage.isEmpty()) {
                logger.info("Original and cropped images are present.");
                Pic pic = new Pic(); //pic객체 생성

                pic.setTitle(picTitle);
                pic.setHint(picHint);
                pic.setCategory(picCategory);
                pic.setOriginalImageData(originalImage.getBytes());
                pic.setCroppedImageData(croppedImage.getBytes());


                picService.savePic(pic); // Pic 객체 저장
                model.addAttribute("message", "Upload successful!");
                logger.info("Upload successful!");
                return "redirect:/pic/picMake?message=Upload%20successful!";
            } else {
                // 파일이 선택되지 않았을 경우의 처리
                logger.warn("File is not selected.");
                model.addAttribute("message", "File is not selected");
            }
        } catch (Exception e) {
            logger.error("An error occurred during file upload.", e);
            e.printStackTrace();
            model.addAttribute("message", "An error occurred. Please try again.");
        }


        return "pic/picMake"; // 같은 템플릿을 반환하여 페이지 새로고침 없이 메시지 표시
    }

    }


