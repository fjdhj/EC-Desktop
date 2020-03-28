package fr.fjdhj.ECDesktop.exception;

public class idException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6397423693795887147L;

	/**
	 * 
	 * @param message L'id de type TYPEID : NUMEROID est introuvable
	 */
	public idException(String message) {
		super(message);
		
	}

}