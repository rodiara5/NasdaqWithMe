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
public class MarketDto {
    private String dt;

    private String marketName;
    
    private String marketTitle;

    private Double marketClose;
    private Double marketChange;
    private Double PerChange;
}
