package com.sportsbet.nfldepthchart.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportsbet.nfldepthchart.dao.DataBaseMock;
import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.models.Team;
import com.sportsbet.nfldepthchart.services.DepthChartService;
import com.sportsbet.nfldepthchart.utils.NFLUtils;

/*
 * The implementation for the main logics
 */
@Service
public class DepthChartServiceImpl implements DepthChartService {

    @Autowired
    private DataBaseMock dataBaseMock;

    /*
     * Add a new player to the depthChart
     */
    @Override
    public boolean addPlayerToDepthChart(String position, Player player, int positionDepth) {
        if(!NFLUtils.isValidPosition(position) 
            || positionDepth < 0 
            || player == null
            || player.getName() == null
            || player.getNumber() < 0
            || (player.getPosition()!=null&&!player.getPosition().equals(position))){
            // if the request parameter is invalid then return false
            return false;
        }
        Team team = dataBaseMock.getTeam();
        int newPlayerNumber = player.getNumber();
        Map<String, List<Integer>> players = team.getPlayersOfPosition();
        // if no list existing, need to create a new one
        players.computeIfAbsent(position, key->new ArrayList<>());
        /*  Add this player to the positionDepth
         *  We assume the first player added into this position will be the first priority of this position
         *  if not(if we want to use the absolute depth number as the priority) this function need to be updated
        */ 
        List<Integer> playerList = players.get(position);
        if(positionDepth<playerList.size()){
            playerList.add(positionDepth, newPlayerNumber);
        }else{
            playerList.add(newPlayerNumber);
        }

        //update position
        addNewPositionForPlayer(team, player.getNumber(), position);
        return true;
    }

    /*
     * Add a new player to the depthChart, without depth parameter
     */
    @Override
    public boolean addPlayerToDepthChart(String position, Player player) {
        if(!NFLUtils.isValidPosition(position) 
            || player == null
            || player.getName() == null
            || player.getNumber() < 0
            || (player.getPosition()!=null&&!player.getPosition().equals(position))){
            // if the request parameter is invalid then return false
            return false;
        }
        Team team = dataBaseMock.getTeam();
        int newPlayerNumber = player.getNumber();
        Map<String, List<Integer>> players = team.getPlayersOfPosition();
        players.computeIfAbsent(position, key->new ArrayList<>());
        players.get(position).add(newPlayerNumber);
        addNewPositionForPlayer(team, player.getNumber(), position);
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
        Map<String, List<Player>> result = new HashMap<>();
        Team team = dataBaseMock.getTeam();
        Map<String, List<Integer>> players = team.getPlayersOfPosition();
        Map<Integer, Player> allPlayers = team.getAllPlayers();
        players.forEach((key, value) ->{
            List<Player> playerList = new ArrayList<>();
            value.forEach(item ->playerList.add(allPlayers.get(item)));
            result.put(key, playerList);
        });
        return result;
    }

    /*
     * Once a new position added, need to update Player meta info accordinglly
     */
    private void addNewPositionForPlayer(Team team, int number, String newPosition){
        Map<Integer, Player> allPlayers = team.getAllPlayers();
        Player player = allPlayers.get(number);
        if(player!=null){
            List<String> positions = player.getPosition();
            if(!positions.contains(newPosition)){
                positions.add(newPosition);
            }
        }
    }
}
