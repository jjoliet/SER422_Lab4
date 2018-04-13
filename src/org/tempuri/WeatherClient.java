package org.tempuri;

import java.rmi.RemoteException;

public class WeatherClient {

	public static void main(String[] args) {
		IService client = new IServiceProxy();
		try {
			System.out.println("get Weather: "  + client.getWeather("Denver", "Colorado"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
