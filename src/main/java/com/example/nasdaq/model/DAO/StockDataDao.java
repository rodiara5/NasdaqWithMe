package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.StockDataEntity;

public interface StockDataDao {
    
    public List<StockDataEntity> getAllStockData();

    public List<StockDataEntity> getOneStockData(String ticker);

}
