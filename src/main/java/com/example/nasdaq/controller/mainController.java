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

import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.DTO.PredictpriceDto;
import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.PredictpriceService;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("/v1/nasdaq")
@Controller
@Slf4j
public class mainController {

    @Autowired
    private PredictpriceService predictpriceservice;

    @Autowired
    private DailyUpdateService dailyUpdateService;

    @GetMapping("/index")
    public String mainPage(Model model){
        
        List<DailyUpdateDto> positive_news = new ArrayList<>();
        List<DailyUpdateDto> negative_news =  new ArrayList<>();
        List<PredictpriceDto> postiive_dtos = predictpriceservice.getTop3predict_price();
        List<PredictpriceDto> negative_dtos = predictpriceservice.getButtom3predict_price();
        List<Map<String, Object>> negative_combinedData = new ArrayList<>();
        List<Map<String, Object>> positive_combinedData = new ArrayList<>();
        
        // for(PredictpriceDto dto:postiive_dtos){
        //     DailyUpdateDto Ddto = dailyUpdateService.getOneDailyInfo(dto.getTicker(), "2024-05-20");
        //     positive_news.add(Ddto);

        // }
        // for(PredictpriceDto dto:negative_dtos){
        //     DailyUpdateDto Ddto = dailyUpdateService.getOneDailyInfo(dto.getTicker(), "2024-05-20");
        //     negative_news.add(Ddto);
        // }
        for(int i = 0; i<negative_dtos.size();i++){
            Map<String, Object> map = new HashMap<>();
            map.put("ticker",negative_dtos.get(i).getTicker());
            map.put("compare_rate",negative_dtos.get(i).getCompare_rate());
            // map.put("news_summary",negative_news.get(i).getNews_summary());
            negative_combinedData.add(map);
        }

        for(int i = 0; i<postiive_dtos.size();i++){
            Map<String, Object> map = new HashMap<>();
            map.put("ticker",postiive_dtos.get(i).getTicker());
            map.put("compare_rate",postiive_dtos.get(i).getCompare_rate());
            // map.put("news_summary",positive_news.get(i).getNews_summary());
            positive_combinedData.add(map);
        }
        model.addAttribute("positive", positive_combinedData);
        model.addAttribute("negative", negative_combinedData);



        
        return "index";
    }

    @GetMapping("/index_detail")
    public String getdeatils(@RequestParam String ticker) {
        String recent_date = dailyUpdateService.getMostRecentDate();
        String url = String.format("/v1/nasdaq/details?ticker=%s&dailydate=%s", ticker, recent_date);
        log.info(ticker);
        return "redirect:" + url;
    }
    
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }


}
