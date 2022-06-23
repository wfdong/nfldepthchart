package com.sportsbet.nfldepthchart.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.models.RequestData;
import com.sportsbet.nfldepthchart.services.DepthChartService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Integration test cases
 */
@AutoConfigureMockMvc
@SpringBootTest
public class NFLDepthChartControllerTest {
    @Autowired
	private MockMvc mvc;

    @MockBean
    DepthChartService depthChartService;

    @Autowired
    private RequestData requestData;

    @Test
    void testAddPlayer() throws Exception{
        Player TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        requestData.setPlayer(TomBrady);
        requestData.setPosition("QB");
        requestData.setPositionDepth(0);
        mvc.perform(MockMvcRequestBuilders
            .post("/addplayer")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty());
    }

    @Test
    void testRemovePlayer() throws Exception{
        Player TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        requestData.setPlayer(TomBrady);
        requestData.setPosition("QB");
        mvc.perform(MockMvcRequestBuilders
            .post("/removeplayer")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty());
    }

    @Test
    void testGetbackups() throws Exception{
        Player TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        requestData.setPlayer(TomBrady);
        requestData.setPosition("QB");

        Player BlaineGabbert = new Player();
        BlaineGabbert.setName("Blaine Gabbert");
        BlaineGabbert.setNumber(11);
        List<String> backups = new ArrayList<String>();
        backups.add("11-Blaine Gabbert");
        Mockito.when(depthChartService.getBackups("QB", TomBrady)).thenReturn(backups);
        mvc.perform(MockMvcRequestBuilders
            .post("/getbackups")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty());
    }

    @Test
    void testGetFullDepthChart() throws Exception{
        Player TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        Player BlaineGabbert = new Player();
        BlaineGabbert.setName("Blaine Gabbert");
        BlaineGabbert.setNumber(11);
        List<Player> players = new ArrayList<Player>();
        players.add(TomBrady);
        players.add(BlaineGabbert);
        Map<String, List<Player>> res = new HashMap<>();
        res.put("QB", players);
        Mockito.when(depthChartService.getFullDepthChart()).thenReturn(res);
        mvc.perform(MockMvcRequestBuilders
            .post("/getFullDepthChart")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty());
    }

    public String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 
}
