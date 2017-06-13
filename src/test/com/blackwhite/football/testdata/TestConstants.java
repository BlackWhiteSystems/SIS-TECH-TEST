package test.com.blackwhite.football.testdata;

import com.blackwhite.football.service.data.FootballTeam;

/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * Provides objects to be used in JUnit tests
 *
 */
public class TestConstants {

	private static final String TEAM_AS_STRING = "{\"name\":\"Team A\",\"city\":\"London\",\"competition\":\"FA Cup\",\"noOfPlayers\":30,\"stadiumCapacity\":25000,\"creationYear\":1966,\"creationMonth\":7,\"creationDay\":29,\"owner\":\"Mr A\"}";
	private static final FootballTeam TEAM = new FootballTeam();
	static {
		TEAM.setCity("London");		
		TEAM.setCompetition("FA Cup");
		TEAM.setName("Team A");
		TEAM.setOwner("Mr A");
		TEAM.setNoOfPlayers(30);
		TEAM.setStadiumCapacity(25000);		
		TEAM.setCreationYear(1966);
		TEAM.setCreationMonth(7);
		TEAM.setCreationDay(29);
		
	}
	
	private TestConstants() {
		// prevent instantiation
	}
	
	public static FootballTeam getTestTeam() {
		return TEAM;
	}
	
	public static String getJSONMessageAsString() {
		return TEAM_AS_STRING;
	}
	
	public static String getTeamName() {
		return "Team A";
	}
}
