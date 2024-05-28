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
            dto.setTicker(entity.getTicker());
            dto.setDailydate(entity.getDailydate());
            dto.setName(entity.getName());
            dto.setIndustry(entity.getIndustry());
            dto.setNews_summary(entity.getNews_summary());
            dto.setMarket_cap(entity.getMarket_cap());
            dto.setEnterprise_val(entity.getEnterprise_val());
            dto.setPer(entity.getPer());
            dto.setPeg(entity.getPeg());
            dto.setPsr(entity.getPsr());
            dto.setPbr(entity.getPbr());
            dto.setEv_ebitda(entity.getEv_ebitda());
            
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<DailyUpdateDto> getOneDailyInfo(String ticker, String dailydate) {
        // TODO Auto-generated method stub

        List<DailyUpdateEntity> entities = dailyUpdatesDao.getOneDailyInfo(ticker, dailydate);
        List<DailyUpdateDto> dtos = new ArrayList<>();
        for(DailyUpdateEntity entity : entities) {
            DailyUpdateDto dto = new DailyUpdateDto();
        
            dto.setTicker(entity.getTicker());
            dto.setDailydate(entity.getDailydate());
            dto.setName(entity.getName());
            dto.setIndustry(entity.getIndustry());
            dto.setNews_summary(entity.getNews_summary());
            dto.setMarket_cap(entity.getMarket_cap());
            dto.setEnterprise_val(entity.getEnterprise_val());
            dto.setPer(entity.getPer());
            dto.setPeg(entity.getPeg());
            dto.setPsr(entity.getPsr());
            dto.setPbr(entity.getPbr());
            dto.setEv_ebitda(entity.getEv_ebitda());

            dtos.add(dto);
        }
        
        return dtos;
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
            String tick = entity.getTicker();
            // 중복으로 들어오는 값 제거
            if(!tickers.contains(tick)){
                tickers.add(tick);
            }
        }
        return tickers;
    }

}
