package com.chen.interview;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App., test is reading the first day input file, compare the
 top 3 to the expected output, then reading the second day, then to the third day, 
 then go to the Fourth day. 
 */
public class SoccerTeamTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoccerTeamTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( SoccerTeamTest.class );
    }

    /**
     * Unit test for SoccerTeam class
     * Leave the test of compartor to the SoccerLeague unit test
     */
    public void testSoccerTeam() {
        String teamName1 = "San Jose EarthQuake";
        SoccerTeam team1 = new SoccerTeam(teamName1);
        assertEquals(teamName1, team1.getTeamName());
        assertEquals(0, team1.getTotalScore());
        team1.updateScore(GameResultEnum.WIN);
        assertEquals(3, team1.getTotalScore());
        team1.updateScore(GameResultEnum.TIE);
        assertEquals(4, team1.getTotalScore());
        team1.updateScore(GameResultEnum.LOSS);
        assertEquals(4, team1.getTotalScore());
    }
}
