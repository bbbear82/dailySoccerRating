package com.chen.interview;

import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

import java.lang.NumberFormatException;

/**
 * This class will output teams' overall rating for each match day
 * Test is done by compare actual output to expected output for the sample input
 */
public class SoccerRating
{	
	public static Set<String> dailyTeamNameSet;
	public static List<SoccerTeam> dailyTeamList;

	public static SoccerTeam parseTeam(String[] strArr) {
		String teamName = "";
    	for(int i = 0; i <= strArr.length - 2; i++)
    		teamName += strArr[i] + " ";
    	return new SoccerTeam(teamName.trim());
	}

	public static int parseMatchScore(String[] strArr) {
		return Integer.valueOf(strArr[strArr.length - 1]);
	}

    public static void main(String[] args) throws FileNotFoundException
    {
        if (args.length < 1) {
            System.err.println("Please provide an input file that contains a match result");
            System.exit(0);
        }
        //keep the distinct team names
        dailyTeamNameSet = new HashSet<String>(); 
        dailyTeamList = new ArrayList<SoccerTeam>();

        SoccerLeague league = new SoccerLeague();
        String inputFile = args[0];
        String line = "";
        int matchDay = 1;

        Scanner sc = new Scanner(new File(inputFile)).useDelimiter("\n");
    	while(sc.hasNext()) {
    		line = sc.next();
    		//System.out.println(line);
    		if(line == null || line.length() == 0) continue;
    		String[] matchResults = line.split(",");
    		if(matchResults.length != 2) continue;
    		String firstStr = matchResults[0].trim();
    		String secondStr = matchResults[1].trim();
      		if(firstStr.isEmpty() || secondStr.isEmpty()) {
    			System.err.println("team name and score are empty");
            	System.exit(0);
    		}
    		String[] firstArray = firstStr.split(" ");
    		String[] secondArray = secondStr.split(" ");
    		if(firstArray.length < 2 || secondArray.length < 2) {
    			System.err.println("Need team name and a match score");
            	System.exit(0);
    		}
    		
    		SoccerTeam firstTeam = parseTeam(firstArray);
    		SoccerTeam secondTeam = parseTeam(secondArray);
    		int firstScore = -1;
    		int secondScore = -1;
    		try {
    			firstScore = parseMatchScore(firstArray);
    			secondScore = parseMatchScore(secondArray);
    		} catch(NumberFormatException e) {
    			  System.err.println("Not able to formatting the score");
            	  System.exit(0);
    		}

    		if(firstScore > secondScore) { //first team win
    			firstTeam.updateScore(GameResultEnum.WIN);
    			secondTeam.updateScore(GameResultEnum.LOSS);
    		} else if(firstScore < secondScore) {//second team win
    			firstTeam.updateScore(GameResultEnum.LOSS);
    			secondTeam.updateScore(GameResultEnum.WIN);
    		} else { //a Tie
      			firstTeam.updateScore(GameResultEnum.TIE);
    			secondTeam.updateScore(GameResultEnum.TIE);
    		}

    		//end of current match day
    		if(dailyTeamNameSet.contains(firstTeam.getTeamName())) {
  
    		    for(SoccerTeam st : dailyTeamList) {
    		    	league.addOrUpdateTeam(st);
    		    }
    		    if(! league.isFull())
    		    	league.setFull(); 

      			if(league.isFull()) {
    				if(dailyTeamList.size() != league.getLeagueSize() 
    					|| dailyTeamNameSet.size() != league.getLeagueSize())
    				System.err.println("Each team should play exactly once each match day.");
    			}
    		    dailyTeamNameSet.clear();
    			dailyTeamList.clear();

    			//print out the top K, here the K is 3. 
    			System.out.println("Matchday " + matchDay++);
    			List<SoccerTeam> todayResult = league.getTopKTeams(3); 
    			for(SoccerTeam st : todayResult)
    				System.out.println(st.toString());
    			System.out.println();

    		    dailyTeamList.add(firstTeam);
    		    dailyTeamList.add(secondTeam);
    		    dailyTeamNameSet.add(firstTeam.getTeamName());
    		    dailyTeamNameSet.add(secondTeam.getTeamName());
    		} else { //continue current match day
    		   dailyTeamList.add(firstTeam);
    		   dailyTeamList.add(secondTeam);
    		   dailyTeamNameSet.add(firstTeam.getTeamName());
    		   dailyTeamNameSet.add(secondTeam.getTeamName());
    		}
    	}

 		for(SoccerTeam st : dailyTeamList) {
    		league.addOrUpdateTeam(st);
    	}	

      	if(league.isFull()) {
    		if(dailyTeamList.size() != league.getLeagueSize() 
    					|| dailyTeamNameSet.size() != league.getLeagueSize())
    			System.err.println("Each team should play exactly once each match day.");
    		}
    	dailyTeamNameSet.clear();
    	dailyTeamList.clear();

    	System.out.println("Matchday " + matchDay++);
      	List<SoccerTeam> todayResult = league.getTopKTeams(3); 
    	for(SoccerTeam st : todayResult)
    		System.out.println(st.toString());

    	sc.close();
    }
}
