package fr.fjdhj.ECDesktop.data;

import java.util.Base64;

import fr.fjdhj.ECDesktop.com.CodeException;
import fr.fjdhj.ECDesktop.data.exception.idException;

public class TransformRawFile {

	public TransformRawFile() {}
	
	/**
	 * Permet de r�cup�rer les donn�es dans un fichier au format .json
	 * @param content Le contenue du fichier
	 * @param student L'�l�ve a qui attribuer le token
	 * @return Une chaine de caract�re contenant les donn�es
	 */
	public static String getData(String content, Student student) throws CodeException{		
		//On enl�ve les "
		content = content.replace("\"", "");
		//content = content.replace(" ", "");
		
		//On enl�ve la 1er et derni�re accolade
		content = content.substring(1, content.length()-1);
		
		String code = content.substring(content.indexOf("code:")+5, content.indexOf(",", content.indexOf("code:")));
		//Il se peut que le message sois le dernier, a ce moment l�, on prend tout jusqu'a la fin
		String message;
		if(content.indexOf(",", content.indexOf(" message:"))!=-1)
			message = content.substring(content.indexOf(" message:")+9, content.indexOf(",", content.indexOf(" message:")));
		else
			message = content.substring(content.indexOf(" message:")+9);
		
		if(!code.equals("200")) 
			throw new CodeException("Une erreur de code "+code+" est survenu : "+message);
		
		
		//On r�cup�re le token si il n'est pas vide on mets a jour
		String token = content.substring(content.indexOf("token:")+6, content.indexOf(",", content.indexOf("token:")));
		if(token != " ")
			student.setToken(token);
		
		//On r�cup�re les donn�e (normalement de data a la fin), on lui enl�ve data:{ et la derni�re }
		String data = content.substring(content.indexOf("data:")+6, content.length()-1);
		
		return data;
		
		
	}
	
	/**
	 * Enregistre les infos re�u suite a une requetes de connexion dans l'�l�ve student
	 * @param content
	 * @param student
	 */
	public static void loginData(String content, Student student) throws CodeException{
		//On r�cup�re les donn�es
		String data = getData(content, student);
		
		//On retire la partie : accounts:[{ et }] a la fin
		data = data.substring(11, data.length()-2);
		
		//On affecte les valeurs pour chaque variable de student
		student.setIdLogin(data.substring(data.indexOf(" idLogin:")+9, data.indexOf(",", data.indexOf(" idLogin:"))));
		student.setId(data.substring(data.indexOf(" id:")+4, data.indexOf(",", data.indexOf(" id:"))));
		student.setUid(data.substring(data.indexOf(" uid:")+5, data.indexOf(",", data.indexOf(" uid:"))));
		student.setPrenom(data.substring(data.indexOf(" prenom:")+8, data.indexOf(",", data.indexOf(" prenom:"))));
		student.setParticule(data.substring(data.indexOf(" particule:")+11, data.indexOf(",", data.indexOf(" particule:"))));
		student.setNom(data.substring(data.indexOf(" nom:")+5, data.indexOf(",", data.indexOf(" nom:"))));
		student.setEmail(data.substring(data.indexOf(" email:")+7, data.indexOf(",", data.indexOf(" email:"))));
		student.setAnneeScolaireCourante(data.substring(data.indexOf(" anneeScolaireCourante:")+23, data.indexOf(",", data.indexOf(" anneeScolaireCourante:"))));
		student.setNomEtablissement(data.substring(data.indexOf(" nomEtablissement:")+18, data.indexOf(",", data.indexOf(" nomEtablissement:"))));
		student.setAccessToken(data.substring(data.indexOf(" accessToken:")+13, data.indexOf(",", data.indexOf(" accessToken:"))));

	}
	
	
	public static void cahierDeTextData(String content, Student student) throws CodeException, idException{
		//On r�cup�re les donn�es
		String data = getData(content, student);
		
		String[] dayData = data.split("], ");
		System.out.println("----------------------------------------------");
		
		//On parcour notre tableau, rang� de cette mani�re : YYYY-MM-dd:[{matiere:..., codeMatiere:..., aFaire:..., idDevoir:..., documentsAFaire:..., donneLe:..., effectue:..., interrogation:..., rendreEnLigne:...}, {un autre devoir}, ...]
		for(String a : dayData) {
			//On r�cup�re la date du calendrier :
			Date day = student.getCalendar().get(a.substring(0,10), true);
			//On r�cup�re chaque trvaille ind�pendament
			a=a.substring(a.indexOf(":[{")+3);
			String[] work = a.split("},\\{");
			
			//On parcour notre list de travaille
			for(String w : work) {
				//On r�cup�re l'id du devoir
				String idDevoir = w.substring(w.indexOf(" idDevoir:")+10, w.indexOf(", ", w.indexOf(" idDevoir:")));
				Matiere codeMatiere  = Matiere.getMatiere(w.substring(w.indexOf(" codeMatiere:")+13, w.indexOf(", ", w.indexOf(" codeMatiere:"))));
				boolean documentsAFaire  = Boolean.parseBoolean(w.substring(w.indexOf(" documentsAFaire:")+17, w.indexOf(", ", w.indexOf(" documentsAFaire:"))));
				boolean effectue  = Boolean.parseBoolean(w.substring(w.indexOf(" effectue:")+10, w.indexOf(", ", w.indexOf(" effectue:"))));
				
				//Si il n'existe pas on le cr�er
				if(!day.contain(idDevoir)) {
					day.addHomeWork(new HomeWork(codeMatiere, idDevoir, documentsAFaire, effectue, true));
				//Si non on peut toujours met a jour le devoir
				}else {
					HomeWork temp = day.getWork(idDevoir);
					temp.setContainDoc(documentsAFaire);
					temp.setAFaire(effectue);
					
				}
			}
				
		}
	}
	
	public static void workData(String content, Student student, Date day) throws CodeException, idException {
		//On r�cup�re les donn�es
		String data = getData(content, student);
		
		data = data.substring(28);
		
		String[] workData = data.split("},\\{entityCode:");
		
		for(String w : workData) {
			Matiere mat = Matiere.getMatiere(w.substring(w.indexOf(" codeMatiere:")+13, w.indexOf(", ", w.indexOf(" codeMatiere:"))));
			String id = w.substring(w.indexOf(" id:")+4, w.indexOf(", ", w.indexOf(" id:")));
			
			if(day.contain(id)) {
				HomeWork work = day.getWork(id);
				String aFaire = w.substring(w.indexOf(" aFaire:{"), w.indexOf(", contenuDeSeance:"));
				work.setWork(new String(Base64.getDecoder().decode(aFaire.substring(aFaire.indexOf(" contenu:")+9, aFaire.indexOf(", ", aFaire.indexOf(" contenu:"))))));
				
			}else {
				HomeWork work = new HomeWork(mat, w, false, false, false);
				day.addHomeWork(work);
			}
			
			 
		}
		
	}

}
