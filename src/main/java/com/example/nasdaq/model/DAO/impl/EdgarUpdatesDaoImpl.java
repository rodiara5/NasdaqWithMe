package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.EdgarReportsDao;
import com.example.nasdaq.model.Entity.EdgarReportsEntity;
import com.example.nasdaq.model.Repository.EdgarReportsRepository;

@Service
public class EdgarUpdatesDaoImpl implements EdgarReportsDao{
    
    @Autowired
    private EdgarReportsRepository edgarReportsRepository;

    @Override
    public List<EdgarReportsEntity> getAllEdgarInfo() {
        // TODO Auto-generated method stub
        List<EdgarReportsEntity> entities = edgarReportsRepository.findAll();
        return entities;
    }

    @Override
    public EdgarReportsEntity getOneEdgarInfo(String ticker) {
        // TODO Auto-generated method stub
        EdgarReportsEntity entity = edgarReportsRepository.findByEdgarReportsPKTicker(ticker);
        return entity;
    }

    
}
