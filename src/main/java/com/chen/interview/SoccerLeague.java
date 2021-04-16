package com.chen.interview;

import java.util.*;

/**
TreeMap sorts String type key to alphabetical order already.
*/
public class SoccerLeague {
	private Map<String, SoccerTeam> leagueTeams;
	private boolean isFull;

	public SoccerLeague() {
		this.leagueTeams = new TreeMap<String, SoccerTeam>();
		isFull = false;
	}

	public void setFull() {
		this.isFull = true;
	}

	public boolean isFull() {
		return this.isFull;
	}

	public int getLeagueSize() {
		return this.leagueTeams.size();
	}

	public Set<String> getAllTeamNames() {
		return this.leagueTeams.keySet();
	}

	public int addOrUpdateTeam(SoccerTeam newTeam) {
		SoccerTeam currTeam = leagueTeams.getOrDefault(newTeam.getTeamName(), null);
		if(isFull && currTeam == null) {
			System.err.println("league has been fix and not accepting new team now ! will do nothing and return");
            return -1;
		}
		if(currTeam == null) {
			currTeam = new SoccerTeam(newTeam.getTeamName());
		}

		currTeam.updateScore(newTeam.getTotalScore());

		this.leagueTeams.put(currTeam.getTeamName(), currTeam);
		return 0;
	}

	public List<SoccerTeam> getTopKTeams(int k) {
		List<SoccerTeam> retList = new ArrayList<SoccerTeam>();
		if(k <= 0) return retList;
		if(k >= this.leagueTeams.size()) k = this.leagueTeams.size();

		Map<String, SoccerTeam> sortedTeams = sortByValues(this.leagueTeams);
		
		for(SoccerTeam s : sortedTeams.values())
			retList.add(s);

		return retList.subList(0, k);
	}

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(
    	Map<K, V> inputMap) {
    	Comparator<K> valueComparator = new Comparator<K>() {
      		public int compare(K k1, K k2) {
        		int compare = inputMap.get(k1).compareTo(inputMap.get(k2));
        		if (compare == 0) 
          			return 1;
        		else 
          			return -compare;
          	}
        };
        Map<K, V> sortedMap = new TreeMap<K, V>(valueComparator);
        sortedMap.putAll(inputMap);
        return sortedMap;
    }

}