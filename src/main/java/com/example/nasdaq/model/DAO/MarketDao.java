package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.MarketEntity;

public interface MarketDao {
    public MarketEntity getByMarketInfo(String dt, String marketName);
    
    public List<MarketEntity> getAllMarketInfo();

    public List<MarketEntity> findAllByMarketTitle(String marketTitle);

    public MarketEntity findByMarketCurrencyAndMarketName(String marketName);

    // public MarketEntity findAllMarketCurrency(String marketName); //수정
}
