package com.sportsbet.nfldepthchart.models;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Team {
    private String teamName;

    /*
     * A mapping between position and the list of players in this position
     * key--position
     * value--List of the player numbers
     */
    private Map<String, List<Integer>> playersOfPosition;

    /*
     * A mapping of all players in this team
     * key--number
     * value--player
     */
    private Map<Integer, Player> allPlayers;

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Map<String, List<Integer>> getPlayersOfPosition() {
        return playersOfPosition;
    }
    public void setPlayersOfPosition(Map<String, List<Integer>> playersOfPosition) {
        this.playersOfPosition = playersOfPosition;
    }

    public Map<Integer, Player> getAllPlayers() {
        return allPlayers;
    }
    public void setAllPlayers(Map<Integer, Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
}
