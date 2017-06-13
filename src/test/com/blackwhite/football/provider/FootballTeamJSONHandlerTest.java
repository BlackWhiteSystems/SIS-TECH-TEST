package test.com.blackwhite.football.service.provider;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.provider.FootballTeamJSONHandler;

import test.com.blackwhite.football.testdata.TestConstants;

/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * JUnit tests for the service provider that converts to/from JSON messages
 *
 */
public class FootballTeamJSONHandlerTest {

	@Test
	public void TestToJSONMessage() {
		
		FootballTeam team = TestConstants.getTestTeam();		
		String jsonMessage = FootballTeamJSONHandler.convertToString(team);	
		
		assert(jsonMessage.contains(team.getCity()));
		assert(jsonMessage.contains(team.getName()));
		assert(jsonMessage.contains(team.getCompetition()));
		assert(jsonMessage.contains(team.getOwner()));
		assert(jsonMessage.contains(team.getNoOfPlayers().toString()));
		assert(jsonMessage.contains(team.getStadiumCapacity().toString()));
		assert(jsonMessage.contains(team.getCreationYear().toString()));
		assert(jsonMessage.contains(team.getCreationMonth().toString()));
		assert(jsonMessage.contains(team.getCreationDay().toString()));

	}
	
	@Test
	public void TestFromJSONMessage() {
		
		FootballTeam team = FootballTeamJSONHandler.convertFromString(TestConstants.getJSONMessageAsString());
		assertEquals("London", team.getCity());
		assertEquals("FA Cup", team.getCompetition());
		assertEquals("Team A", team.getName());
		assertEquals("Mr A", team.getOwner());
		assertEquals(new Integer(30), team.getNoOfPlayers());
		assertEquals(new Integer(25000), team.getStadiumCapacity());
		
		LocalDate date = LocalDate.of(1966, 7, 29);		
		assert(date.isEqual(team.getCreationDate()));

	}
}
