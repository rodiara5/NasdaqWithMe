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
public class PredictpriceDto {
    private String ticker;

    private String dailydate;

    private float price;

    private float compare_rate;
}
