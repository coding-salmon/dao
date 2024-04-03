package com.doa.doa.service;

import com.doa.doa.entity.Pic;
import com.doa.doa.repository.PicRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PicServiceImpl implements PicService{

    private final PicRepository picRepository;

    @Autowired
    public PicServiceImpl(PicRepository picRepository){
        this.picRepository = picRepository;
    }

    @Override
    public Pic savePic(Pic pic) {
        return picRepository.save(pic);
    }

    public Optional<Pic> getRandomPic(){
        return picRepository.findRandom();
    }


}


