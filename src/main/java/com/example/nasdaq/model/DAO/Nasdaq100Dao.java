package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.Nasdaq100Entity;

public interface Nasdaq100Dao {
    
    // select
    public Nasdaq100Entity getByTicker(String ticker);
    public List<Nasdaq100Entity> getAllNasdaq100();

    // insert, update, delete -> 필요없음, pymysql로 관리

}
