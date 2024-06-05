package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.IndustryEntity;

public interface IndustryDao {
    public IndustryEntity getIndustryAvg(String industry, String dailydate);

    public List<IndustryEntity> getAllIndustry(String dailydate);

    public List<IndustryEntity> getWeeklyInfo(String industry);
}
