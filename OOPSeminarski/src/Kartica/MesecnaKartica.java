
package Kartica;

public class MesecnaKartica implements Kartica {
    private String imeKartice;
    private double cenaKartice;

    public MesecnaKartica(String imeKartice, double cenaKartice) {
        this.imeKartice = imeKartice;
        this.cenaKartice = cenaKartice;
    }

    public String getImeKartice() {
        return imeKartice;
    }

    public void setImeKartice(String imeKartice) {
        this.imeKartice = imeKartice;
    }

    public double getCenaKartice() {
        return cenaKartice;
    }

    public void setCenaKartice(double cenaKartice) {
        this.cenaKartice = cenaKartice;
    }
    
    @Override
    public String toString() {
        return "MesecnaKartica{" + "imeKartice=" + imeKartice + ", cenaKartice=" + cenaKartice + '}';
    }

    @Override
    public double kupovinaKartice(double stanjeNaRacunu, double cenaKartice) {
        double novoStanje;
        novoStanje = stanjeNaRacunu-cenaKartice;
        return novoStanje;
    }
    
}
