package fr.fjdhj.ECDesktop.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {
	
	public static final String CSV_HEAD = "id,nom,prenom";
	

	public CSV() {}

	
	private static List<String> read(File Path) {
		System.out.println("Lecture du fichier "+Path.toString());
		BufferedReader bufferedreader = null;
		try {
			bufferedreader = new BufferedReader(new FileReader(Path));
			String strCurrentLine;
			List<String> content = new ArrayList<String>();
			
			while((strCurrentLine = bufferedreader.readLine()) != null) {
				content.add(strCurrentLine);
			}
			
			return content;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {bufferedreader.close();} catch (IOException e) {e.printStackTrace();}
		}
		return null;
	}
	
	public static List<String> readCSV(File Path, String separator) {
		//On lit le fichier
		List<String> content = read(Path);
		if(content == null) {
			return null;
		}
		System.out.println("Traitement des données");
		//On créer un tableau qui pour chaque ligne, a chaque clef, va écrire : clef1:contenu1,clef2:contenu2
		String[] head = content.get(0).split(separator);
		content.remove(0);
		
		String[] val;
		List<String> resp = new ArrayList<String>();
		for(String line : content) {
			val = line.split(separator);
			String str = "";
			for(int i = 0; i<val.length; i++) {
				str+=head[i]+":"+val[i]+",";
			}
			resp.add(str);
		}
		
		System.out.println("OK");
		System.out.println("------------------------------------------------------");
		
		return resp;
		
	}
	
	public static void writeCSV(File Path, String head, String[] content) {
		BufferedWriter bufferedwriter = null;
		System.out.println("Ecriture dans le fichier "+Path.toString());
		try {
			//On écrit dans le fichier
			bufferedwriter = new BufferedWriter(new FileWriter(Path));
			
			bufferedwriter.write(head+"\n");
			for(String str : content) {
				bufferedwriter.write(str+"\n");
			}
			
			System.out.println("OK");
			System.out.println("------------------------------------------------------");
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {bufferedwriter.flush();bufferedwriter.close();} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
	public static void addCSVLine(File Path, String content) {
		List<String> c = read(Path);
		if(c!=null) {
			System.out.println("Ajout d'une ligne ...");
			String finalContent="";
			for(int i = 1; i<c.size(); i++) {
				finalContent+=c.get(i)+"\n";
			}
			finalContent+=content;
			writeCSV(Path, c.get(0), finalContent.split("\n"));
		}
	}
	
	public static void removeCSVLine(File Path, String content) {
		List<String> c = read(Path);
		if(c!=null) {
			System.out.println("Supression d'une ligne ...");
			String finalContent="";
			for(int i = 1; i<c.size(); i++) {
				if(!c.get(i).equals(content)) 
					finalContent+=c.get(i)+"\n";
				else
					System.out.println("La ligne a été trouvé");
			}
			writeCSV(Path, c.get(0), finalContent.split("\n"));
		}
	}
	
}
