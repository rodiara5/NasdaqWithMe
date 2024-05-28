package com.example.nasdaq.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity(name = "Nasdaq100Entity")
@Table(name = "nasdaq100")
public class Nasdaq100Entity {
    
    @Id
    @NotBlank
    @Column(unique=true)
    private String ticker;
    
    @NotBlank
    private String name;

    private String established;

    private String CEO;

    private String products;

    private String milestones;

    private String industry;

}
