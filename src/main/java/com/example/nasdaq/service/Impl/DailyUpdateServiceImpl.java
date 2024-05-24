package com.example.nasdaq.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.DailyUpdatesDao;
import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.Entity.DailyUpdateEntity;
import com.example.nasdaq.service.DailyUpdateService;

@Service
public class DailyUpdateServiceImpl implements DailyUpdateService{
    
    @Autowired
    private DailyUpdatesDao dailyUpdatesDao;

    @Override
    public List<DailyUpdateDto> getAllDailyInfo() {
        // TODO Auto-generated method stub
        List<DailyUpdateEntity> entities = dailyUpdatesDao.getAllDailyInfo();
        List<DailyUpdateDto> dtos = new ArrayList<>();

        for(DailyUpdateEntity entity : entities) {
            DailyUpdateDto dto = new DailyUpdateDto();
            dto.setTicker(entity.getDailyUpdatesPK().getTicker());
            dto.setDailydate(entity.getDailyUpdatesPK().getDailydate());
            dto.setName(entity.getName());
            dto.setNews_summary(entity.getNews_summary());
            dto.setNews_sentiment(entity.getNews_sentiment());
            dto.setMarket_cap(entity.getMarket_cap());
            dto.setEnterprise_val(entity.getEnterprise_val());
            dto.setPer(entity.getPer());
            dto.setPbr(entity.getPbr());
            
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public DailyUpdateDto getOneDailyInfo(String ticker, String dailydate) {
        // TODO Auto-generated method stub

        DailyUpdateEntity entity = dailyUpdatesDao.getOneDailyInfo(ticker, dailydate);
        DailyUpdateDto dto = new DailyUpdateDto();
        dto.setTicker(entity.getDailyUpdatesPK().getTicker());
            dto.setDailydate(entity.getDailyUpdatesPK().getDailydate());
            dto.setName(entity.getName());
            dto.setNews_summary(entity.getNews_summary());
            dto.setNews_sentiment(entity.getNews_sentiment());
            dto.setMarket_cap(entity.getMarket_cap());
            dto.setEnterprise_val(entity.getEnterprise_val());
            dto.setPer(entity.getPer());
            dto.setPbr(entity.getPbr());
        return dto;
    }

    @Override
    public String getMostRecentDate() {
        // TODO Auto-generated method stub
        String recent_date = dailyUpdatesDao.getMostRecentDate();
        return recent_date;
    }

    @Override
    public List<String> getTickersContaining(String ticker) {
        // TODO Auto-generated method stub
        List<DailyUpdateEntity> entities = dailyUpdatesDao.getTickersContaining(ticker);
        List<String> tickers = new ArrayList<>();
        for(DailyUpdateEntity entity : entities) {
            String tick = entity.getDailyUpdatesPK().getTicker();
            // 중복으로 들어오는 값 제거
            if(!tickers.contains(tick)){
                tickers.add(tick);
            }
        }
        return tickers;
    }

}
