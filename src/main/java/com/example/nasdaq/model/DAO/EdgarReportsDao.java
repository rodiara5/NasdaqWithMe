package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.EdgarReportsEntity;

public interface EdgarReportsDao {
    
    // select
    public EdgarReportsEntity getOneEdgarInfo(String ticker);

    public List<EdgarReportsEntity> getAllEdgarInfo();

}
