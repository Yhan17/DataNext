package br.unitins.datanext.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiGraphic {
	
	public static String connectionSoy() {
		try {
        	
        	String url = "https://www.quandl.com/api/v3/datasets/CEPEA/SOYBEAN_C.json?api_key=398PRmtv-QNJSQuxhS-4";
        	
        	HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        	
        	conn.setRequestMethod("GET");
        	conn.setRequestProperty("Accept", "application/json");
        	
        	 if (conn.getResponseCode() != 200) {
        		 System.out.println("Não FUNCIONOU :(");
             }

             BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

             String output = "";
             String line;
             while ((line = br.readLine()) != null) {
                 output += line;
             }

             conn.disconnect();
             
             return output;

        }catch(IOException ex) {

        	return null;
        }	
	}
	
	public static String connectionCorn() {
		try {
			
			String url = "https://www.quandl.com/api/v3/datasets/CEPEA/CORN.json?api_key=398PRmtv-QNJSQuxhS-4";
			
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				System.out.println("Não FUNCIONOU :(");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String output = "";
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}
			
			conn.disconnect();
			
			return output;
			
		}catch(IOException ex) {
			
			return null;
		}	
	}
}
