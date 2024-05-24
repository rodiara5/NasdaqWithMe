package com.example.nasdaq.service;

import java.util.List;
import java.util.Map;

import com.example.nasdaq.model.DTO.MarketDto;

public interface MarketService {
    public MarketDto getByMarketInfo(String dt, String marketName);
    public List<MarketDto> getAllMarketInfo();
    public List<MarketDto> findAllByMarketTitle(String marketTitle); 

    // public MarketDto findAllMarketCurrency(String marketName); // 수정
    Map<String, Map<String, Double>> getCurrencyRates();

    public Long findByMarketCurrencyAndMarketName(String first, String second, Long money);

}   
