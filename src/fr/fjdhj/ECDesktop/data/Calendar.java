package fr.fjdhj.ECDesktop.data;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
	
	private	List<Date> date = new ArrayList<Date>();
	
	public Calendar() {}
	
	/**
	 * Permet de récuperer la date voulu, si elle n'existe pas, elle est créée
	 * La date fournie doit être de la forme YYYY-MM-DD
	 * @param date 
	 * @return La date voulu
	 */
	public Date get(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		//On regarde si la date existes
		for(Date d : this.date) {
			if(d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
				return d;
			}
		}
		//Si elle n'existe pas on la créer
		System.out.println("Creation de la date : " + date);
		Date d = new Date(year, month, day);
		this.date.add(d);
		return d;
	}

	public List<Date> getDate() {return date;}
	public void setDate(List<Date> date) {this.date = date;}

}
