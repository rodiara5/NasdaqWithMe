package com.example.nasdaq.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nasdaq.model.Entity.WatchlistEntity;

public interface WatchlistRepository extends JpaRepository<WatchlistEntity, Integer>{
    
    public List<WatchlistEntity> findByUserId(String userId);

    public WatchlistEntity findByUserIdAndTicker(String userId, String ticker);
}
