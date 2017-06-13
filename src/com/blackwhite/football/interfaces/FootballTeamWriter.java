package com.blackwhite.football.service.interfaces;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.provider.DataProvider;
import com.blackwhite.football.service.provider.FootballTeamJSONHandler;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

/**
* 
* @author T Price
* @copyright: 2017 Black & White Systems Ltd
* @version: 1
* 
* CLass that provides the POST method to allow a team to be added or updated
*/
@Path("/post")
public class FootballTeamWriter {

	private static Logger LOGGER = Logger.getLogger(FootballTeamWriter.class);

	@POST
	@Path("/addTeam")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addTeam(@QueryParam("team") String jsonMessage) {
		
		LOGGER.info("FootballTeamWriter::addTeam: post received to add team");
		String result = "";
		FootballTeam team = FootballTeamJSONHandler.convertFromString(jsonMessage);		
		if (team != null) {
			DataProvider.addTeam(team);
			result = "Team " + team.getName() + " added";
		} else {
			result = "Team has not been added as it is not in the correct format";
		}
		return Response.status(200).entity(result).build();
	}

}
