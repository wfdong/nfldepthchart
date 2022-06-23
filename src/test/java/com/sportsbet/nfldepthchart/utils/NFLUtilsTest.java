package com.sportsbet.nfldepthchart.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NFLUtilsTest {
    @Test
    void isValidPositionTest(){
        assertEquals(true, NFLUtils.isValidPosition("LWR"));
        assertEquals(true, NFLUtils.isValidPosition("RWR"));
        assertEquals(true, NFLUtils.isValidPosition("LT"));
        assertEquals(true, NFLUtils.isValidPosition("LG"));
        assertEquals(true, NFLUtils.isValidPosition("C"));
        assertEquals(true, NFLUtils.isValidPosition("RG"));
        \assertEquals(true, NFLUtils.isValidPosition("RT"));
        assertEquals(true, NFLUtils.isValidPosition("TE"));
        assertEquals(true, NFLUtils.isValidPosition("QB"));
        assertEquals(true, NFLUtils.isValidPosition("RB"));
    }
}
