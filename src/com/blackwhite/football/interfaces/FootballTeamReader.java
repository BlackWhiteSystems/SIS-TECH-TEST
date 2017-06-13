package com.blackwhite.football.service.interfaces;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.data.helper.TeamCapacityComparator;
import com.blackwhite.football.service.provider.DataProvider;

/**
* 
* @author T Price
* @copyright: 2017 Black & White Systems Ltd
* @version: 1
* 
* Class that provides the following GET methods :
*  get a single team's details;
*  get a list of all teams ordered by team name;
*  get a list of all teams ordered by ascending stadium capacity 
*/
@Path("/query")
public class FootballTeamReader {

	private static Logger LOGGER = Logger.getLogger(FootballTeamReader.class);

	@GET
	@Path("/getTeamDetails")
	@Produces("application/json")
	public Response getTeamDetails(@QueryParam("name") String name) {
		
		LOGGER.info("FootballTeamReader::getTeamDetails: team detail look up request received");
		String result = "";
		FootballTeam team = DataProvider.getTeamDetails(name);
		if (team != null) {
			try {
				LOGGER.info("FootballTeamReader::getTeamDetails: details for " + name + " retrieved");
				result = convertToJSON(team).toString();		
			} catch (JSONException jEx) {
				LOGGER.error("FootballTeamReader::getTeamDetails: error creating JSON message\n" + jEx.getMessage());
				result = "Lookup failed - error reading team details";
			}
		} else {
			LOGGER.info("FootballTeamReader::getTeamDetails: no team found for " + name);
			result = "No details found for team " + name;
		}
	
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/getTeamList")
	@Produces("application/json")
    public Response getTeamList() {
		LOGGER.info("FootballTeamReader::getTeamList: full team list request received");
		List<FootballTeam> teamList = DataProvider.getAllTeams();
		String result = null;
		if ((teamList == null) || (teamList.size() == 0)) {
			result = "No teams found";
		} else {
			try {
				JSONArray messages = new JSONArray();
				for (FootballTeam team : teamList) {
					messages.put(convertToJSON(team));
				}
				result = messages.toString();
				
			} catch (JSONException jEx) {
				LOGGER.error("FootballTeamReader::getTeamList: error creating JSON message\n" + jEx.getMessage());
				result = "Lookup failed - error reading team list";				
			}		
		}
		
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/getByCapacity")
	@Produces("application/json")
    public Response getByCapacity() {
		LOGGER.info("FootballTeamReader::getByCapacity:  team list ordered by stadium capacity request received");
		List<FootballTeam> teamList = DataProvider.getAllTeams();
		teamList.sort(new TeamCapacityComparator());
		
		String result = null;
		if ((teamList == null) || (teamList.size() == 0)) {
			result = "No teams found";
		} else {
			try {
				JSONArray messages = new JSONArray();
				for (FootballTeam team : teamList) {
					messages.put(convertToJSON(team));
				}
				result = messages.toString();
				
			} catch (JSONException jEx) {
				LOGGER.error("FootballTeamReader::getByCapacity: error creating JSON message\n" + jEx.getMessage());
				result = "Lookup failed - error reading team list";				
			}		
		}
		
		return Response.status(200).entity(result).build();
	}
	
	
	private JSONObject convertToJSON(FootballTeam team) throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", team.getName()); 
		jsonObject.put("city", team.getCity()); 
		jsonObject.put("competition", team.getCompetition()); 
		jsonObject.put("owner", team.getOwner()); 
		jsonObject.put("noOfPlayers", team.getNoOfPlayers()); 
		jsonObject.put("stadiumCapacity", team.getStadiumCapacity()); 
		jsonObject.put("creationYear", team.getCreationYear()); 
		jsonObject.put("creationMonth", team.getCreationMonth()); 
		jsonObject.put("creationDay", team.getCreationDay()); 
		
		return jsonObject;
	}
}
