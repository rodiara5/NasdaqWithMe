package com.example.nasdaq.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.DailyUpdatesDao;
import com.example.nasdaq.model.DAO.IndustryDao;
import com.example.nasdaq.model.DTO.IndustryDto;
import com.example.nasdaq.model.Entity.IndustryEntity;
import com.example.nasdaq.service.IndustryService;

@Service
public class IndustryServiceImpl implements IndustryService{
    
    @Autowired
    private IndustryDao industryDao;

    @Autowired
    private DailyUpdatesDao dailyUpdatesDao;

    @Override
    public IndustryDto getIndustryAvg(String industry) {
        // TODO Auto-generated method stub

        String recentDate = dailyUpdatesDao.getMostRecentDate();
        IndustryEntity entity = industryDao.getIndustryAvg(industry, recentDate);
        IndustryDto dto = new IndustryDto();
        
        dto.setIndustry(entity.getIndustryPK().getIndustry());
        dto.setDailydate(entity.getIndustryPK().getDailydate());
        dto.setAvgMarketCap(entity.getAvgMarketCap());
        dto.setAvgPER(entity.getAvgPER());
        dto.setAvgPBR(entity.getAvgPBR());
        dto.setAvgPSR(entity.getAvgPSR());
        dto.setAvgEV_EBITDA(entity.getAvgEV_EBITDA());

        return dto;
    }

    @Override
    public List<IndustryDto> getAllIndustry() {
        // TODO Auto-generated method stub
        String recentDate = dailyUpdatesDao.getMostRecentDate();
        List<IndustryEntity> entities = industryDao.getAllIndustry(recentDate);
        List<IndustryDto> dtos = new ArrayList<>();

        for(IndustryEntity entity : entities){
            IndustryDto dto = new IndustryDto();

            dto.setIndustry(entity.getIndustryPK().getIndustry());
            dto.setDailydate(entity.getIndustryPK().getDailydate());
            dto.setAvgMarketCap(entity.getAvgMarketCap());
            dto.setAvgPER(entity.getAvgPER());
            dto.setAvgPSR(entity.getAvgPSR());
            dto.setAvgPBR(entity.getAvgPBR());
            dto.setAvgEV_EBITDA(entity.getAvgEV_EBITDA());
            dto.setAvgFluc(entity.getAvgFluc());

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<IndustryDto> getWeeklyInfo(String industry) {
        // TODO Auto-generated method stub
        List<IndustryEntity> entities = industryDao.getWeeklyInfo(industry);
        List<IndustryDto> dtos = new ArrayList<>();

        for(IndustryEntity entity : entities) {
            IndustryDto dto = new IndustryDto();
            dto.setIndustry(entity.getIndustryPK().getIndustry());
            dto.setDailydate(entity.getIndustryPK().getDailydate());
            dto.setAvgPER(entity.getAvgPER());
            dto.setAvgPBR(entity.getAvgPBR());

            dtos.add(dto);
        }
        return dtos;
    }

    
    
}
