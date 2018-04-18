<%@ page import="org.tempuri.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<Head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<Title>SER422 Lab4 Task 1: Weather Service</Title></Head>
<form method="post" id="formID" action="./getWeather">
<div>
	<table>
    	<tbody><tr>
        	<td class="auto-style1">
            <span id="state_name_lable_weather">State Name:</span>
  			</td>
  <%
  String city_val = request.getParameter("city_name_weather");
  String selected = request.getParameter("state_names_for_weather");
  if(city_val == null){
	  city_val = "";
  }
  if(selected == null){
	  selected = "";
  }
  %>
  <td style="width: 10%">
  <select name="state_names_for_weather" id="state_names_for_weather" title="Please select state from list">
	<option value="<%= selected %>" selected disabled hidden><%= selected %></option>
	<option>Alabama</option>
	<option>Alaska</option>
	<option>Arizona</option>
	<option>Arkansas</option>
	<option>California</option>
	<option>Colorado</option>
	<option>Connecticut</option>
	<option>District of Columbia</option>
	<option>Delaware</option>
	<option>Florida</option>
	<option>Georgia</option>
	<option>Hawaii</option>
	<option>Idaho</option>
	<option>Illinois</option>
	<option>Indiana</option>
	<option>Iowa</option>
	<option>Kansas</option>
	<option>Kentucky</option>
	<option>Louisiana</option>
	<option>Maine</option>
	<option>Maryland</option>
	<option>Massachusetts</option>
	<option>Michigan</option>
	<option>Minnesota</option>
	<option>Mississippi</option>
	<option>Missouri</option>
	<option>Montana</option>
	<option>Nebraska</option>
	<option>Nevada</option>
	<option>New Hampshire</option>
	<option>New Jersey</option>
	<option>New Mexico</option>
	<option>New York</option>
	<option>North Carolina</option>
	<option>North Dakota</option>
	<option>Ohio</option>
	<option>Oklahoma</option>
	<option>Oregon</option>
	<option>Pennsylvania</option>
	<option>Rhode Island</option>
	<option>South Carolina</option>
	<option>South Dakota</option>
	<option>Tennessee</option>
	<option>Texas</option>
	<option>Utah</option>
	<option>Vermont</option>
	<option>Virginia</option>
	<option>Washington</option>
	<option>West Virginia</option>
	<option>Wisconsin</option>
	<option>Wyoming</option>
</select>
                            </td>
                            <td class="auto-style3"></td>
                            <td style="width: 10%">
                                <span id="city_name_text">City Name</span>
                            </td>
                            <td style="width: 10%">
                                <input name="city_name_weather" type="text" id="city_name_weather" title="Please enter city" value="<%= city_val %>" style="width:155px;">
                            </td>
                            <td class="auto-style3"></td>
                            <td class="auto-style5">
                                <span id="hourly_text">Hourly</span>
                            </td>
                            <td class="auto-style6">
                                <span title="Check for Hourly Update"><input id="hourly_check" type="checkbox" name="hourly_check"></span>
                            </td>
                            <td class="auto-style3">
                                <span id="ten_day_text">10 Days</span>
                            </td>
                            <td class="auto-style5">
                                <span title="Check for 10 days update"><input id="Ten_day_check" type="checkbox" name="Ten_day_check"></span>
                            </td>
                            <td style="width: 10%">
                                <input type="submit" name="get_weather" value="Get Weather" id="get_weather" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false">
                            </td>
                        </tr>
                    </tbody></table>
                </div>
</form>
<%
Weather[] theWeather = (Weather[])request.getAttribute("list");
if(theWeather != null){
	out.write("<br><h3>Weather for: " + city_val +", " + selected + ":</h3>"); 
	if(theWeather[0].getType() == "current"){
		out.write("<br><div style=\"float:left;\">"+ theWeather[0].getTime()+"</div><br><br>");
		out.write("<table border=\"1\" style=\"border-collapse:collapse;\">");
		out.write("<tr><th>Weather</th><th>Temp(F & C)</th><th>Feels Like (F & C)</th><th>Relative Humidity(%)</th><th>Wind</th><th>Wind Direction</th>");
	}else if(theWeather[0].getType() == "hourly"){		
			out.write("<br>");
			out.write("<table border=\"1\" style=\"border-collapse:collapse;\">");
			out.write("<tr><th>Time</th><th>Weather</th><th>Temp(F & C)</th><th>Feels Like (F & C)</th><th>Relative Humidity(%)</th><th>Prediction</th>");
	}else{
		out.write("<br>");
		out.write("<table border=\"1\" style=\"border-collapse:collapse;\">");
		out.write("<tr><th>Day</th><th>Weather</th><th>High(F & C)</th><th>Low(F & C)</th><th>Prediction</th>");
	}
	for(Weather obj: theWeather){
		out.write(obj.toHTML());
	}
	out.write("</table>");
}
%>

</HTML>