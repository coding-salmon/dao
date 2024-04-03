package com.doa.doa.repository;

import com.doa.doa.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicRepository extends JpaRepository<Pic, Long> {

    //Pic의 모든 ID를 블러오는 메서드
    @Query("SELECT p.id FROM Pic p")
    List<Long> findAllPicIds();


}
