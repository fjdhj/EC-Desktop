package fr.fjdhj.ECDesktop.data;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
	
	private	List<Date> date = new ArrayList<Date>();
	
	public Calendar() {}
	
	/**
	 * Permet de récuperer la date voulu, si elle n'existe pas, elle est créée
	 * La date fournie doit être de la forme YYYY-MM-DD
	 * Elle peut être créer automatiquement si creat et sur true
	 * @param date 
	 * @param creat 
	 * @return La date voulu
	 */
	public Date get(String date, boolean creat) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		//On regarde si la date existes
		for(Date d : this.date) {
			if(d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
				return d;
			}
		}
		//Si elle n'existe pas on la créer si creat=true
		if(creat) {
			System.out.println("Creation de la date : " + date);
			Date d = new Date(year, month, day);
			this.date.add(d);
			return d;
		}
		return null;
		
	}
	
	
	/**
	 * 1 : Janvier; 12: Decembre
	 * @param month
	 * @return
	 */
	public static String nameOfMonth(int month) {
		if(month==1) {return "Janvier";}
		if(month==2) {return "Fevrier";}
		if(month==3) {return "Mars";}
		if(month==4) {return "Avril";}
		if(month==5) {return "Mai";}
		if(month==6) {return "Juin";}
		if(month==7) {return "Juillet";}
		if(month==8) {return "Aout";}
		if(month==9) {return "Septembre";}
		if(month==10) {return "Octobre";}
		if(month==11) {return "Novembre";}
		if(month==12) {return "Decembre";}
		return null;
		
	}
	
	public static boolean isBisextile(int year) {
		if(year%4==0) {
			if(year%100==0) {
				if(year%400==0) {
					return true;
				}
			}else {
				return true;
			}
		}
		return false;
	}
	
	public static int numberOfDayInMonth(int month, int year) {
		//Fevrier
		if(month==2) {
			if(isBisextile(year))
				return 29;
			else
				return 28;
		}else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12||month==0) {
			return 31;
		}else {
			return 30;
		}
	}

	public List<Date> getDate() {return date;}
	public void setDate(List<Date> date) {this.date = date;}

}
