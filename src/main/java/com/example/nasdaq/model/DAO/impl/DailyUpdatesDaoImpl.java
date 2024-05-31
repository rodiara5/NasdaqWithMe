package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.DailyUpdatesDao;
import com.example.nasdaq.model.Entity.DailyUpdateEntity;
import com.example.nasdaq.model.Repository.DailyUpdateRepository;

@Service
public class DailyUpdatesDaoImpl implements DailyUpdatesDao{
    
    @Autowired
    private DailyUpdateRepository dailyUpdateRepository;

    @Override
    public List<DailyUpdateEntity> getAllDailyInfo() {
        List<DailyUpdateEntity> entities = dailyUpdateRepository.findAllByOrderByDailydateDesc();
        return entities;
    }

    @Override
    public List<DailyUpdateEntity> getOneDailyInfo(String ticker, String dailydate) {
        List<DailyUpdateEntity> entities = dailyUpdateRepository.findByTickerAndDailydate(ticker, dailydate);
        return entities;
    }

    @Override
    public String getMostRecentDate() {
        String recent_date = dailyUpdateRepository.findMostRecentDate();
        return recent_date;
    }

    @Override
    public List<DailyUpdateEntity> getTickersContaining(String ticker) {
        List<DailyUpdateEntity> tickers = dailyUpdateRepository.findByTickerContainingIgnoreCase(ticker);
        return tickers;
    }

}
