package fr.fjdhj.ECDesktop.data;

public enum Matiere {
	
	FRANCAIS            ("Français", "FRANC"),
	MATHEMATIQUES       ("Mathematiques", "MATHS"),
	HISTOIRE_GEOGRAPHIE ("Histoire Géographie", "HI-GE"),
	SCIENCE_VIE_TERRE   ("Science et vie de la terre", "SVT"),
	PHYSIQUE_CHIMIE     ("Physique Chimie", "PH-CH"),
	ANGLAIS_LV1         ("Anglais LV1", "AGL1"),
	ANGLAIS_LV2         ("Anglais LV2", "AGL2"),
	UNKNOW              ("","");
	
	private final String nom;
	private final String code;

	Matiere(String nom, String code) {
		this.nom = nom;
		this.code = code;
	}

	public static Matiere getMatiere(String code) {
		switch(code) {
		case "FRANC":
			return Matiere.FRANCAIS;
		case "MATHS":
			return Matiere.MATHEMATIQUES;
		case "HI-GE":
			return Matiere.HISTOIRE_GEOGRAPHIE;
		case "SVT":
			return Matiere.SCIENCE_VIE_TERRE;
		case "PH-CH":
			return Matiere.PHYSIQUE_CHIMIE;
		case "AGL1":
			return Matiere.ANGLAIS_LV1;
		case "AGL2":
			return Matiere.ANGLAIS_LV2;
		default:
			return null;

			
			
		}
	}
	
	public String getNom() {
		return nom;
	}

	public String getCode() {
		return code;
	}

}
