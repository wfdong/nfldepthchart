package com.sportsbet.nfldepthchart.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportsbet.nfldepthchart.dao.DataBaseMock;
import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.models.Team;
import com.sportsbet.nfldepthchart.services.DepthChartService;

/*
 * The implementation for the main logics
 */
@Service
public class DepthChartServiceImpl implements DepthChartService {

    @Autowired
    private DataBaseMock dataBaseMock;

    @Override
    public boolean addPlayerToDepthChart(String position, Player player, Integer positionDepth) {
        Team team = dataBaseMock.getTeam();
        Map<String, List<Player>> allPlayers = team.getPlayers();
        if(allPlayers.get(position) == null){
            // if no list existing, need to create a new one
            List<Player> playerList = new ArrayList<>();
            allPlayers.put(position, playerList);
        }
        if(positionDepth == null){
            // add to the end position
            allPlayers.get(position).add(player);
        }
        return true;
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
