package test.com.blackwhite.football.service.data;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.blackwhite.football.service.data.FootballTeam;

/**
 * 
 * @author T Price
 * @copyright: 2017 Black & White Systems Ltd
 * @version: 1
 * 
 * JUnit tests for the FootballTeam POJO
 *
 */

public class FootballTeamTest {

	@Test
	public void testAssignment() {
		FootballTeam team = new FootballTeam();
		
		team.setCity("London");
		assertEquals("London", team.getCity());
		
		team.setCompetition("FA Cup");
		assertEquals("FA Cup", team.getCompetition());
		
		team.setName("Team A");
		assertEquals("Team A", team.getName());
		
		team.setOwner("Mr A");
		assertEquals("Mr A", team.getOwner());
		
		team.setNoOfPlayers(25);
		assertEquals(new Integer(25), team.getNoOfPlayers());
		
		team.setStadiumCapacity(25000);
		assertEquals(new Integer(25000), team.getStadiumCapacity());
		
		LocalDate date = LocalDate.of(1966, 7, 29);
		team.setCreationYear(1966);
		team.setCreationMonth(7);
		team.setCreationDay(29);
		
		assert(date.isEqual(team.getCreationDate()));
		
	}
}
