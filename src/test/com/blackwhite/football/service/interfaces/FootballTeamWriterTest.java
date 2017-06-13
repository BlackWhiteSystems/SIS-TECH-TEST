package test.com.blackwhite.football.service.interfaces;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.interfaces.FootballTeamWriter;
import com.blackwhite.football.service.provider.DataProvider;

import test.com.blackwhite.football.testdata.TestConstants;

/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * JUnit tests for POST method
 */
public class FootballTeamWriterTest {

	@Test
	public void testAddTeam() {
		String jsonMessage = TestConstants.getJSONMessageAsString();
		
		FootballTeamWriter writer = new FootballTeamWriter();
		
		Response response = writer.addTeam(jsonMessage);
		assertEquals(response.getEntity(), "Team Team A added");
		FootballTeam refTeam = TestConstants.getTestTeam();
		FootballTeam team = DataProvider.getTeamDetails(TestConstants.getTeamName());
		
		assertEquals(refTeam.getName(), team.getName());
		assertEquals(refTeam.getCity(), team.getCity());
		assertEquals(refTeam.getCompetition(), team.getCompetition());
		assertEquals(refTeam.getNoOfPlayers(), team.getNoOfPlayers());
		assertEquals(refTeam.getStadiumCapacity(), team.getStadiumCapacity());
		assertEquals(refTeam.getOwner(), team.getOwner());
		assert(refTeam.getCreationDate().isEqual(team.getCreationDate()));

	}
}
