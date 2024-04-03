package com.doa.doa.repository;

import com.doa.doa.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PicRepository extends JpaRepository<Pic, Long> {

   //랜덤으로 한 개의 pic객체를 가져오는 쿼리
    @Query(value = "SELECT * FROM pic ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Pic> findRandom();


}
