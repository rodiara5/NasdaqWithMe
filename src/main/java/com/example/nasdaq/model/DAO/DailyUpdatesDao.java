package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.DailyUpdateEntity;
public interface DailyUpdatesDao {
    
    // select
    public List<DailyUpdateEntity> getOneDailyInfo(String ticker, String dailydate);

    public String getMostRecentDate();

    public List<DailyUpdateEntity> getTickersContaining(String ticker);

    public List<DailyUpdateEntity> getWeeklyInfo(String ticker);
}
