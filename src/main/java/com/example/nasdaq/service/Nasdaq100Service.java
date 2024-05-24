package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.Nasdaq100Dto;

public interface Nasdaq100Service {
    
    // select
    public Nasdaq100Dto findByTicker(String ticker);

    public List<Nasdaq100Dto> getAllNasdaq100();
}
