package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.DTO.TopTickersInterface;

public interface DailyUpdateService {
    // select
    public List<DailyUpdateDto> getOneDailyInfo(String ticker, String dailydate);

    public String getMostRecentDate();

    public List<String> getTickersContaining(String ticker);

    public TopTickersInterface getBestTickersByIndustry(String industry, String dailydate);
}
