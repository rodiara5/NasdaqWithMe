package com.example.nasdaq.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nasdaq.model.Entity.MarketEntity;
import com.example.nasdaq.model.Entity.MarketUpdatesPK;

public interface MarketRepository extends JpaRepository<MarketEntity, MarketUpdatesPK> {
    public MarketEntity findByMarketUpdatesPKDtAndMarketUpdatesPKMarketName(String dt, String marketName);

    public List<MarketEntity> findByMarketUpdatesPKDt(String dt);

    @Query(value = "select * from daily_market where market_title=:marketTitle", nativeQuery = true)
    public List<MarketEntity> findAllMarketTitle(@Param(value = "marketTitle") String marketTitle);

    @Query(value = "SELECT * FROM daily_market WHERE market_title='Currencies' and market_name=:marketName", nativeQuery = true)
    public MarketEntity findByMarketCurrencyAndMarketName(@Param(value = "marketName") String marketName);  //수정
}
