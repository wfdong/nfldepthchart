package com.sportsbet.nfldepthchart.utils;

import java.util.Arrays;

public class NFLUtils {
    public static boolean isValidPosition(String position) {
        String[] validPositions = {"LWR","RWR","LT","LG","C","RG","RT","TE","QB","RB"};
        return Arrays.asList(validPositions).contains(position);
    }
}
