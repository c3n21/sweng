package utente;

public enum Sesso {
MASCHIO("M"),
FEMMINA("F");

private String s;

private Sesso(String s) {
    this.s = s;
}

public String get() {
    return s;

}}
