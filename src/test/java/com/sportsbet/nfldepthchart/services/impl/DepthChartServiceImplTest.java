package com.sportsbet.nfldepthchart.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sportsbet.nfldepthchart.models.Player;
import com.sportsbet.nfldepthchart.services.DepthChartService;

public class DepthChartServiceImplTest {
    DepthChartService depthChartService;
    static Player TomBrady, BlaineGabbert, KyleTrask, MikeEvans, JaelonDarden, ScottMiller;

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
        depthChartService = new DepthChartServiceImpl();
        addPlayersToDepthChars();
    }

    @AfterEach
    void clear() {
        clearPlayersFromDepthChars();
    }

    @Test
    void addPlayerToDepthChartTest(){
        Player MarpetAli = new Player();
        MarpetAli.setName("Marpet Ali");
        MarpetAli.setNumber(74);
        assertEquals(true, depthChartService.addPlayerToDepthChart("LG", MarpetAli, 0));
        
        Player LeverettNick = new Player();
        LeverettNick.setName("Leverett Nick");
        LeverettNick.setNumber(60);
        assertEquals(true, depthChartService.addPlayerToDepthChart("LG", LeverettNick, null));
        assertNotNull(depthChartService.getFullDepthChart());

        Player EvansMike = new Player();
        EvansMike.setName("Evans Mike");
        EvansMike.setNumber(13);
        List<String> positions = new ArrayList<String>();
        positions.add("LWR");
        EvansMike.setPosition(positions);
        assertEquals(false, depthChartService.addPlayerToDepthChart("LG", EvansMike, null));
        assertNotNull(depthChartService.getFullDepthChart());
    }

    @Test
    void getBackupsTest(){
        List<String> backupsTomBrady = depthChartService.getBackups("QB", TomBrady);
        assertNotNull(backupsTomBrady);
        assertEquals(2, backupsTomBrady.size());
        assertEquals("11-Blaine Gabbert", backupsTomBrady.get(0));
        assertEquals("2-Kyle Trask", backupsTomBrady.get(1));

        List<String> backupsJaelonDarden = depthChartService.getBackups("QB", JaelonDarden);
        assertNotNull(backupsJaelonDarden);
        assertEquals(1, backupsJaelonDarden.size());
        assertEquals("10-ScottMiller", backupsJaelonDarden.get(0));

        List<String> backupsMikeEvans = depthChartService.getBackups("QB", MikeEvans);
        assertNull(backupsMikeEvans);

        List<String> backupsBlaineGabbert = depthChartService.getBackups("QB", BlaineGabbert);
        assertNotNull(backupsBlaineGabbert);
        assertEquals(1, backupsBlaineGabbert.size());
        assertEquals("2-Kyle Trask", backupsBlaineGabbert.get(0));

        List<String> backupsKyleTrask = depthChartService.getBackups("QB", KyleTrask);
        assertNull(backupsKyleTrask);
    }

    @Test
    void getFullDepthChartTest(){
        Map<String, List<Player>> result = depthChartService.getFullDepthChart();
        assertNotNull(result);
        assertNotNull(result.get("QB"));
        assertEquals(3, result.get("QB").size());
        assertEquals("Tom Brady", result.get("QB").get(0).getName());
        assertEquals("Blaine Gabbert", result.get("QB").get(1).getName());
        assertEquals("Kyle Trask", result.get("QB").get(2).getName());

        assertEquals(3, result.get("LWR").size());
        assertEquals("Mike Evans", result.get("LWR").get(0).getName());
        assertEquals("Jaelon Darden", result.get("LWR").get(1).getName());
        assertEquals("Scott Miller", result.get("LWR").get(2).getName());
    }

    @Test
    void removePlayerFromDepthChartTest(){
        Player resultMikeEvans = depthChartService.removePlayerFromDepthChart("LWR", MikeEvans);
        assertNotNull(resultMikeEvans);
        assertEquals(13, resultMikeEvans.getNumber());
        assertEquals("Mike Evans", resultMikeEvans.getName());

        Player Roger = new Player();
        Roger.setName("Roger");
        Roger.setNumber(1000);
        Player resultRoger = depthChartService.removePlayerFromDepthChart("LWR", Roger);
        assertNull(resultRoger);

        Player resultTomBrady = depthChartService.removePlayerFromDepthChart("LWR", TomBrady);
        assertNull(resultTomBrady);
    }

    private void addPlayersToDepthChars() {
        TomBrady = new Player();
        TomBrady.setName("Tom Brady");
        TomBrady.setNumber(12);
        BlaineGabbert = new Player();
        BlaineGabbert.setName("Blaine Gabbert");
        BlaineGabbert.setNumber(11);
        KyleTrask = new Player();
        KyleTrask.setName("Kyle Trask");
        KyleTrask.setNumber(2);
        MikeEvans = new Player();
        MikeEvans.setName("Mike Evans");
        MikeEvans.setNumber(13);
        JaelonDarden = new Player();
        JaelonDarden.setName("Jaelon Darden");
        JaelonDarden.setNumber(1);
        ScottMiller = new Player();
        ScottMiller.setNumber(10);
        ScottMiller.setName("Scott Miller");
        depthChartService.addPlayerToDepthChart("QB", TomBrady, 0);
        depthChartService.addPlayerToDepthChart("QB", BlaineGabbert, 1);
        depthChartService.addPlayerToDepthChart("QB", KyleTrask, 2);

        depthChartService.addPlayerToDepthChart("LWR", MikeEvans, 0);
        depthChartService.addPlayerToDepthChart("LWR", JaelonDarden, 1);
        depthChartService.addPlayerToDepthChart("LWR", ScottMiller, 2);
    }

    private void clearPlayersFromDepthChars() {
        depthChartService.removePlayerFromDepthChart("QB", TomBrady);
        depthChartService.removePlayerFromDepthChart("QB", BlaineGabbert);
        depthChartService.removePlayerFromDepthChart("QB", KyleTrask);

        depthChartService.removePlayerFromDepthChart("LWR", MikeEvans);
        depthChartService.removePlayerFromDepthChart("LWR", JaelonDarden);
        depthChartService.removePlayerFromDepthChart("LWR", ScottMiller);
    }

}
