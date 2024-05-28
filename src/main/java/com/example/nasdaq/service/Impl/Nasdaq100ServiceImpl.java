package com.example.nasdaq.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.Nasdaq100Dao;
import com.example.nasdaq.model.DTO.Nasdaq100Dto;
import com.example.nasdaq.model.Entity.Nasdaq100Entity;
import com.example.nasdaq.service.Nasdaq100Service;

@Service
public class Nasdaq100ServiceImpl implements Nasdaq100Service{
    
    @Autowired
    private Nasdaq100Dao nasdaq100Dao;

    @Override
    public Nasdaq100Dto findByTicker(String ticker) {
        // TODO Auto-generated method stub
        Nasdaq100Entity entity = nasdaq100Dao.getByTicker(ticker);
        Nasdaq100Dto dto = new Nasdaq100Dto();
        dto.setTicker(entity.getTicker());
        dto.setName(entity.getName());
        dto.setEstablished(entity.getEstablished());
        dto.setCEO(entity.getCEO());
        dto.setProducts(entity.getProducts());
        dto.setMilestones(entity.getMilestones());
        dto.setIndustry(entity.getIndustry());
        return dto;
    }

    @Override
    public List<Nasdaq100Dto> getAllNasdaq100() {
        // TODO Auto-generated method stub
        List<Nasdaq100Entity> entities = nasdaq100Dao.getAllNasdaq100();
        List<Nasdaq100Dto> dtos = new ArrayList<>();
        for (Nasdaq100Entity entity : entities) {
            Nasdaq100Dto dto = new Nasdaq100Dto();
            dto.setTicker(entity.getTicker());
            dto.setName(entity.getName());
            dto.setEstablished(entity.getEstablished());
            dto.setCEO(entity.getCEO());
            dto.setProducts(entity.getProducts());
            dto.setMilestones(entity.getMilestones());
            dto.setIndustry(entity.getIndustry());

            dtos.add(dto);
        }
        return dtos;
    }

    
}
