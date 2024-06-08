package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.WatchlistDao;
import com.example.nasdaq.model.Entity.WatchlistEntity;
import com.example.nasdaq.model.Repository.WatchlistRepository;

@Service
public class WatchlistDaoImpl implements WatchlistDao{
    
    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public List<WatchlistEntity> getWatchlist(String userId) {
        // TODO Auto-generated method stub
        List<WatchlistEntity> entities = watchlistRepository.findByUserId(userId);
        return entities;
    }

    
    @Override
    public WatchlistEntity getOneWatchlist(String userId, String ticker) {
        // TODO Auto-generated method stub
        WatchlistEntity entity = watchlistRepository.findByUserIdAndTicker(userId, ticker);
        return entity;
    }


    @Override
    public void addToWatchlist(String userId, String ticker, String name) {
        // TODO Auto-generated method stub
        WatchlistEntity entity = new WatchlistEntity();
        entity.setTicker(ticker);
        entity.setUserId(userId);
        entity.setName(name);

        watchlistRepository.save(entity);
    }

    @Override
    public void deleteFromWatchlist(String userId, String ticker) {
        // TODO Auto-generated method stub
        WatchlistEntity entity = watchlistRepository.findByUserIdAndTicker(userId, ticker);
        watchlistRepository.delete(entity);
    }

    
}
