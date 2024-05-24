package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.DailyUpdateEntity;
public interface DailyUpdatesDao {
    
    // select
    // public DailyUpdateEntity getOneDailyInfo(String ticker, Date dailydate);
    public DailyUpdateEntity getOneDailyInfo(String ticker, String dailydate);

    public List<DailyUpdateEntity> getAllDailyInfo();

    public String getMostRecentDate();

    public List<DailyUpdateEntity> getTickersContaining(String ticker);
}
