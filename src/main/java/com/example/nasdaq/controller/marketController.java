package com.example.nasdaq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nasdaq.model.DTO.MarketDto;
import com.example.nasdaq.model.Repository.MarketRepository;
import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.MarketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/v1/nasdaq")
public class marketController {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MarketService marketService;

    @Autowired
    private DailyUpdateService dailyUpdateService;


    @GetMapping("/market")
    public String marketPage(Model model, @RequestParam(defaultValue = "Futures") String marketTitle, String marketName){
        List<MarketDto> dtos = marketService.findAllByMarketTitle(marketTitle);
        String title = dtos.get(0).getMarketTitle().toString();
    
        String close = dtos.get(0).getMarketClose().toString();

        // MarketDto dto = marketService.findAllMarketCurrency(marketName); //수정

        log.info("dtos: " + dtos);

        model.addAttribute("marketList", dtos);
        model.addAttribute("marketTitle", title);
        model.addAttribute("marketDt", dailyUpdateService.getMostRecentDate());
        model.addAttribute("marketClose", close);

        
        // model.addAttribute("currency_list", dto); //수정

        return "market";
    }

    //환율 계산기 테스트용
    @GetMapping("/currency-converter")
    public String getCurrencyConverter(Model model) {
        Map<String, Map<String, Double>> currencyRates = marketService.getCurrencyRates();
        model.addAttribute("currencyRates", currencyRates);
        return "exchange";
    }
    
    @GetMapping("/converter")
    public Long converter(@RequestParam String first, @RequestParam String second, @RequestParam String money) {
        Long lmoney = Long.valueOf(money);
        log.info(marketService.findByMarketCurrencyAndMarketName(first, second, lmoney).toString());
        return marketService.findByMarketCurrencyAndMarketName(first, second, lmoney);
    }
}
