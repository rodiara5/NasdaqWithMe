package com.example.nasdaq.service;

import java.util.List;

import com.example.nasdaq.model.DTO.WatchlistDto;

public interface WatchlistService {
    public List<WatchlistDto> getWatchlist(String userId);

    public Integer toggleWatchlist(String userId, String ticker, String name);

}
