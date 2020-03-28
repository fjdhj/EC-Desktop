package fr.fjdhj.ECDesktop.com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest {
	
	public static final String LOGIN_LINK = "https://api.ecoledirecte.com/v3/login.awp";
	public static final String LOGIN_CONTENT_TYPE = "application/x-www-form-urlencoded";
	
	/**
	 * Il est necessaire de remplacer id par l'ID de l'élève
	 */
	public static final String MESSAGE_LINK = "https://api.ecoledirecte.com/v3/Eleves/id/cahierdetexte.awp?verbe=get&";
	/**
	 * Il est necessaire de remplacer id par l'ID de l'élève et date par la date voulue
	 */
	public static final String WORK_LINK = "https://api.ecoledirecte.com/v3/Eleves/id/cahierdetexte/date.awp?verbe=get&";
	
	public static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";
	 
	/**
	 * Fait un requetes de type POST
	 * @param url L'URL de la requetes
	 * @param data Les informations
	 * @param contentType Le type de contenue
	 * @return
	 */
	public static String sendingPost(String url, String data, String contentType){
		StringBuffer response = null;
		DataOutputStream wr = null;
		BufferedReader in = null;
		
		try {
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection(); 
			
			//On paramettre la connexion
			con.setRequestMethod("POST");
			//con.setRequestProperty("User-Agent", "Mozilla/5.0");
			//con.setRequestProperty("Accept-Language", "fr-fr");
			con.setRequestProperty("Content-Type", contentType);
			
			//On envoie les donne
			con.setDoOutput(true);
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
			
			//On récupère la réponse
			int responseCode = con.getResponseCode();
			System.out.println("nSending 'POST' request to URL : " + url);
			System.out.println("Post Data : " + data);
			System.out.println("Response Code : " + responseCode);
			System.out.println("---------------------------------------------------------------------------------------------------");
			
			//On récupère les données
			in = new BufferedReader(
			          new InputStreamReader(con.getInputStream()));
			String output;
			response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {wr.close();} catch (IOException e){}
			try {in.close();} catch (IOException e){}
		}
		
		
		return response.toString();
	}
}
