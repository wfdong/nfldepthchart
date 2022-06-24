package com.sportsbet.nfldepthchart.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Player implements Serializable{
    public Player(){}

    private int number;
    private String name;
    private List<String> position;

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getPosition() {
        return position;
    }
    public void setPosition(List<String> position) {
        this.position = position;
    }
}
