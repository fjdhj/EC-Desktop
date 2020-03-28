package fr.fjdhj.ECDesktop.data;

import fr.fjdhj.ECDesktop.com.HTTPRequest;
import fr.fjdhj.ECDesktop.exception.CodeException;
import fr.fjdhj.ECDesktop.exception.idException;

public class Student {
	
	private String login;
	private String password;
	
	private String token = "";
	private String idLogin = "";
	private String id = "";
	private String uid = "";
	private String prenom = "";
	private String particule = "";
	private String nom = "";
	private String email = "";
	private String anneeScolaireCourante = "";
	private String nomEtablissement = "";
	private String accessToken = "";
	
	private Calendar cal = new Calendar();
	
	public Student(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Student() {}
	
	/**
	 * Connecte l'élève afin de pouvoir récupèrer les informations
	 * @throws CodeException
	 */
	public void login() throws CodeException{
		//On génère le contenue de notre requetes
		String data = "data={\"identifiant\": \"" + login +"\", \"motdepasse\": \"" + password +"\"}";
		
		//On envoie la requetes de connexion
		String response = HTTPRequest.sendingPost(HTTPRequest.LOGIN_LINK, data, HTTPRequest.LOGIN_CONTENT_TYPE);
		System.out.println(response);
		
		TransformRawFile.loginData(response, this);
		
		//On mets tous a jour
		update();
	}
	
	public void updateCahierDeTexte() throws CodeException{
		//On génère le contenue de notre requetes
		String data = "data={\"token\": \"" + getToken() +"\"}";
		String link = HTTPRequest.MESSAGE_LINK.replace("id", getId());
		//On envoie la requetes
		String response = HTTPRequest.sendingPost(link, data, HTTPRequest.DEFAULT_CONTENT_TYPE);

		try {
			TransformRawFile.cahierDeTextData(response, this);
		} catch (idException e) {
			e.printStackTrace();
		}
		
		for(Date d : cal.getDate()) {
			updateWork(d);
		}
		

	}
	
	public void updateWork(Date date) throws CodeException {
		//On génère le contenue de notre requetes
		String data = "data={\"token\": \"" + getToken() +"\"}";
		String link = HTTPRequest.WORK_LINK.replace("id", getId());
		link = link.replace("date", Date.formatDate(date.getYear(), date.getMonth(), date.getDay()));
		
		//On envoie la requetes
		String response = HTTPRequest.sendingPost(link, data, HTTPRequest.DEFAULT_CONTENT_TYPE);
		try {
			TransformRawFile.workData(response, this, date);
		} catch (idException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Met a jour : le cahier de texte, le contenue des séance
	 * @throws CodeException 
	 */
	public void update() throws CodeException {
		updateCahierDeTexte();
	}
	
	public void printAllInfo(){
		System.out.println("token : "+ getToken());
		System.out.println("idLogin : "+ getIdLogin());
		System.out.println("id : "+ getId());
		System.out.println("uid : "+ getUid());
		System.out.println("Prenom : "+ getPrenom());
		System.out.println("Particule : "+ getParticule());
		System.out.println("Nom : "+ getNom());
		System.out.println("Email : "+ getEmail());
		System.out.println("Annee Scolaire Courant : "+ getAnneeScolaireCourante());
		System.out.println("Nom Etablissement : "+ getNomEtablissement());
		System.out.println("AccessToken : "+ getAccessToken());
	}

	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}

	public void setPassword(String password) {this.password = password;}

	public String getToken() {return token;}
	public void setToken(String token) {this.token = token;}

	public String getIdLogin() {return idLogin;}
	public void setIdLogin(String idLogin) {this.idLogin = idLogin;}

	public String getId() {return id;}
	public void setId(String id) {this.id = id;}

	public String getUid() {return uid;}
	public void setUid(String uid) {this.uid = uid;}

	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}

	public String getParticule() {return particule;}
	public void setParticule(String particule) {this.particule = particule;}

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public String getAnneeScolaireCourante() {return anneeScolaireCourante;}
	public void setAnneeScolaireCourante(String anneeScolaireCourante) {this.anneeScolaireCourante = anneeScolaireCourante;}

	public String getNomEtablissement() {return nomEtablissement;}
	public void setNomEtablissement(String nomEtablissement) {this.nomEtablissement = nomEtablissement;}

	public String getAccessToken() {return accessToken;}
	public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

	public Calendar getCalendar() {return cal;}

	

}
