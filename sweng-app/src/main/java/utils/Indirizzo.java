package utils;

public final class Indirizzo {
	private final String nazione;
	private final String provincia;
	private final String comune;
	private final String cap;
	private final String via;
	private final String civico;
	private final String scala;
	private final String interno;
	
	public Indirizzo(
			String n, 
			String p, 
			String co, 
			String ca, 
			String v, 
			String ci,
			String s,
			String i) {
		nazione   = n;
		provincia = p;
		comune    = co;
		cap       = ca;
		via   	  = v;
		civico	  = ci;
		scala 	  = s;
		interno   = i;
	}

	public String getNazione() {
		return nazione;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getComune() {
		return comune;
	}

	public String getCap() {
		return cap;
	}

	public String getVia() {
		return via;
	}

	public String getCivico() {
		return civico;
	}

	public String getScala() {
		return scala;
	}

	public String getInterno() {
		return interno;
	}
}
