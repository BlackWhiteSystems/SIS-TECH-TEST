package com.blackwhite.football.service.provider;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.blackwhite.football.service.data.FootballTeam;

/**
* 
* @author T Price
* @copyright: 2017 Black & White Systems Ltd
* @version: 1
* 
* Provides static methods to convert a Football Team to/from a String that is in JSON format
*/
public final class FootballTeamJSONHandler {

	private static Logger LOGGER = Logger.getLogger(FootballTeamJSONHandler.class);

	private FootballTeamJSONHandler() {
		// prevent instantiation
	}
	
	
	public static FootballTeam convertFromString(String jsonRepresentation) {
		
		if ((jsonRepresentation == null) || (jsonRepresentation.length() == 0)) {
			LOGGER.error("FootballTeamJSONHandler::convertFromString: no message string passed");
			return null;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		FootballTeam team = null;
		
		try {
			team = mapper.readValue(jsonRepresentation, FootballTeam.class);
		} catch (IOException ioEx) {
			LOGGER.error("FootballTeamJSONHandler::convertFromString: error converting JSON message./nError is " + ioEx.getMessage());
		}
		
		return team;
	}
	
	
	public static String convertToString(FootballTeam team) {
		
		if (team == null) {
			LOGGER.error("FootballTeamJSONHandler::convertToString: null object passed.");
			return null;
		}
		
		StringBuffer jsonRepresentation = null;
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		try {
			 mapper.writeValue(writer, team);
		
		} catch (IOException ioEx) {
			LOGGER.error("FootballTeamJSONHandler::convertToString: error converting JSON message./nError is " + ioEx.getMessage());			
		}
		
		jsonRepresentation = writer.getBuffer();		
		return jsonRepresentation.toString();
	}

}
