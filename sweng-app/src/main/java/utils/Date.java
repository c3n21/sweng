package utils;

public class Date{
	private int giorno;
	private int mese;
	private int anno;

	public Date(int a, int m, int g) {
		this.giorno = g;
		this.mese = m;
		this.anno = a;
	}

	public Date(){
		this.giorno = 1;
		this.mese = 0;
		this.anno = 1900;
	}

	public int getGiorno(){
		return giorno;
	}

	public int getMese(){
		return mese;
	}

	public int getAnno(){
		return anno;
	}

	public int getTime(){
		return (this.anno - 1900) * 365 + (this.mese * (365/12)) + this.giorno;
	}


}