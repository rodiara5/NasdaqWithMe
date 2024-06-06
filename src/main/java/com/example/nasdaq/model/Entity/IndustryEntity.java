package com.example.nasdaq.model.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="IndustryEntity")
@Table(name="industry")
@ToString
public class IndustryEntity {
    
    @EmbeddedId
    private IndustryPK industryPK;

    private double avgMarketCap;

    private double avgPER;

    private double avgPSR;

    private double avgPBR;

    private double avgEV_EBITDA;

    private double avgFluc;
}
