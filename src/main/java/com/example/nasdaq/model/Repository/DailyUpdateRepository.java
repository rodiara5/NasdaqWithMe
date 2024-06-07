package com.example.nasdaq.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nasdaq.model.DTO.HottestTickersInterface;
import com.example.nasdaq.model.DTO.TopTickersInterface;
import com.example.nasdaq.model.Entity.DailyUpdateEntity;


public interface DailyUpdateRepository extends JpaRepository<DailyUpdateEntity, Long>{

    // 종목코드와 날짜별로 레코드 리턴
    public List<DailyUpdateEntity> findByTickerAndDailydate(String ticker, String dailydate);

    // 최신 날짜 리턴
    @Query(value = "SELECT dailydate FROM daily_update ORDER BY dailydate DESC LIMIT 1", nativeQuery = true)
    public String findMostRecentDate();

    // 검색 기능 구현할 때 필요한 메소드, 글자 입력과 동시에 존재하는지 찾아본다
    public List<DailyUpdateEntity> findByTickerContainingIgnoreCase(String ticker);

    // 산업군별로 per best 종목 리턴
    @Query(value = "SELECT ticker, name FROM daily_update WHERE industry = :industry AND dailydate = :dailydate ORDER BY market_cap DESC LIMIT 1", nativeQuery = true)
    public TopTickersInterface findBestTickerByIndustry(@Param(value = "industry") String industry, @Param(value = "dailydate") String dailydate);


    @Query(value = "SELECT DISTINCT ticker, name, fluc FROM daily_update WHERE dailydate = :dailydate ORDER BY fluc DESC LIMIT 3;", nativeQuery = true)
    public List<HottestTickersInterface> findTop3TickerByFluc (@Param(value = "dailydate") String dailydate);

    @Query(value = "SELECT ticker, name, fluc FROM daily_update WHERE dailydate = :dailydate ORDER BY fluc ASC LIMIT 3;", nativeQuery = true)
    public List<HottestTickersInterface> findWorst3TickerByFluc (@Param(value = "dailydate") String dailydate);

    // public List<DailyUpdateEntity> findTop7ByTickerOrderByDailydateDesc(String ticker);
    // "WITH RankedData AS (\n" + //
    // "SELECT *, ROW_NUMBER() OVER (PARTITION BY DATE(dailydate)) AS rn\n" + //
    // "FROM daily_update\n" + //
    // "WHERE dailydate >= CURDATE() - INTERVAL 7 DAY\n" + //
    // ")\n" + //
    // "SELECT *\n" + //
    // "FROM RankedData\n" + //
    // "WHERE rn = 1 AND ticker = :ticker;"
    @Query(value = "WITH RankedData AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY DATE(dailydate)) AS rn FROM daily_update WHERE dailydate >= CURDATE() - INTERVAL 7 DAY AND ticker = :ticker) SELECT * FROM RankedData WHERE rn = 1", nativeQuery = true)
    public List<DailyUpdateEntity> findWeeklyTickerInfo(@Param(value = "ticker") String ticker);
}
