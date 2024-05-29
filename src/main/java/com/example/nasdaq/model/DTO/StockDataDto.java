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
public class StockDataDto {
    
    private String ticker;
    private Long date;
    
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private double ma20;
    private double std;
    private double upper;
    private double lower;
}
