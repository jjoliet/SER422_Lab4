package org.tempuri;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
		processRequest(request,response);
		request.getRequestDispatcher("./index.jsp");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Using for verification currently.
		response.setStatus(HttpServletResponse.SC_OK);
		processRequest(request, response);
		request.getRequestDispatcher(response.encodeURL("./index.jsp")).forward(request, response);

	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		//Process request Parameters
		String hourly = request.getParameter("hourly_check");
		String tenDay = request.getParameter("Ten_day_check");
		String city_name = request.getParameter("city_name_weather");
		String state_name = request.getParameter("state_names_for_weather");
		String json_String ="";
		ArrayList<Weather> al = new ArrayList<Weather>();
		JsonParser parser = new JsonParser();
		//Determine which function to call. Defaults to "tenDay" if both hourly and ten day forecast are checked.
		try {
			if(tenDay!= null) {
				json_String = client.getWeather_tenDays(city_name, state_name, true);		
				JsonArray obj = parser.parse(json_String).getAsJsonObject().get("forecast").getAsJsonObject().get("simpleforecast").getAsJsonObject().get("forecastday").getAsJsonArray();
				for(JsonElement el: obj) {
					JsonObject temp = el.getAsJsonObject();
					String temp2 = temp.get("high").getAsJsonObject().get("fahrenheit") +" " + temp.get("high").getAsJsonObject().get("celsius");
					String temp3 = temp.get("low").getAsJsonObject().get("fahrenheit") +" " + temp.get("low").getAsJsonObject().get("celsius");
					al.add(new Weather("tenDay", temp.get("date").getAsJsonObject().get("weekday_short").getAsString(), temp.get("icon_url").getAsString(), temp2, temp3, temp.get("icon").getAsString()));
				
				}
			}else if(hourly!=null) {
				json_String = client.getWeather_hourly(city_name, state_name, true);
				JsonArray obj = parser.parse(json_String).getAsJsonObject().get("hourly_forecast").getAsJsonArray();
				for(JsonElement el: obj) {
					JsonObject temp = el.getAsJsonObject();
					String temp_string = temp.get("temp").getAsJsonObject().get("english") + " " + temp.get("temp").getAsJsonObject().get("metric");
					String feel_string = temp.get("feelslike").getAsJsonObject().get("english") + " " + temp.get("feelslike").getAsJsonObject().get("metric");
					al.add(new Weather("hourly", temp.get("FCTTIME").getAsJsonObject().get("pretty").getAsString(), temp.get("icon_url").getAsString(), temp_string, feel_string, temp.get("humidity").getAsString(), temp.get("wx").getAsString()));
				}
			}
			else {
				json_String = client.getWeather(city_name, state_name);
				JsonObject obj = parser.parse(json_String).getAsJsonObject().get("current_observation").getAsJsonObject();
				al.add(new Weather("current", obj.get("observation_time").getAsString(), obj.get("weather").getAsString(), obj.get("temperature_string").getAsString(), obj.get("feelslike_string").getAsString(), obj.get("relative_humidity").getAsString(), obj.get("wind_string").getAsString(), obj.get("wind_dir").getAsString()));
			}
		} catch (Exception e) {
			//Bad handling, but for now, what I will have.
			e.printStackTrace();
		}
		
		Weather[] list = al.toArray(new Weather[al.size()]);
		request.setAttribute("list", list);		
	}


}
