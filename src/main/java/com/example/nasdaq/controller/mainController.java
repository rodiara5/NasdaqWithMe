package com.example.nasdaq.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nasdaq.model.DTO.IndustryDto;
import com.example.nasdaq.model.DTO.TopTickersInterface;
import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.IndustryService;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("/v1/nasdaq")
@Controller
@Slf4j
public class mainController {

    @Autowired
    private DailyUpdateService dailyUpdateService;

    @Autowired
    private IndustryService industryService;

    @GetMapping("/main")
    public String mainPage(Model model) {

    String recentDate = dailyUpdateService.getMostRecentDate();
    List<IndustryDto> dtos = industryService.getAllIndustry();
    Map<String, List<List<String>>> topFive = new HashMap<>();

    int count = 1;
    for (IndustryDto dto : dtos) {
        String industry = dto.getIndustry();
        model.addAttribute(String.format("industry%d", count), industry);
        

        List<TopTickersInterface> tickers_names = dailyUpdateService.getTop5TickersByIndustry(industry, recentDate);
        
        List<List<String>> list = topFive.getOrDefault(industry, new ArrayList<>());
        for (TopTickersInterface ticker_name : tickers_names) {
            List<String> tickerInfo = new ArrayList<>();
            String ticker = ticker_name.getTicker();
            String name = ticker_name.getName();
            tickerInfo.add(ticker);
            tickerInfo.add(name);
            list.add(tickerInfo);
        }
        
        topFive.put(industry, list);
        model.addAttribute(String.format("ticker%d", count), topFive.get(industry));
        count++;
    }

    return "main";
}

    @GetMapping("/index_detail")
    public String getdetails(@RequestParam String ticker) {
        String recentDate = dailyUpdateService.getMostRecentDate();
        String url = String.format("/v1/nasdaq/details?ticker=%s&dailydate=%s", ticker, recentDate);
        log.info(ticker);
        return "redirect:" + url;
    }
    
}
