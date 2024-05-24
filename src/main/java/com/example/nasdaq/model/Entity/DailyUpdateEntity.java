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
@Entity(name = "DailyUpdateEntity")
@Table(name = "daily_update")
// @IdClass(dailyUpdatesPK.class)
public class DailyUpdateEntity {
    
    // @Id
    // @NotBlank
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name="ticker")
    // private Nasdaq100Entity ticker;

    // @Id
    // @NotBlank
    // private Date dailydate;

    @EmbeddedId
    private dailyUpdatesPK dailyUpdatesPK;

    @NotBlank
    private String name;

    private String news_summary;

    private String news_sentiment;

    private String market_cap;

    private String enterprise_val;

    private double per;

    private double pbr;
}
