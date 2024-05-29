package com.example.nasdaq.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.StockDataDao;
import com.example.nasdaq.model.DTO.StockDataDto;
import com.example.nasdaq.model.Entity.StockDataEntity;
import com.example.nasdaq.service.StockDataService;

@Service
public class StockDataServiceImpl implements StockDataService{
    
    @Autowired
    private StockDataDao stockDataDao;

    @Override
    public List<StockDataDto> getAllStockData() {
        // TODO Auto-generated method stub
        List<StockDataEntity> entities = stockDataDao.getAllStockData();
        List<StockDataDto> dtos = new ArrayList<>();

        for(StockDataEntity entity : entities){
            StockDataDto dto = new StockDataDto();
            dto.setTicker(entity.getStockDataPK().getTicker());
            dto.setDate(entity.getStockDataPK().getDate());
            dto.setOpenPrice(entity.getOpenPrice());
            dto.setHighPrice(entity.getHighPrice());
            dto.setLowPrice(entity.getLowPrice());
            dto.setClosePrice(entity.getClosePrice());
            dto.setMa20(entity.getMa20());
            dto.setStd(entity.getStd());
            dto.setOpenPrice(entity.getOpenPrice());
            dto.setClosePrice(entity.getClosePrice());

            dtos.add(dto);
        }
        
        return dtos;
    }

    @Override
    public List<StockDataDto> getOneStockData(String ticker) {
        // TODO Auto-generated method stub
        List<StockDataEntity> entities = stockDataDao.getOneStockData(ticker);
        List<StockDataDto> dtos = new ArrayList<>();

        for(StockDataEntity entity : entities){
            StockDataDto dto = new StockDataDto();
            dto.setTicker(entity.getStockDataPK().getTicker());
            dto.setDate(entity.getStockDataPK().getDate());
            dto.setOpenPrice(entity.getOpenPrice());
            dto.setHighPrice(entity.getHighPrice());
            dto.setLowPrice(entity.getLowPrice());
            dto.setClosePrice(entity.getClosePrice());
            dto.setMa20(entity.getMa20());
            dto.setStd(entity.getStd());
            dto.setOpenPrice(entity.getOpenPrice());
            dto.setClosePrice(entity.getClosePrice());

            dtos.add(dto);
        }
        
        return dtos;
    }

    
}
