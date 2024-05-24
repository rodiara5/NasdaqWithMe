package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.EdgarReportsDto;

public interface EdgarReportsService {
    public EdgarReportsDto getOneEdgarInfo(String ticker);

    public List<EdgarReportsDto> getAllEdgarInfo();
}
