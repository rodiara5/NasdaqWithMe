package com.example.nasdaq.model.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.StockDataDao;
import com.example.nasdaq.model.Entity.StockDataEntity;
import com.example.nasdaq.model.Repository.StockDataRepository;

@Service
public class StockDataDaoImpl implements StockDataDao{
    
    @Autowired
    private StockDataRepository stockDataRepository;

    @Override
    public List<StockDataEntity> getAllStockData() {
        // TODO Auto-generated method stub
        List<StockDataEntity> entities = stockDataRepository.findAll();
        return entities;
    }

    @Override
    public List<StockDataEntity> getOneStockData(String ticker) {
        // TODO Auto-generated method stub
        List<StockDataEntity> entities = stockDataRepository.findStockDataByDateAndTicker(ticker);
        return entities;
    }

    
}
