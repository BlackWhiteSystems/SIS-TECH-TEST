package test.com.blackwhite.football.service.web;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
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
 * JUnit tests for GET methods over HTTP
 *
 */
public class GetFootballTeamTest {

	@Test
	public void testGetTeamDetails() {

		HttpURLConnection connection = null;				
	    try
	    {
   		 	StringBuffer urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20A%22,%22stadiumCapacity%22:25000");
   		 	urlStr.append(",%22city%22:%22London%22,%22competition%22:%22FA%20Cup%22,%22noOfPlayers%22:30");
   		 	urlStr.append(",%22creationYear%22:1966,%22creationMonth%22:7,%22creationDay%22:29,%22owner%22:%22Mr%20A%22}");
   		 	URL url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    int responseCode = connection.getResponseCode();

			 urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/query/getTeamDetails?name=Team%20A");
	    	 url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(12));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    responseCode = connection.getResponseCode();
		    assertEquals(200, responseCode);
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			FootballTeam team = FootballTeamJSONHandler.convertFromString(response.toString());	
			FootballTeam reference = TestConstants.getTestTeam();
			assertEquals(reference.getName(), team.getName());
			assertEquals(reference.getOwner(), team.getOwner());
			assertEquals(reference.getStadiumCapacity(), team.getStadiumCapacity());
			assertEquals(reference.getNoOfPlayers(), team.getNoOfPlayers());
			assertEquals(reference.getCompetition(), team.getCompetition());
			assert(reference.getCreationDate().equals(team.getCreationDate()));
			
		 } catch (Exception e) {
		    e.printStackTrace();
		 } finally {
			 if (connection != null) {
				 connection.disconnect();
			 }
		 }	  
	}
	
	@Test
	public void testGetTeamList() {
		HttpURLConnection connection = null;				
	    try
	    {
   		 	StringBuffer urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20A%22,%22stadiumCapacity%22:25000}");
   		 	URL url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    int responseCode = connection.getResponseCode();
		    
   		 	urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20C%22,%22stadiumCapacity%22:55000}");
   		 	url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    responseCode = connection.getResponseCode();
		    
   		 	urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20B%22,%22stadiumCapacity%22:35000}");
   		 	url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    responseCode = connection.getResponseCode();


			urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/query/getTeamList");
	    	url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(12));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    responseCode = connection.getResponseCode();
		    assertEquals(200, responseCode);
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONArray messages = new JSONArray(response.toString());
			assertEquals(messages.length(), 3);
			JSONObject message = (JSONObject)messages.get(0);
			assertEquals("Team A", message.get("name"));
			
			message = (JSONObject)messages.get(1);
			assertEquals("Team B", message.get("name"));
		
			message = (JSONObject)messages.get(2);
			assertEquals("Team C", message.get("name"));

		 } catch (Exception e) {
		    e.printStackTrace();
		 } finally {
			 if (connection != null) {
				 connection.disconnect();
			 }
		 }	  		
	}
	
	@Test
	public void testGetByCapacity() {
		HttpURLConnection connection = null;				
	    try
	    {
   		 	StringBuffer urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20A%22,%22stadiumCapacity%22:25000}");
   		 	URL url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    int responseCode = connection.getResponseCode();
		    
   		 	urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20B%22,%22stadiumCapacity%22:55000}");
   		 	url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    responseCode = connection.getResponseCode();
		    
   		 	urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/post/addTeam?team={%22name%22:%22Team%20C%22,%22stadiumCapacity%22:35000}");
   		 	url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(200));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
		    responseCode = connection.getResponseCode();


			urlStr = new StringBuffer("http://localhost:8080/FootballTeamLookup/footballservice/query/getByCapacity");
	    	url = new URL(urlStr.toString());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/json ");
		    connection.setRequestProperty("Content-Length", Integer.toString(12));
		    connection.setRequestProperty("Content-Language", "en-US");  
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    responseCode = connection.getResponseCode();
		    assertEquals(200, responseCode);
		    
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONArray messages = new JSONArray(response.toString());
			assertEquals(messages.length(), 3);
			JSONObject message = (JSONObject)messages.get(0);
			assertEquals("Team A", message.get("name"));
			assertEquals(25000, message.get("stadiumCapacity"));
			
			message = (JSONObject)messages.get(1);
			assertEquals("Team C", message.get("name"));
			assertEquals(35000, message.get("stadiumCapacity"));
			
			message = (JSONObject)messages.get(2);
			assertEquals("Team B", message.get("name"));
			assertEquals(55000, message.get("stadiumCapacity"));

		 } catch (Exception e) {
		    e.printStackTrace();
		 } finally {
			 if (connection != null) {
				 connection.disconnect();
			 }
		 }	  		
	}

}
