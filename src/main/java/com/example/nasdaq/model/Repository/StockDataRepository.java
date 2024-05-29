package com.example.nasdaq.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nasdaq.model.Entity.StockDataEntity;
import com.example.nasdaq.model.Entity.stockDataPK;

public interface StockDataRepository extends JpaRepository<StockDataEntity, stockDataPK>{
    @Query(value = "SELECT ticker, date, open_price, high_price, low_price, close_price, ma20, std, upper, lower FROM stock_data WHERE (ticker = :ticker)", nativeQuery = true)
    public List<StockDataEntity> findStockDataByDateAndTicker(@Param(value = "ticker") String ticker);
}
