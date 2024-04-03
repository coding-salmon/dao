package com.doa.doa.service;

import com.doa.doa.entity.Pic;
import com.doa.doa.repository.PicRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class PicServiceImpl implements PicService{

    private final PicRepository picRepository;
    private List<Long> allPicIds; //모든 pic의 id를 저장하는 리스트
    private Set<Long> usedIds = new HashSet<>(); //사용된 pic의 id를 저장하는 세트
    private final Random random = new Random(); //랜덤 값을 생성하는 객체



    @Autowired
    public PicServiceImpl(PicRepository picRepository){
        this.picRepository = picRepository;
    }



    //애플리케이션 시작 시 초기화 작업을 수행하는 메서드
    @PostConstruct
    public void init(){
        //애플리케이션 시작 시 데이터베이스에서 모든 Pic의 ID를 가져와서 리스트에 저장
        allPicIds = picRepository.findAllPicIds();
    }



    @Override
    public Pic savePic(Pic pic) {
        return picRepository.save(pic);
    }



    //랜덤하게 pic을 가져오는 메서드
    @Override
    public Pic getRandomPic() {
        if (allPicIds.isEmpty() || allPicIds.size() == usedIds.size()) {
            return null; // 사용 가능한 pic이 없을 때 null 반환
        }

        Long selectedId = null;
        while (selectedId == null || usedIds.contains(selectedId)) {

            //랜덤하게 pic의 id를 선택
            int randomIndex = random.nextInt(allPicIds.size());
            selectedId = allPicIds.get(randomIndex);
        }
        if (!usedIds.contains(selectedId)) {
            //선택된 pic의 id를 사용한 id 세트에 추가하고 해당 pic의 모든 정보를 반환
            usedIds.add(selectedId);
            return getPicById(selectedId); // 해당 Id에 해당하는 pic의 모든 정보를 반환
        }
        return null; // 혹시 모르는 예외 상황에 대비
    }

    //모든 pic이 사용되었을때 반환하는 메시지를 생성하는 메서드
    @Override
    public String getMessageForAllUsed() {
    if (allPicIds.size() == usedIds.size()) {
        return "준비된 모든 Pic을 이용해주셔서 감사합니다. 직접 Pic을 만들어 행복한 점심을 공유하세요.";
    }
    return "";
    }

    public Pic getPicById(Long id){
        return picRepository.findById(id).orElse(null);
    }

}


