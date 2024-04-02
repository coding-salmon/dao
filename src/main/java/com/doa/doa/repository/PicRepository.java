package com.doa.doa.repository;

import com.doa.doa.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicRepository extends JpaRepository<Pic, Long> {
    List<Pic> findRandomPic();
}
