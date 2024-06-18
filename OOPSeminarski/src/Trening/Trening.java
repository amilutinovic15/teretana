
package Trening;

import Kartica.DnevnaKartica;
import Kartica.MesecnaKartica;
import java.time.LocalDate;

public class Trening {
    //dnevni, dnevni sa trenerom
    private DnevnaKartica dnevnaKartica;
    private MesecnaKartica mesecnaKartica;
    private LocalDate datumIzdavanja;

    public Trening(DnevnaKartica dnevnaKartica, MesecnaKartica mesecnaKartica, LocalDate datumIzdavanja) {
        this.dnevnaKartica = dnevnaKartica;
        this.mesecnaKartica = mesecnaKartica;
        this.datumIzdavanja = datumIzdavanja;
    }
    public Trening(DnevnaKartica dnevnaKartica,  LocalDate datumIzdavanja) {
        this.dnevnaKartica = dnevnaKartica;
        this.mesecnaKartica = null;
        this.datumIzdavanja = datumIzdavanja;
    }

    public Trening(MesecnaKartica mesecnaKartica,  LocalDate datumIzdavanja) {
        this.dnevnaKartica = null;
        this.mesecnaKartica = mesecnaKartica;
        this.datumIzdavanja = datumIzdavanja;
    }


    public DnevnaKartica getDnevnaKartica() {
        return dnevnaKartica;
    }

    public void setDnevnaKartica(DnevnaKartica dnevnaKartica) {
        this.dnevnaKartica = dnevnaKartica;
    }

    public MesecnaKartica getMesecnaKartica() {
        return mesecnaKartica;
    }

    public void setMesecnaKartica(MesecnaKartica mesecnaKartica) {
        this.mesecnaKartica = mesecnaKartica;
    }

    public LocalDate getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    @Override
    public String toString() {
        return dnevnaKartica +", " + mesecnaKartica + ", datumIzdavanja=" + datumIzdavanja + '}';
    }

    

    
    
    
    
    
}
