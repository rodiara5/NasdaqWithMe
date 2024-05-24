package com.example.nasdaq.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.EdgarReportsDao;
import com.example.nasdaq.model.DTO.EdgarReportsDto;
import com.example.nasdaq.model.Entity.EdgarReportsEntity;
import com.example.nasdaq.service.EdgarReportsService;

@Service
public class EdgarReportsServiceImpl implements EdgarReportsService{
    
    @Autowired
    private EdgarReportsDao edgarReportsDao;

    @Override
    public List<EdgarReportsDto> getAllEdgarInfo() {
        // TODO Auto-generated method stub
        List<EdgarReportsEntity> entities = edgarReportsDao.getAllEdgarInfo();
        List<EdgarReportsDto> dtos = new ArrayList<>();
        for(EdgarReportsEntity entity : entities){
            EdgarReportsDto dto = new EdgarReportsDto();
            dto.setTicker(entity.getEdgarReportsPK().getTicker());
            dto.setDate(entity.getEdgarReportsPK().getDate());
            dto.setName(entity.getName());
            dto.setSummary_10q(entity.getSummary_10q());
            dto.setSummary_10k(entity.getSummary_10k());

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public EdgarReportsDto getOneEdgarInfo(String ticker) {
        // TODO Auto-generated method stub
        EdgarReportsEntity entity = edgarReportsDao.getOneEdgarInfo(ticker);
        EdgarReportsDto dto = new EdgarReportsDto();

        dto.setTicker(entity.getEdgarReportsPK().getTicker());
        dto.setDate(entity.getEdgarReportsPK().getDate());
        dto.setName(entity.getName());
        dto.setSummary_10q(entity.getSummary_10q());
        dto.setSummary_10k(entity.getSummary_10k());
        return dto;
    }

}
