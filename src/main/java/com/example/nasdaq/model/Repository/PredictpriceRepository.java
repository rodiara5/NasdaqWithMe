package com.example.nasdaq.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.example.nasdaq.model.Entity.PredictpriceEntity;
import com.example.nasdaq.model.Entity.PredictpricePK;

public interface PredictpriceRepository  extends JpaRepository<PredictpriceEntity, PredictpricePK>{
    
    @Query(value = "select * from predict_price p where dailydate like \"2024-05-22%\" order by p.compare_rate desc limit 3", nativeQuery=true)
    List<PredictpriceEntity> findTop3ByorderRateDesc();

    
    @Query(value="SELECT * from predict_price p where dailydate like \"2024-05-22%\"  order by p.compare_rate limit 3", nativeQuery=true)
    List<PredictpriceEntity> findTop3ByorderRateAsc();

    @Query(value="select * from predict_price where (ticker = :ticker) order by dailydate desc limit 5", nativeQuery = true)
    List<PredictpriceEntity> findticker5day(@Param(value = "ticker") String ticker);

}
