package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.DTO.HottestTickersInterface;
import com.example.nasdaq.model.DTO.TopTickersInterface;

public interface DailyUpdateService {
    // select
    public List<DailyUpdateDto> getOneDailyInfo(String ticker, String dailydate);

    public String getMostRecentDate();

    public List<String> getTickersContaining(String ticker);

    public TopTickersInterface getBestTickersByIndustry(String industry, String dailydate);

    public List<HottestTickersInterface> getTop3TickersByFluc(String dailydate);

    public List<HottestTickersInterface> getWorst3TickersByFluc(String dailydate);

    public List<DailyUpdateDto> getWeeklyInfo(String ticker);
}
