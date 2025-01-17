
package Osobe;

import java.util.ArrayList;

public abstract class Osoba {
    protected String ime;
    protected String prezime;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return "Osoba{" + "ime=" + ime + ", prezime=" + prezime + '}';
    }
    
    

}
