package br.unitins.datanext.unity.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.google.gson.Gson;

import br.unitins.datanext.models.GrainApi;

public class Main {

	public static void main(String[] args) {
        try {
        	
        	String url = "https://www.quandl.com/api/v3/datasets/CEPEA/SOYBEAN_C.json?api_key=";
        	
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
//             System.out.println(br.readLine());
             Gson gson = new Gson();
             GrainApi dados = gson.fromJson(new String(output.getBytes()), GrainApi.class);
             
//             System.out.println(dados);
//        	 System.out.println(Arrays.toString(dados.getDataset().getData()[0]));
//        	 System.out.println(dados.getDataset().getData()[0][0]);
        	 
        	 for (int i = 6; i > 0; i--) {
            	 System.out.println(dados.getDataset().getData()[i][0]);
				
			}
        	 
//        	 LocalDate dt = LocalDate.parse(dados.getDataset().getData()[1][0]); 
//        	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
//             System.out.println(formatter.format(dt)); 
             System.out.println(DateTimeFormatter.ofPattern("dd/MM/YYYY").format(LocalDate.parse(dados.getDataset().getData()[1][0])));
        	 
//        	 System.out.println(LocalDate.parse(dados.getDataset().getData()[1][0] ,DateTimeFormatter.ofPattern( "dd/MM/uuuu" )));
        	 
//             for (int i = 0; i < dados.getDataset().getData().length; i++) {
//				
//            	 System.out.println(Arrays.toString(dados.getDataset().getData()[i]));
//			}
//             System.out.println(output);
        }catch(IOException ex) {

        }	

	}

}
