package com.example.nasdaq.service.Impl;

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
        dto.setAvgPER(entity.getAvgPER());
        dto.setAvgPEG(entity.getAvgPEG());
        dto.setAvgPBR(entity.getAvgPBR());
        dto.setAvgPSR(entity.getAvgPSR());
        dto.setAvgEV_EBITDA(entity.getAvgEV_EBITDA());

        return dto;
    }

    
}
