package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.IndustryDto;

public interface IndustryService {
    public IndustryDto getIndustryAvg(String industry);

    public List<IndustryDto> getAllIndustry();

    public List<IndustryDto> getWeeklyInfo(String industry);
}
