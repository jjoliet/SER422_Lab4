package org.tempuri;

import java.io.IOException;
import java.rmi.RemoteException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("serial")
public class WeatherClient extends HttpServlet {
	private static IService client;
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		client = new IServiceProxy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = "text/html";
		response.setContentType(contentType);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(client.getWeather("Denver", "Colorado"));
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Using for verification currently.
//		System.out.println(request.getParameter("state_names_for_weather"));
//		System.out.println(request.getParameter("city_name_weather"));
//		System.out.println(request.getParameter("hourly_check"));
//		System.out.println(request.getParameter("Ten_day_check"));
		String contentType = "text/html";
		response.setContentType(contentType);
		response.setStatus(HttpServletResponse.SC_OK);
		//response.getWriter().print(client.getWeather_hourly("Denver", "Colorado", true));
		String temp = client.getWeather_tenDays("Denver", "Colorado", true);
		System.out.println("ok so far");
		JsonParser parser = new JsonParser();
		//JsonObject obj = parser.parse(temp).getAsJsonObject().get("current_observation").getAsJsonObject();
		//System.out.println("Ok still... " + obj.get("weather")); Works for getting initial objects
		
//		JsonObject obj = parser.parse(temp).getAsJsonObject();
//		JsonArray arr = obj.get("hourly_forecast").getAsJsonArray();
//		System.out.println("Here you go " + arr.get(0).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("pretty"));
		
		JsonObject obj = parser.parse(temp).getAsJsonObject().get("forecast").getAsJsonObject().get("simpleforecast").getAsJsonObject();
		JsonArray arr = obj.get("forecastday").getAsJsonArray();
		System.out.println("Here you go " + arr.get(0).getAsJsonObject());
		
		response.getWriter().print(temp);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		//Process request Parameters
		boolean hourly = request.getParameter("hourly_check").equalsIgnoreCase("on") ? true : false;
		boolean tenDay = request.getParameter("Ten_day_check").equalsIgnoreCase("on") ? true : false;
		String city_name = request.getParameter("city_name_weather");
		String state_name = request.getParameter("state_names_for_weather");
		String json_String ="";
		JsonParser parser = new JsonParser();
		//Determine which function to call. Defaults to "tenDay" if both hourly and ten day forecast are checked.
		try {
			if(tenDay) {
				json_String = client.getWeather_tenDays(city_name, state_name, tenDay);				
				JsonArray obj = parser.parse(json_String).getAsJsonObject().get("forecast").getAsJsonObject().get("simpleforecast").getAsJsonObject().get("forecastday").getAsJsonArray();
				for(JsonElement el: obj) {
					
				}
			}else if(hourly) {
				json_String = client.getWeather_hourly(city_name, state_name, hourly);
			}
			else {
				json_String = client.getWeather(city_name, state_name);
			}
		} catch (Exception e) {
			//Bad handling, but for now, what I will have.
			e.printStackTrace();
		}
		
		
	}

}
