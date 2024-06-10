package com.example.nasdaq.service;

import com.example.nasdaq.model.DTO.PredictpriceDto;

import java.util.List;

public interface PredictpriceService {
    
     public  List<PredictpriceDto> getTop3predict_price();

     public List<PredictpriceDto> getButtom3predict_price() ;

     public List<PredictpriceDto> getTickerprice(String ticker);
}
