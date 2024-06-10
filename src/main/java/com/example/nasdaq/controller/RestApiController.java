package com.example.nasdaq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.DTO.IndustryDto;
import com.example.nasdaq.model.DTO.PredictpriceDto;
import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.IndustryService;
import com.example.nasdaq.service.PredictpriceService;

import lombok.extern.slf4j.Slf4j;
import com.example.nasdaq.service.WatchlistService;

@RestController
@RequestMapping("/api/v1/nasdaq")
@Slf4j
public class RestApiController {
    

    @Autowired
    private DailyUpdateService dailyUpdateService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private PredictpriceService predictpriceService;

    private WatchlistService watchlistService;

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

    @GetMapping("/industries")
    public List<IndustryDto> industries() {
        List<IndustryDto> dtos = industryService.getAllIndustry();
        return dtos;
    }

    @GetMapping("/industry")
    public List<IndustryDto> industry(@RequestParam String industry) {
        List<IndustryDto> dtos = industryService.getWeeklyInfo(industry);
        return dtos;
    }

    @GetMapping("/ratios")
    public List<DailyUpdateDto> dailyUpdates(@RequestParam String ticker) {
        // String recentDate = dailyUpdateService.getMostRecentDate();
        List<DailyUpdateDto> dtos = dailyUpdateService.getWeeklyInfo(ticker);
        return dtos;
    }
    @GetMapping("/price")
    public List<PredictpriceDto> getprice(@RequestParam String ticker) {
        List<PredictpriceDto> dtos = predictpriceService.getTickerprice(ticker);
        log.info("dtos: " + dtos);
        return dtos;

        

    }

    @PostMapping("/addWatchlist")
    public ResponseEntity<Integer> addWatchlist(@RequestParam String userId, @RequestParam String ticker, @RequestParam String name){
        Integer result = watchlistService.toggleWatchlist(userId, ticker, name);
        System.out.println(result+"시발");
        return ResponseEntity.ok(result);
    }
}
