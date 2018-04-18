package org.tempuri;

//Easier way to have done all this would have been to pass the JSON string, and do the
//JSON strings in here, to abstract a layer from the servlet. (OOPS)
//Fix-> Sometime I have more time
public class Weather {
	private String type, time, weather;
	private String temp;
	private String feels, humidity;
	private String wind_or_low, dir_or_pred;
	
	//Constructor for the current weather.
	Weather(String type, String time, String weather, String temp, String feels, String humidity, String wind, String dir){
		this.type = type;
		this.time = time;
		this.weather = weather;
		this.temp = temp;
		this.feels = feels;
		this.humidity = humidity;
		this.wind_or_low = wind;
		this.dir_or_pred= dir;
	}
	
	//Constructor for Hourly forecast
	Weather(String type, String time, String weather, String temp, String feels, String humidity, String prediction){
		this.type = type;
		this.time = time;
		this.weather = weather;
		this.temp = temp;
		this.feels = feels;
		this.humidity = humidity;
		this.dir_or_pred = prediction;
	}
	
	//Constructor for 10day
	Weather(String type, String time, String weather, String high, String low, String prediction){
		this.type = type;
		this.time = time;
		this.weather = weather;
		this.dir_or_pred = prediction;
		this.temp = high;
		this.wind_or_low = low;	
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getFeels() {
		return feels;
	}

	public void setFeels(String feels) {
		this.feels = feels;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind_or_low() {
		return wind_or_low;
	}

	public void setWind_or_low(String wind_or_low) {
		this.wind_or_low = wind_or_low;
	}

	public String getDir_or_pred() {
		return dir_or_pred;
	}

	public void setDir_or_pred(String dir_or_pred) {
		this.dir_or_pred = dir_or_pred;
	}
	
	public String toHTML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr><td>");
		if(this.type == "current") {
			sb.append(this.weather + "</td><td>"+this.temp+"</td><td>"+this.feels+"</td><td>");
			sb.append(this.humidity+"</td><td>"+ this.wind_or_low+"</td><td>"+this.dir_or_pred+"</td></tr>");
			return sb.toString();
		} else if(this.type == "hourly") {
			sb.append(this.time+"</td><td>");
			sb.append("<img src=\"" +this.weather+"\">" + "</td><td>"+this.temp+"</td><td>"+this.feels+"</td><td>");
			sb.append(this.humidity+"</td><td>"+ this.dir_or_pred+"</td></tr>");
			return sb.toString();
		}else {
			sb.append(this.time+"</td><td>");
			sb.append("<img src=\"" +this.weather+"\">" + "</td><td>"+this.temp+"</td><td>");
			sb.append(this.wind_or_low+"</td><td>"+ this.dir_or_pred+"</td></tr>");
			return sb.toString();
		}
		//7:00 PM MDT on April 14, 2018</td><td class=" center"><img src="http://icons.wxug.com/i/c/k/partlycloudy.gif"></td><td class=" center">57 F (13 C)</td><td class=" center">33 F (1 C)</td><td class=" center">Partly Cloudy</td>

	}
	
}
