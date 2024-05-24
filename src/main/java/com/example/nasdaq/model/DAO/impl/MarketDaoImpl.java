package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.MarketDao;
import com.example.nasdaq.model.Entity.MarketEntity;
import com.example.nasdaq.model.Repository.MarketRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketDaoImpl implements MarketDao{
    
    @Autowired
    private MarketRepository marketRepository;

    @Override
    public List<MarketEntity> getAllMarketInfo() {
        List<MarketEntity> entities = marketRepository.findAll();
        return entities;
        // return marketRepository.findAll();
    }

    @Override
    public MarketEntity getByMarketInfo(String dt, String marketName){
        MarketEntity entity = marketRepository.findByMarketUpdatesPKDtAndMarketUpdatesPKMarketName(dt, marketName);
        return entity;
    }

    @Override
    public List<MarketEntity> findAllByMarketTitle(String marketTitle) {
        return marketRepository.findAllMarketTitle(marketTitle);
    }

    @Override
    public MarketEntity findByMarketCurrencyAndMarketName(String marketName) {
        // TODO Auto-generated method stub
        return marketRepository.findByMarketCurrencyAndMarketName(marketName);
    }

    // @Override
    // public MarketEntity findAllMarketCurrency(String marketName) { 
    //     return marketRepository.findAllMarketCurrency(marketName);
    // } //수정

}
