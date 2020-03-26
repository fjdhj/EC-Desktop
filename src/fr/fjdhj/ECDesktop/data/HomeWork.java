package fr.fjdhj.ECDesktop.data;

public class HomeWork {
	
	private Matiere matiere;
	private String idDevoir;
	private boolean containDoc;
	private boolean effectue;

	/**
	 * 
	 * @param matiere
	 * @param idDevoir
	 * @param containDoc
	 * @param aFaire
	 */
	public HomeWork(Matiere matiere, String idDevoir, boolean containDoc, boolean aFaire) {
		this.matiere = matiere;
		this.idDevoir = idDevoir;
		this.containDoc = containDoc;
		this.effectue = aFaire;
	}

	public Matiere getMatiere() {return matiere;}
	public void setMatiere(Matiere matiere) {this.matiere = matiere;}

	public String getIdDevoir() {return idDevoir;}
	public void setIdDevoir(String idDevoir) {this.idDevoir = idDevoir;}

	public boolean isContainDoc() {return containDoc;}
	public void setContainDoc(boolean containDoc) {this.containDoc = containDoc;}

	public boolean isAFaire() {return effectue;}
	public void setAFaire(boolean aFaire) {this.effectue = aFaire;}

}
