package com.example.nasdaq.model.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Entity(name = "predict_price")
@Table(name = "predict_price")
public class PredictpriceEntity {

    @EmbeddedId
    private PredictpricePK predictpricePK;

    @NotBlank
    private float price;
    
    private float compare_rate;
}
