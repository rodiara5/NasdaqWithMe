package com.example.nasdaq.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.MarketDao;
import com.example.nasdaq.model.DTO.MarketDto;
import com.example.nasdaq.model.Entity.MarketEntity;
import com.example.nasdaq.model.Repository.MarketRepository;
import com.example.nasdaq.service.MarketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarketServiceImpl implements MarketService {

    private static final Logger logger = LoggerFactory.getLogger(MarketServiceImpl.class);
    
    @Autowired
    private MarketDao marketDao;

    @Override
    public MarketDto getByMarketInfo(String dt, String marketName) {
        // TODO Auto-generated method stub
        MarketEntity entity = marketDao.getByMarketInfo(dt, marketName);
        MarketDto dto = new MarketDto();

        if (entity == null) {
            logger.error("MarketEntity is null for dt: {} and marketName: {}", dt, marketName);
            return null; // 적절한 기본값이나 예외 처리
        }

        dto.setDt(entity.getMarketUpdatesPK().getDt());
        dto.setMarketName(entity.getMarketUpdatesPK().getMarketName());
        
        dto.setMarketTitle(entity.getMarketTitle());
        dto.setMarketClose(entity.getMarketClose());
        dto.setMarketChange(entity.getMarketChange());
        dto.setPerChange(entity.getPerChange());

        return dto;
    }

    @Override
    public List<MarketDto> getAllMarketInfo() {
        // TODO Auto-generated method stub
        List<MarketEntity> entities = marketDao.getAllMarketInfo();
        List<MarketDto> dtos = new ArrayList<>();

        for (MarketEntity entity : entities) {
            MarketDto dto = new MarketDto();
            dto.setDt(entity.getMarketUpdatesPK().getDt());
            dto.setMarketName(entity.getMarketUpdatesPK().getMarketName());
            
            dto.setMarketTitle(entity.getMarketTitle());
            dto.setMarketClose(entity.getMarketClose());
            dto.setMarketChange(entity.getMarketChange());
            dto.setPerChange(entity.getPerChange());

            dtos.add(dto);
        }
        return dtos;
    }


    @Override
    public List<MarketDto> findAllByMarketTitle(String marketTitle) {
        List<MarketEntity> entities = marketDao.findAllByMarketTitle(marketTitle);
        List<MarketDto> dtos = new ArrayList<>();

        for (MarketEntity entity : entities) {
            MarketDto dto = new MarketDto();
            dto.setDt(entity.getMarketUpdatesPK().getDt());
            dto.setMarketName(entity.getMarketUpdatesPK().getMarketName());
            
            dto.setMarketTitle(entity.getMarketTitle());
            dto.setMarketClose(entity.getMarketClose());
            dto.setMarketChange(entity.getMarketChange());
            dto.setPerChange(entity.getPerChange());

            dtos.add(dto);
        }
        return dtos;
    }

    // @Override
    // public MarketDto findAllMarketCurrency(String marketName){
    //     MarketEntity entity = marketDao.findAllMarketCurrency(marketName);

    //     MarketDto dto = new MarketDto();
    //     dto.setDt(entity.getMarketUpdatesPK().getDt());
    //     dto.setMarketName(entity.getMarketUpdatesPK().getMarketName());
            
    //     dto.setMarketTitle(entity.getMarketTitle());
    //     dto.setMarketClose(entity.getMarketClose());
    //     dto.setMarketChange(entity.getMarketChange());
    //     dto.setPerChange(entity.getPerChange());
        
    //     return dto;
    // } //수정

    //버릴 수도 있음
    @Autowired
    private MarketRepository marketRepository;

    @Override
    public Long findByMarketCurrencyAndMarketName(String first, String second, Long money) {
        String marketName = first+"/"+second;
        MarketEntity entity = marketDao.findByMarketCurrencyAndMarketName(marketName);
        Double result = entity.getMarketClose() * money;
        Long trueresult = Math.round(result);
        return trueresult;
    }

    public Map<String, Map<String, Double>> getCurrencyRates() {
        List<MarketEntity> currencies = marketRepository.findAllMarketTitle("Currencies");
        Map<String, Map<String, Double>> currencyRatio = new HashMap<>();

        for (MarketEntity currency : currencies) {
            MarketDto dto = new MarketDto();
            String[] names = currency.getMarketUpdatesPK().getMarketName().split("/");
            String fromCurrency = names[0];
            String toCurrency = names[1];
            Double rate = currency.getMarketClose();

            currencyRatio
                    .computeIfAbsent(fromCurrency, k -> new HashMap<>())
                    .put(toCurrency, rate);
            currencyRatio
                    .computeIfAbsent(toCurrency, k -> new HashMap<>())
                    .put(fromCurrency, 1 / rate);
        }

        return currencyRatio;
    }
}
