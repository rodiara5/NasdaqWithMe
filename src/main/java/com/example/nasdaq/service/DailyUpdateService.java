package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.DailyUpdateDto;

public interface DailyUpdateService {
    // select
    public List<DailyUpdateDto> getOneDailyInfo(String ticker, String dailydate);

    public List<DailyUpdateDto> getAllDailyInfo();

    public String getMostRecentDate();

    public List<String> getTickersContaining(String ticker);
}
