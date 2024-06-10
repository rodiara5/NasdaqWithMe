package com.example.nasdaq.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.PredictpriceDao;
import com.example.nasdaq.model.DTO.PredictpriceDto;
import com.example.nasdaq.model.Entity.PredictpriceEntity;
import com.example.nasdaq.service.PredictpriceService;
import java.util.List;
import java.util.ArrayList;

@Service
public class PredictpriceServiceImpl implements PredictpriceService{
    
    @Autowired
    private PredictpriceDao predictpricedao;

    @Override
    public List<PredictpriceDto> getTop3predict_price() {
        // TODO Auto-generated method stub

        List<PredictpriceEntity> entities = predictpricedao.findTop3predict_price();
        List<PredictpriceDto> dtos = new ArrayList<>();

        for(PredictpriceEntity entity : entities) {
            PredictpriceDto dto = new PredictpriceDto();
            dto.setTicker(entity.getPredictpricePK().getTicker());
            dto.setDailydate(entity.getPredictpricePK().getDailydate());
            dto.setPrice(entity.getPrice());
            dto.setCompare_rate(entity.getCompare_rate());

            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public List<PredictpriceDto> getButtom3predict_price() {
        // TODO Auto-generated method stub

        List<PredictpriceEntity> entities = predictpricedao.findButtom3predict_price();
        List<PredictpriceDto> dtos = new ArrayList<>();

        for(PredictpriceEntity entity : entities) {
            PredictpriceDto dto = new PredictpriceDto();
            dto.setTicker(entity.getPredictpricePK().getTicker());
            dto.setDailydate(entity.getPredictpricePK().getDailydate());
            dto.setPrice(entity.getPrice());
            dto.setCompare_rate(entity.getCompare_rate());

            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public List<PredictpriceDto> getTickerprice(String ticker){

        List<PredictpriceEntity> entities = predictpricedao.getTickerprice(ticker);
        List<PredictpriceDto> dtos = new ArrayList<>();

        for (int i = entities.size() - 1; i >= 0; i--) {
            PredictpriceEntity entity = entities.get(i);
            PredictpriceDto dto = new PredictpriceDto();
            dto.setTicker(entity.getPredictpricePK().getTicker());
            dto.setDailydate(entity.getPredictpricePK().getDailydate());
            dto.setPrice(entity.getPrice());
            dto.setCompare_rate(entity.getCompare_rate());
        
            dtos.add(dto);
        }

        return dtos;
    }
}
