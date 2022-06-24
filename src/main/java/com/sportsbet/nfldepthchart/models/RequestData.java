package com.sportsbet.nfldepthchart.models;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData implements Serializable{
    private static final long serialVersionUID = 5926468583005150707L;

    private Player player;
    private String position;
    private Integer positionDepth = -1;

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
