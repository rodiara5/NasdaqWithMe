package com.example.nasdaq.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nasdaq.model.Entity.IndustryEntity;
import com.example.nasdaq.model.Entity.IndustryPK;

public interface IndustryRepository extends JpaRepository<IndustryEntity, IndustryPK> {
    
    @Query(value = "SELECT industry, dailydate, AVG(market_cap) as avg_Market_Cap, AVG(per) as avgPER, AVG(psr) as avgPSR, AVG(pbr) as avgPBR, AVG(ev_ebitda) as avgEV_EBITDA, AVG(fluc) as avg_fluc FROM daily_update WHERE industry = :industry and dailydate = :dailydate GROUP BY industry, dailydate;", nativeQuery = true)
    public IndustryEntity getIndustryAvg(@Param(value = "industry") String industry, @Param(value = "dailydate") String dailydate);

    @Query(value = "SELECT industry, dailydate, avg_market_cap, avgper, avgpsr, avgpbr, avgev_ebitda, avg_fluc FROM industry WHERE dailydate = :dailydate and industry != 'NASDAQ Index' AND industry IN (SELECT DISTINCT industry FROM daily_update)ORDER BY avg_fluc DESC LIMIT 3", nativeQuery = true)
    public List<IndustryEntity> getAllIndustry(@Param(value = "dailydate") String dailydate);

    @Query(value = "SELECT industry, dailydate, avg_market_cap, avgper, avgpsr, avgpbr, avgev_ebitda, avg_fluc FROM industry WHERE dailydate = :dailydate and industry = 'NASDAQ Index'", nativeQuery = true)
    public IndustryEntity getNasdaqIndex(@Param(value = "dailydate") String dailydate);

    public List<IndustryEntity> findTop7ByIndustryPKIndustryOrderByIndustryPKDailydateDesc(String Industry);
}
