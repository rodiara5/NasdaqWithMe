package com.example.nasdaq.model.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="StockDataEntity")
@Table(name="stock_data")
@ToString
public class StockDataEntity {
    
    @EmbeddedId
    private stockDataPK stockDataPK;
    
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private double ma20;
    private double std;
    private double upper;
    private double lower;
}
