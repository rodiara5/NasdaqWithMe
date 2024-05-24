package com.example.nasdaq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.Nasdaq100Service;
import com.example.nasdaq.service.StockDataService;

@RestController
@RequestMapping("/api/v1/nasdaq")
public class RestApiController {
    
    @Autowired
    private Nasdaq100Service nasdaq100Service;

    @Autowired
    private DailyUpdateService dailyUpdateService;

    @Autowired
    private StockDataService stockDataService;


    // java script에서 사용할 rest handler
    @GetMapping("/search")
    public Map<String, Object> searchTickers(@RequestParam String ticker){
        List<String> tickers = dailyUpdateService.getTickersContaining(ticker);
        String recent_date = dailyUpdateService.getMostRecentDate();

        Map<String, Object> response = new HashMap<>();
        response.put("tickers", tickers);
        response.put("recent_date", recent_date);

        return response;
    }
}
