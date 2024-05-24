package com.example.nasdaq.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DailyUpdateDto {
    private String ticker;

    private String dailydate;

    private String name;

    private String news_summary;

    private String news_sentiment;

    private String market_cap;

    private String enterprise_val;

    private double per;

    private double pbr;
}
