package com.sportsbet.nfldepthchart.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.sportsbet.nfldepthchart.models.RequestData;
import com.sportsbet.nfldepthchart.models.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * A controller used to provide rest services
 */
@RestController
public class NFLDepthChartController {
    @Autowired
    ResponseData responseData;

    @PostMapping(value="/addplayer")
    public ResponseData addPlayer(@RequestBody RequestData requestData) {
        //TODO: process POST request
        
        return null;
    }
    
    @PostMapping(value="/removeplayer")
    public ResponseData removePlayer(@RequestBody RequestData requestData) {
        //TODO: process POST request
        
        return null;
    }

    @PostMapping(value="/getbackups")
    public ResponseData getBackups(@RequestBody RequestData requestData) {
        //TODO: process POST request
        
        return null;
    }

    @GetMapping(value="/getFullDepthChart")
    public ResponseData getFullDepthChart() {
        //TODO: process GET request
        return null;
    }
    
}
