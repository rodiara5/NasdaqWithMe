package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.Nasdaq100Dao;
import com.example.nasdaq.model.Entity.Nasdaq100Entity;
import com.example.nasdaq.model.Repository.Nasdaq100Repository;

@Service
public class Nasdaq100DaoImpl implements Nasdaq100Dao{
    
    @Autowired
    private Nasdaq100Repository nasdaq100Repository;

    @Override
    public List<Nasdaq100Entity> getAllNasdaq100() {
        // TODO Auto-generated method stub
        return nasdaq100Repository.findAll();
    }

    @Override
    public Nasdaq100Entity getByTicker(String ticker) {
        // TODO Auto-generated method stub
        return nasdaq100Repository.findByTicker(ticker);
    }

    
}
