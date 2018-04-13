package org.tempuri;

import java.rmi.RemoteException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Jason Joliet
 * Creates the weather client for WSDL @ http://vhost3.cs.rit.edu/weather/Service.svc?wsdl
 *
 */
@SuppressWarnings("serial")
public class WeatherClientServlet extends HttpServlet{
	
	/**
	 * Initialize the servlet
	 */
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		
	}
	/**
	 * This happens on a get Request (Nothing)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		IService client = new IServiceProxy();
		try {
			System.out.println("get Weather: "  + client.getWeather("Denver", "Colorado"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
		
		

	

}
