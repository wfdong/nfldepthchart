package com.sportsbet.nfldepthchart.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.services.DepthChartService;

/*
 * The implementation for the main logics
 */
@Service
public class DepthChartServiceImpl implements DepthChartService {


    @Override
    public boolean addPlayerToDepthChart(String position, Player player, Integer positionDepth) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Player removePlayerFromDepthChart(String position, Player player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getBackups(String position, Player player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, List<Player>> getFullDepthChart() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
