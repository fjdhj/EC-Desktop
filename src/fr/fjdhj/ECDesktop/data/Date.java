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
	
	public boolean contain(String idDevoir){
		for(HomeWork work : homeWork) {
			if(work.getIdDevoir().equals(idDevoir))
				return true;
		}
		
		return false;
	}
	
	public HomeWork getWork(String idDevoir) throws idException {
		for(HomeWork work : homeWork) {
			if(work.getIdDevoir().equals(idDevoir))
				return work;
		}
		
		throw new idException("L'id de type idDevoir : "+idDevoir+" est introuvable");
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
