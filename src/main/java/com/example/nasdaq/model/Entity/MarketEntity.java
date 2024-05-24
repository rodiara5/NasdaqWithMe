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
@Entity(name="MarketEntity")
@Table(name="daily_market")
public class MarketEntity {
    
    // @Id
    // @NotBlank
    // @Column(unique = true)
    // private String marketName;

    // @Id
    // @PastOrPresent
    // private Date postDate; 
    // //  나중에 type String으로 바꾸면 얘도 바꿔줘야함
    
    @EmbeddedId
    private MarketUpdatesPK marketUpdatesPK;

    @NotBlank
    private String marketTitle;


    private double marketClose;

    private double marketChange;

    private double PerChange;
    // private double marketVolumn;

}
