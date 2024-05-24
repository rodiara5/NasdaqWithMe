package com.example.nasdaq.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nasdaq.model.Entity.EdgarReportsEntity;
import com.example.nasdaq.model.Entity.edgarReportsPK;

public interface EdgarReportsRepository extends JpaRepository<EdgarReportsEntity, edgarReportsPK>{
    public EdgarReportsEntity findByEdgarReportsPKTicker(String ticker);
}
