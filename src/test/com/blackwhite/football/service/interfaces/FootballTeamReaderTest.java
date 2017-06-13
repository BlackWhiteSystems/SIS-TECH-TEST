package test.com.blackwhite.football.service.interfaces;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.blackwhite.football.service.data.FootballTeam;
import com.blackwhite.football.service.interfaces.FootballTeamReader;
import com.blackwhite.football.service.provider.DataProvider;
import com.blackwhite.football.service.provider.FootballTeamJSONHandler;

import test.com.blackwhite.football.testdata.TestConstants;

/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * JUnit tests for the GET methods 
 *
 */
public class FootballTeamReaderTest {

	@Test
	public void testGetTeamDetails() {	
		FootballTeamReader reader = new FootballTeamReader();		
		Response response = reader.getTeamDetails("Team C");
		Object entity = response.getEntity();
		FootballTeam returnedTeam = FootballTeamJSONHandler.convertFromString(entity.toString());		

		assertEquals("Team C", returnedTeam.getName());
	}
	
	@Test
	public void testGetTeamList() {
		JSONArray messages = null;
		FootballTeamReader reader = new FootballTeamReader();		
		Response response = reader.getTeamList();
		Object entity = response.getEntity();
		try {
			messages = new JSONArray(entity.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			return;
		}
		
		assertEquals(4, messages.length());
		
		FootballTeam team = null;
		Object message;
		try {
			message = messages.get(0);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals("Team A", team.getName());
			message = messages.get(1);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals("Team B", team.getName());
			message = messages.get(2);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals("Team C", team.getName());
			message = messages.get(3);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals("Team D", team.getName());
			
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}		
	}
	
	@Test
	public void testGetByCapacity() {
		JSONArray messages = null;
		FootballTeamReader reader = new FootballTeamReader();		
		Response response = reader.getByCapacity();
		Object entity = response.getEntity();
		try {
			messages = new JSONArray(entity.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			return;
		}
		
		assertEquals(4, messages.length());
		
		FootballTeam team = null;
		Object message;
		try {
			message = messages.get(0);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals(new Integer(10000), team.getStadiumCapacity());
			message = messages.get(1);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals(new Integer(10010), team.getStadiumCapacity());
			message = messages.get(2);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals(new Integer(25000), team.getStadiumCapacity());
			message = messages.get(3);
			team = FootballTeamJSONHandler.convertFromString(message.toString());
			assertEquals(new Integer(50000), team.getStadiumCapacity());
			
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@Before
	public void setUp() {
		
		FootballTeam team1 = TestConstants.getTestTeam();		
		DataProvider.addTeam(team1);
		FootballTeam team2 = team1.copy();
		team2.setName("Team B");
		team2.setStadiumCapacity(10000);
		DataProvider.addTeam(team2);
		FootballTeam team3 = team1.copy();
		team3.setName("Team C");
		team3.setStadiumCapacity(50000);
		DataProvider.addTeam(team3);
		FootballTeam team4 = team1.copy();
		team4.setName("Team D");
		team4.setStadiumCapacity(10010);
		DataProvider.addTeam(team4);		
	}
	
}
