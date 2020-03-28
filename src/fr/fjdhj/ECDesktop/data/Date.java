package fr.fjdhj.ECDesktop.data;

import java.util.ArrayList;
import java.util.List;

import fr.fjdhj.ECDesktop.data.exception.idException;

public class Date{
	
	private int year;
	private int month;
	private int day;
	
	private List<HomeWork> homeWork = new ArrayList<HomeWork>();

	public Date(int year, int month, int day) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
				
	}
	
	/**
	 * 
	 * @param idDevoir
	 * @return
	 */
	public boolean contain(String idDevoir){
		for(HomeWork work : homeWork) {
			if(work.getIdDevoir().equals(idDevoir))
				return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param idDevoir
	 * @return
	 * @throws idException
	 */
	public HomeWork getWork(String idDevoir) throws idException {
		for(HomeWork work : homeWork) {
			if(work.getIdDevoir().equals(idDevoir))
				return work;
		}
		
		throw new idException("L'id de type idDevoir : "+idDevoir+" est introuvable");
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String formatDate(int year, int month, int day) {
		String y = new Integer(year).toString();
		String m;
		String d;
		if(month==1) {m = "01";}
		else if(month==2) {m = "02";}
		else if(month==3) {m = "03";}
		else if(month==4) {m = "04";}
		else if(month==5) {m = "05";}
		else if(month==6) {m = "06";}
		else if(month==7) {m = "07";}
		else if(month==8) {m = "08";}
		else if(month==9) {m = "09";}
		else {m = new Integer(month).toString();}
		
		if(day==1) {d = "01";}
		else if(day==2) {d = "02";}
		else if(day==3) {d = "03";}
		else if(day==4) {d = "04";}
		else if(day==5) {d = "05";}
		else if(day==6) {d = "06";}
		else if(day==7) {d = "07";}
		else if(day==8) {d = "08";}
		else if(day==9) {d = "09";}
		else {d = new Integer(day).toString();}
		
		return(y+"-"+m+"-"+d);
	}

	public int getYear() {return year;}
	public void setYear(int year) {this.year = year;}

	public int getMonth() {return month;}
	public void setMonth(int month) {this.month = month;}

	public int getDay() {return day;}
	public void setDay(int day) {this.day = day;}

	public List<HomeWork> getHomeWork() {return homeWork;}
	public void setHomeWork(List<HomeWork> homeWork) {this.homeWork = homeWork;}
	public void addHomeWork(HomeWork work) {homeWork.add(work);}
	
	

}
