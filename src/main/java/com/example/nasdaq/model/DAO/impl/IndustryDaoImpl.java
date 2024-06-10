package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.IndustryDao;
import com.example.nasdaq.model.Entity.IndustryEntity;
import com.example.nasdaq.model.Repository.IndustryRepository;

@Service
public class IndustryDaoImpl implements IndustryDao{
    
    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public IndustryEntity getIndustryAvg(String industry, String dailydate) {
        // TODO Auto-generated method stub
        IndustryEntity entity = industryRepository.getIndustryAvg(industry, dailydate);
        return entity;
    }

    @Override
    public List<IndustryEntity> getAllIndustry(String dailydate) {
        // TODO Auto-generated method stub
        List<IndustryEntity> entities = industryRepository.getAllIndustry(dailydate);
        return entities;
    }

    @Override
    public List<IndustryEntity> getWeeklyInfo(String industry) {
        // TODO Auto-generated method stub
        List<IndustryEntity> entities = industryRepository.findWeeklyIndustryInfo(industry);
        return entities;
    }

    
}
