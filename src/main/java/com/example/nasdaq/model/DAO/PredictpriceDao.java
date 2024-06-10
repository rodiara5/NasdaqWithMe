package com.example.nasdaq.model.DAO;

import java.util.List;
import com.example.nasdaq.model.Entity.PredictpriceEntity;

public interface PredictpriceDao {

    public List<PredictpriceEntity> findButtom3predict_price();
    
    public List<PredictpriceEntity> findTop3predict_price();
    
    public List<PredictpriceEntity> getTickerprice(String ticker);
    
        
    
}
