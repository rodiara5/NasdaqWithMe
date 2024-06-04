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
    @Query(value = "SELECT ticker, name FROM daily_update WHERE industry = :industry AND dailydate = :dailydate ORDER BY per DESC LIMIT 1", nativeQuery = true)
    public TopTickersInterface findBestTickerByIndustry(@Param(value = "industry") String industry, @Param(value = "dailydate") String dailydate);


    @Query(value = "SELECT ticker, name, fluc FROM daily_update WHERE dailydate = :dailydate ORDER BY fluc DESC LIMIT 3;", nativeQuery = true)
    public List<HottestTickersInterface> findTop3TickerByFluc (@Param(value = "dailydate") String dailydate);

    @Query(value = "SELECT ticker, name, fluc FROM daily_update WHERE dailydate = :dailydate ORDER BY fluc ASC LIMIT 3;", nativeQuery = true)
    public List<HottestTickersInterface> findWorst3TickerByFluc (@Param(value = "dailydate") String dailydate);
}
