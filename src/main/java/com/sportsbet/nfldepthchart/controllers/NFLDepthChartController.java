package com.sportsbet.nfldepthchart.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.models.RequestData;
import com.sportsbet.nfldepthchart.models.ResponseData;
import com.sportsbet.nfldepthchart.services.DepthChartService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * A controller used to provide rest services
 */
@RestController
public class NFLDepthChartController {

    @Autowired
    DepthChartService depthChartService;

    @Autowired
    ResponseData responseData;

    @PostMapping(value="/addplayer")
    public ResponseData addPlayer(@RequestBody RequestData requestData) {
        Player player = requestData.getPlayer();
        String position = requestData.getPosition();
        int depth = requestData.getPositionDepth();
        responseData.clear();
        responseData.addAttribute("StatusCode", "-1");
        responseData.addAttribute("msg", "Add player fail!");
        if(depth>=0){
            if (depthChartService.addPlayerToDepthChart(position, player, depth)){
                responseData.addAttribute("StatusCode", "0");
                responseData.addAttribute("msg", "Add player success!");
                return responseData;
            }
        }else {
            if (depthChartService.addPlayerToDepthChart(position, player)){
                responseData.addAttribute("StatusCode", "0");
                responseData.addAttribute("msg", "Add player success!");
                return responseData;
            }
        }
        return responseData;
    }
    
    @PostMapping(value="/removeplayer")
    public ResponseData removePlayer(@RequestBody RequestData requestData) {
        Player player = requestData.getPlayer();
        String position = requestData.getPosition();
        responseData.clear();
        responseData.addAttribute("StatusCode", "-2");
        responseData.addAttribute("msg", "Remove player fail!");
        if(player!=null && position != null){
            Player removedPlayer = depthChartService.removePlayerFromDepthChart(position, player);
            if (removedPlayer==null){
                return responseData;
            }else  {
                responseData.addAttribute("StatusCode", "0");
                responseData.addAttribute("msg", "Remove player success!");
                responseData.addAttribute("Player", removedPlayer);
                return responseData;
            }
        }
        return responseData;
    }

    @PostMapping(value="/getbackups")
    public ResponseData getBackups(@RequestBody RequestData requestData) {
        Player player = requestData.getPlayer();
        String position = requestData.getPosition();
        responseData.clear();
        responseData.addAttribute("StatusCode", "-3");
        responseData.addAttribute("msg", "Query backup player fail!");
        if(player!=null && position != null){
            List<String> result = depthChartService.getBackups(position, player);
            responseData.addAttribute("StatusCode", "0");
            responseData.addAttribute("msg", "Query backup player success!");
            responseData.addAttribute("Backups", result);
            return responseData;
        }
        return responseData;
    }

    @GetMapping(value="/getFullDepthChart")
    public ResponseData getFullDepthChart() {
        responseData.clear();
        Map<String, List<Player>> result = depthChartService.getFullDepthChart();
        responseData.addAttribute("StatusCode", "0");
        responseData.addAttribute("msg", "Quer full depth chart success!");
        responseData.addAttribute("FullChart", result);
        return responseData;
    }
    
}
