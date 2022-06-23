package com.sportsbet.nfldepthchart.models;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class RequestData implements Serializable{
    private static final long serialVersionUID = 5926468583005150707L;

    private Player player;
    private String position;
    private Integer positionDepth;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Integer getPositionDepth() {
        return positionDepth;
    }
    public void setPositionDepth(Integer positionDepth) {
        this.positionDepth = positionDepth;
    }
}
