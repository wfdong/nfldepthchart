package com.sportsbet.nfldepthchart.models;

import java.util.List;
import java.util.Map;

public class Team {
    private String teamName;
    private  Map<String, List<Player>> players;

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public Map<String, List<Player>> getPlayers() {
        return players;
    }
    public void setPlayers(Map<String, List<Player>> players) {
        this.players = players;
    }
}
