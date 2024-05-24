package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.StockDataDto;

public interface StockDataService {
    
    public List<StockDataDto> getAllStockData();

    public List<StockDataDto> getOneStockData(String ticker);
}
