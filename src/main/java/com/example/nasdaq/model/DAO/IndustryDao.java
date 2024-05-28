package com.example.nasdaq.model.DAO;

import com.example.nasdaq.model.Entity.IndustryEntity;

public interface IndustryDao {
    public IndustryEntity getIndustryAvg(String industry, String dailydate);
}
