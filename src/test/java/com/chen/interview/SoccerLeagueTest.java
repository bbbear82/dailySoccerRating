package com.chen.interview;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * unit test for SoccerLeague class, with a focus on the sorting
 for keys and values.
 */
public class SoccerLeagueTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoccerLeagueTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(SoccerLeagueTest.class);
    }

    /**
     * Test soccerLeague class
     */
    public void testSoccerLeague1() {
        String teamName1 = "San Jose EarthQuake"; //WIN
        SoccerTeam team1 = new SoccerTeam(teamName1);
        team1.updateScore(GameResultEnum.WIN);

        String teamName2 = "76er"; //LOSS
        SoccerTeam team2 = new SoccerTeam(teamName2);
        team2.updateScore(GameResultEnum.LOSS);

        String teamName3 = "Lakes"; //WIN
        SoccerTeam team3 = new SoccerTeam(teamName3);
        team3.updateScore(GameResultEnum.WIN);

        String teamName4 = "I am a new team";
        SoccerTeam team4 = new SoccerTeam(teamName4);
        team4.updateScore(GameResultEnum.TIE);

        SoccerLeague league = new SoccerLeague();
        int ret1 = league.addOrUpdateTeam(team1);
        int ret2 = league.addOrUpdateTeam(team2);
        int ret3 = league.addOrUpdateTeam(team3);

        league.setFull();
        assertEquals(3, league.getLeagueSize());
        assertEquals(0, ret1);
        assertEquals(0, ret2);
        assertEquals(0, ret3);

        int ret4 = league.addOrUpdateTeam(team4);
        assertEquals(3, league.getLeagueSize());
        assertEquals(-1, ret4);

        Set<String> nameSet = league.getAllTeamNames();
        List<String> nList = new ArrayList<String>();
        for(String n : nameSet)
            nList.add(n);
        assertEquals(teamName2, nList.get(0));
        assertEquals(teamName3, nList.get(1));
        assertEquals(teamName1, nList.get(2));
    }

    /**
     * Test soccerLeague class
     */
    public void testSoccerLeague2() {
        String teamName1 = "San Jose EarthQuake"; //WIN
        SoccerTeam team1 = new SoccerTeam(teamName1);
        team1.updateScore(GameResultEnum.WIN);

        String teamName2 = "76er"; //LOSS
        SoccerTeam team2 = new SoccerTeam(teamName2);
        team2.updateScore(GameResultEnum.LOSS);

        String teamName3 = "Lakes"; //WIN
        SoccerTeam team3 = new SoccerTeam(teamName3);
        team3.updateScore(GameResultEnum.WIN);

        String teamName4 = "I am a new team";
        SoccerTeam team4 = new SoccerTeam(teamName4);
        team4.updateScore(GameResultEnum.TIE);

        SoccerLeague league = new SoccerLeague();
        int ret1 = league.addOrUpdateTeam(team1);
        int ret2 = league.addOrUpdateTeam(team2);
        int ret3 = league.addOrUpdateTeam(team3);

        league.setFull();
        assertEquals(3, league.getLeagueSize());
        assertEquals(0, ret1);
        assertEquals(0, ret2);
        assertEquals(0, ret3);

        int ret4 = league.addOrUpdateTeam(team4);
        assertEquals(3, league.getLeagueSize());
        assertEquals(-1, ret4);

        Set<String> nameSet = league.getAllTeamNames();
        List<String> nList = new ArrayList<String>();
        for(String n : nameSet)
            nList.add(n);
        assertEquals(teamName2, nList.get(0));
        assertEquals(teamName3, nList.get(1));
        assertEquals(teamName1, nList.get(2));

        assertEquals(0, league.getTopKTeams(0).size());
        assertEquals(1, league.getTopKTeams(1).size());
        assertEquals(2, league.getTopKTeams(2).size());
        assertEquals(3, league.getTopKTeams(3).size());
        assertEquals(3, league.getTopKTeams(4).size());

        List<SoccerTeam> sortedTeamList = league.getTopKTeams(3);
        assertEquals(teamName3, sortedTeamList.get(0).getTeamName());
        assertEquals(teamName1, sortedTeamList.get(1).getTeamName());
        assertEquals(teamName2, sortedTeamList.get(2).getTeamName());
    }


}
