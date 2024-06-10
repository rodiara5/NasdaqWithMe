package com.example.nasdaq.model.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.nasdaq.model.DAO.PredictpriceDao;
import com.example.nasdaq.model.Entity.PredictpriceEntity;
import com.example.nasdaq.model.Repository.PredictpriceRepository;

@Service
public class PredictpriceDaoImpl implements PredictpriceDao{

    @Autowired
    private PredictpriceRepository PredictpriceRepository;
    @Override
    public List<PredictpriceEntity> findButtom3predict_price() {
        // TODO Auto-generated method stub
       
        return PredictpriceRepository.findTop3ByorderRateAsc();
    }

    @Override
    public List<PredictpriceEntity> findTop3predict_price() {
        // TODO Auto-generated method stub
       
        return PredictpriceRepository.findTop3ByorderRateDesc();
    }

    @Override
    public List<PredictpriceEntity> getTickerprice(String ticker) {

        return PredictpriceRepository.findticker5day(ticker);
    }

}
