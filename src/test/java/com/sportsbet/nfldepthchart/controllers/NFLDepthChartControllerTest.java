package com.sportsbet.nfldepthchart.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbet.nfldepthchart.dao.DataBaseMock;
import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.models.RequestData;
import com.sportsbet.nfldepthchart.services.DepthChartService;
import com.sportsbet.nfldepthchart.services.impl.DepthChartServiceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.List;

/*
 * Integration test cases
 */
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class NFLDepthChartControllerTest {
    @Autowired
	private MockMvc mvc;

    @Mock
    DepthChartService depthChartService;

    @Autowired
    private RequestData requestData;

    @Mock
    DataBaseMock dataBaseMock;

    @BeforeEach
    void init() {
        depthChartService = new DepthChartServiceImpl();
        dataBaseMock = new DataBaseMock();
        ReflectionTestUtils.setField(depthChartService, "dataBaseMock", dataBaseMock);
    }

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
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").value("0"));
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
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").value("0"));
    }

    @Test
    void testGetbackups() throws Exception{
        Player TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        requestData.setPlayer(TomBrady);
        requestData.setPosition("QB");
        mvc.perform(MockMvcRequestBuilders
            .post("/addplayer")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        Player BlaineGabbert = new Player();
        BlaineGabbert.setName("Blaine Gabbert");
        BlaineGabbert.setNumber(11);
        requestData.setPlayer(BlaineGabbert);
        requestData.setPosition("QB");
        mvc.perform(MockMvcRequestBuilders
            .post("/addplayer")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        requestData.setPlayer(TomBrady);
        requestData.setPosition("QB");
        List<String> backups = new ArrayList<String>();
        backups.add("11-Blaine Gabbert");
        //Mockito.when(depthChartService.getBackups("QB", TomBrady)).thenReturn(backups);
        mvc.perform(MockMvcRequestBuilders
            .post("/getbackups")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").value("0"));
    }

    @Test
    void testGetFullDepthChart() throws Exception{
        Player TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        requestData.setPlayer(TomBrady);
        requestData.setPosition("QB");
        mvc.perform(MockMvcRequestBuilders
            .post("/addplayer")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        Player BlaineGabbert = new Player();
        BlaineGabbert.setName("Blaine Gabbert");
        BlaineGabbert.setNumber(11);
        requestData.setPlayer(BlaineGabbert);
        requestData.setPosition("QB");
        mvc.perform(MockMvcRequestBuilders
            .post("/addplayer")
            .content(asJsonString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
        //Mockito.when(depthChartService.getFullDepthChart()).thenReturn(res);
        mvc.perform(MockMvcRequestBuilders
            .get("/getFullDepthChart")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").isNotEmpty())
            .andExpect(jsonPath("$.response.StatusCode").value("0"));
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
