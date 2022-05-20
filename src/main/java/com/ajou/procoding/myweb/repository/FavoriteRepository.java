package com.ajou.procoding.myweb.repository;

import com.ajou.procoding.myweb.entity.FavoriteMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteMusic, String> {
    List<FavoriteMusic> findAll();
    void deleteById(String id);
}