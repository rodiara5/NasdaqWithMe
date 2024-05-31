package com.example.nasdaq.model.DAO.impl;

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
        IndustryEntity entity = industryRepository.getIndustryAvg(industry, dailydate);
        return entity;
    }

    
}
