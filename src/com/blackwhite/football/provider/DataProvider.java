package com.blackwhite.football.service.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.data.helper.TeamComparator;
/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * Maintains in memory the list of football teams that have been
 * added to the system
 * To prevent concurrency issues a ConcurrentHashMap is used to store the data in memory
 *
 */
public class DataProvider {

	private static Map<String, FootballTeam> TEAM_MAP = new ConcurrentHashMap<String, FootballTeam>();
	private static Logger LOGGER = Logger.getLogger(DataProvider.class);

	private DataProvider() {
		// prevent instantiation
	}
	
	/**
	 * Adds a team to the data repository
	 * @param team - Football team to be added to repository
	 */
	public static void addTeam(FootballTeam team) {
		if (team == null) {
			LOGGER.error(" ***** DataProvider::addTeam: team is null so unable to add");
			return;
		}
		
		if (TEAM_MAP.containsKey(team.getName())) {
			LOGGER.info(" ****** DataProvider::addTeam: updating team data for " + team.getName());
		} else {
			LOGGER.info(" ****** DataProvider::addTeam: adding new team data for " + team.getName());
		}
		
		TEAM_MAP.put(team.getName(), team);
	}
	
	/**
	 * retrieves a team from the data repository
	 * @param name - name of team to look up
	 * @return - Football team if present or null
	 */
	public static FootballTeam getTeamDetails(String name) {
		if (name == null) {
			LOGGER.error(" ***** DataProvider::getTeamDetails: name is null so unable to look up");
			return null;
		}
		return TEAM_MAP.get(name);	
	}
	
	/**
	 * returns the list of all teams in the data repository
	 * @return List of copies of all FootballTeams in the repository sorted by team name
	 */
	public static List<FootballTeam> getAllTeams() {
		List<FootballTeam> teamList = new ArrayList<FootballTeam>(TEAM_MAP.size());
		
		for (FootballTeam team : TEAM_MAP.values()) {
			teamList.add(team.copy());	
		}
		
		teamList.sort(new TeamComparator());
		return teamList;
	}
}
