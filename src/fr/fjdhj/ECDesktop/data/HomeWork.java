package fr.fjdhj.ECDesktop.data;

public class HomeWork {
	
	private Matiere matiere;
	private String idDevoir;
	private boolean containDoc;
	private boolean effectue;
	private boolean isAFaire;
	
	
	private String work = "";
	
	/**
	 * 
	 * @param matiere
	 * @param idDevoir
	 * @param containDoc
	 * @param effectue
	 */
	public HomeWork(Matiere matiere, String idDevoir, boolean containDoc, boolean effectue, boolean isAFaire) {
		this.matiere = matiere;
		this.idDevoir = idDevoir;
		this.containDoc = containDoc;
		this.effectue = effectue;
		this.isAFaire = isAFaire;
	}

	public Matiere getMatiere() {return matiere;}
	public void setMatiere(Matiere matiere) {this.matiere = matiere;}

	public String getIdDevoir() {return idDevoir;}
	public void setIdDevoir(String idDevoir) {this.idDevoir = idDevoir;}

	public boolean isContainDoc() {return containDoc;}
	public void setContainDoc(boolean containDoc) {this.containDoc = containDoc;}

	public boolean isEffectue() {return effectue;}
	public void setEffectue(boolean effectue) {this.effectue = effectue;}

	public String getWork() {return work;}
	public void setWork(String work) {this.work = work;}

	public boolean isAFaire() {return isAFaire;}
	public void setAFaire(boolean isAFaire) {this.isAFaire = isAFaire;}

}
