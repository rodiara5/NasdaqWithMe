package com.example.nasdaq.model.DAO;

import java.util.List;

import com.example.nasdaq.model.Entity.WatchlistEntity;

public interface WatchlistDao {

    public List<WatchlistEntity> getWatchlist(String userId);

    public WatchlistEntity getOneWatchlist(String userId, String ticker);

    public void addToWatchlist(String userId, String ticker, String name);

    public void deleteFromWatchlist(String userId, String ticker);
}
