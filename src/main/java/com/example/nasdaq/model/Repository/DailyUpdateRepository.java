package com.example.nasdaq.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.nasdaq.model.Entity.DailyUpdateEntity;


public interface DailyUpdateRepository extends JpaRepository<DailyUpdateEntity, Long>{
    // public DailyUpdateEntity findByDailyUpdatesPKTickerAndDailyUpdatesPKDailydate(String ticker, String dailydate);
    public List<DailyUpdateEntity> findByTickerAndDailydate(String ticker, String dailydate);

    // public List<DailyUpdateEntity> findAllByOrderByDailyUpdatesPKDailydateDesc();
    public List<DailyUpdateEntity> findAllByOrderByDailydateDesc();

    @Query(value = "SELECT dailydate FROM daily_update ORDER BY dailydate DESC LIMIT 1", nativeQuery = true)
    public String findMostRecentDate();

    // public List<DailyUpdateEntity> findByDailyUpdatesPKTickerContainingIgnoreCase(String ticker);
    public List<DailyUpdateEntity> findByTickerContainingIgnoreCase(String ticker);

}
