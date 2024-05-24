package com.example.nasdaq.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nasdaq.model.Entity.Nasdaq100Entity;

public interface Nasdaq100Repository extends JpaRepository<Nasdaq100Entity, String>{
    
    public Nasdaq100Entity findByTicker(String ticker);
}
