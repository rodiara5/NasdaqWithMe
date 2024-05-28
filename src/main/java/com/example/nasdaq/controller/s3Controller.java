// package com.example.nasdaq.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.nasdaq.model.DTO.DailyUpdateDto;
// import com.example.nasdaq.service.DailyUpdateService;
// import com.example.nasdaq.service.S3Service;

// @Controller
// @RequestMapping("/v1/nasdaq")
// public class s3Controller {

//     @Autowired
//     private S3Service s3Service;

//     @Autowired
//     private DailyUpdateService dailyUpdateService;

//     private static final String BUCKET_NAME = "playdata-team1-bucket";

//     @GetMapping("/s3-test")
//     public String getfromS3(Model model, @RequestParam String ticker) {
//         String recentDate = dailyUpdateService.getMostRecentDate();
//         List<DailyUpdateDto> dtos = dailyUpdateService.getOneDailyInfo(ticker, recentDate);
//         DailyUpdateDto firstDto = dtos.get(0);
//         if(firstDto.getNews_summary().equals("None")){
//             model.addAttribute("news_summary", String.format("오늘은 %s 관련 뉴스가 없습니다!", ticker));
//         } else {
//             List<String> summary_arr = new ArrayList<>();

//             for (DailyUpdateDto dto : dtos) {
//                 String file_path = dto.getNews_summary();
//                 String news_summary = s3Service.getFileFromS3(BUCKET_NAME, file_path);
//                 summary_arr.add(news_summary);
//             }

//             model.addAttribute("news_summary", summary_arr);
//         }

//         return "details2";
//     }

// }
