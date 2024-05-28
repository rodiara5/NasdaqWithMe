package com.example.nasdaq.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nasdaq.model.Entity.IndustryEntity;
import com.example.nasdaq.model.Entity.IndustryPK;

public interface IndustryRepository extends JpaRepository<IndustryEntity, IndustryPK> {
    
    @Query(value = "SELECT industry, dailydate, AVG(per) as avgPER, AVG(peg) as avgPEG, AVG(psr) as avgPSR, AVG(pbr) as avgPBR, AVG(ev_ebitda) as avgEV_EBITDA FROM daily_update WHERE industry = :industry and dailydate = :dailydate GROUP BY industry, dailydate;", nativeQuery = true)
    public IndustryEntity getIndustryAvg(@Param(value = "industry") String industry, @Param(value = "dailydate") String dailydate);
}
