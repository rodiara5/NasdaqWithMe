package com.example.nasdaq.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.DTO.IndustryDto;
import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.EdgarReportsService;
import com.example.nasdaq.service.IndustryService;
import com.example.nasdaq.service.Nasdaq100Service;
import com.example.nasdaq.service.S3Service;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/v1/nasdaq")
@Controller
@Slf4j
public class detailsController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private DailyUpdateService dailyUpdateService;

    @Autowired
    private EdgarReportsService edgarReportsService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private Nasdaq100Service nasdaq100Service;

    private static final String BUCKET_NAME = "playdata-team1-bucket";


    @GetMapping("/details")
    public String detailsPage(Model model, @RequestParam String ticker){
        String recentDate = dailyUpdateService.getMostRecentDate();
        List<DailyUpdateDto> dtos = dailyUpdateService.getOneDailyInfo(ticker, recentDate);
        DailyUpdateDto firstDto = dtos.get(0);
        if(firstDto.getNews_summary().equals("None")){
            model.addAttribute("summary", String.format("오늘은 %s 관련 뉴스가 없습니다!", ticker));
        } else {
            List<String> summary_arr = new ArrayList<>();

            for (DailyUpdateDto dto : dtos) {
                String file_path = dto.getNews_summary();
                String news_summary = s3Service.getFileFromS3(BUCKET_NAME, file_path);
                summary_arr.add(news_summary);
            }

            model.addAttribute("summary", summary_arr);
        }
        log.info("[detailsController] firstDto >>> "+firstDto);
        log.info("[detailsController] industry >>> "+firstDto.getIndustry());

        IndustryDto industryDto = industryService.getIndustryAvg(firstDto.getIndustry());
        log.info("[detailsController] industryDto >>> "+industryDto);

        model.addAttribute("Industry", industryDto.getIndustry());
        model.addAttribute("avgPER", roundToTwoDecimalPlaces(industryDto.getAvgPER()));
        model.addAttribute("avgPSR", roundToTwoDecimalPlaces(industryDto.getAvgPSR()));
        model.addAttribute("avgPBR", roundToTwoDecimalPlaces(industryDto.getAvgPBR()));
        model.addAttribute("avgEV_EBITDA", roundToTwoDecimalPlaces(industryDto.getAvgEV_EBITDA()));

        model.addAttribute("nasdaq100", nasdaq100Service.findByTicker(ticker));
        model.addAttribute("company", firstDto);
        // 숫자를 포맷팅하여 세 자리마다 쉼표 추가
        model.addAttribute("MarketCap", formatNumber(firstDto.getMarket_cap()));
        // model.addAttribute("edgar", edgarReportsService.getOneEdgarInfo(ticker));

        return "details";

    }

    @GetMapping("/search")
    public String searchTicker(@RequestParam String ticker){
        String recent_date = dailyUpdateService.getMostRecentDate();
        String url = String.format("/v1/nasdaq/details?ticker=%s&dailydate=%s", ticker, recent_date);
        return "redirect:" + url;
    }

    public static double roundToTwoDecimalPlaces(double num) {
        // 소수 둘째 자리까지 확장하여 반올림
        double roundedNum = Math.round(num * 100.0) / 100.0;
        return roundedNum;
    }

    public static String formatNumber(long number) {
        double result;
        String unit;

        if (number >= 1_000_000_000_000L) {
            result = number / 1_000_000_000_000.0;
            unit = "조";
        } else if (number >= 1_000_000_000L) {
            result = number / 1_000_000_000.0;
            unit = "억";
        } else if (number >= 1_000_000L) {
            result = number / 1_000_000.0;
            unit = "백만";
        } else {
            result = number;
            unit = "";
        }

        return String.format("%.1f%s", result, unit);
    }
}
