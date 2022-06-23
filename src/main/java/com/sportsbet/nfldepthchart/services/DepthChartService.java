package com.sportsbet.nfldepthchart.services;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.sportsbet.nfldepthchart.models.Player;

/*
 * The main logic services
 */
@Service
public interface DepthChartService {
    public boolean addPlayerToDepthChart (String position, Player player, int positionDepth);
    public boolean addPlayerToDepthChart (String position, Player player);
    public Player removePlayerFromDepthChart(String position, Player player);
    public List<String> getBackups (String position, Player player);
    public Map<String, List<Player>> getFullDepthChart();
}
