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
public class EdgarReportsDto {
    
    private String ticker;

    private String date;

    private String name;
    
    private String summary_10q;
    private String summary_10k;

}
