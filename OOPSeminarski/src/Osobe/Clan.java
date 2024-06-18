
package Osobe;


import Kartica.DnevnaKartica;
import Kartica.MesecnaKartica;
import Trening.Trening;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Clan extends Osoba {
    private int idClan;
    private double stanjeNaRacunu;
    private Trener trener;
    private Trening trening;
    

    public Clan(String ime, String prezime,int idClan, double stanjeNaRacunu, Trener trener, Trening trening) {
        super(ime, prezime);
        this.idClan = idClan;
        this.stanjeNaRacunu = stanjeNaRacunu;
        this.trener = trener;
        this.trening = trening;
    }
    

    @Override
    public String toString() {
        return "Ime: "+this.ime+", Prezime: "+this.prezime + ", id clana: " + idClan + ", stanje na racunu:" + stanjeNaRacunu +", Trener:"+ trener +", Trening:"+trening ; //+KARTICA?
    }
    

    public int getIdClan() {
        return idClan;
    }

    public void setIdClan(int idClan) {
        this.idClan = idClan;
    }

    public double getStanjeNaRacunu() {
        return stanjeNaRacunu;
    }

    public void setStanjeNaRacunu(double stanjeNaRacunu) {
        this.stanjeNaRacunu = stanjeNaRacunu;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }
    
    

    //vraca gotovog clana
    public static void novClanUpis(String imeDatoteke, ArrayList<Clan> clanovi){
        
        Gson gson = new Gson();
        try{
            FileWriter fw = new FileWriter(imeDatoteke);
            BufferedWriter bw = new BufferedWriter(fw);
            //ako stavim da prelazi u novi red onda nema EOF [] i nece da ih cita kao objekte
            /*for(Clan c:clanovi){
                bw.write(gson.toJson(c)+"\n");
            }*/
            bw.write(gson.toJson(clanovi));
            
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println("Greska prilikom upisa clana");
        }
    }


    public static ArrayList<Clan> citajClanove(String imeDatoteke) {
        Gson gson = new Gson();
        ArrayList<Clan> listaClanova = new ArrayList<>();
        String izlaz="";
        try {
            FileReader fr=new FileReader(imeDatoteke);
            BufferedReader br=new BufferedReader(fr);
            String red="";
            while ((red=br.readLine())!=null) {                
                izlaz+=red;
            }
            listaClanova = gson.fromJson(izlaz, new TypeToken<ArrayList<Clan>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fajl za upis nije pronadjen");
        } catch (IOException ex) {
            System.out.println("Greska prilikom citanja clanova");
        }
        return listaClanova;
    }
    
    public static void brisiClana(int idClanaZaBrisanje, ArrayList<Clan> listaClanova){
        Gson gson = new Gson();
        Iterator<Clan> iter = listaClanova.iterator();
        boolean exist_member = false;//predpostavka da clan postoji
        while(iter.hasNext()){
            Clan c = iter.next();
            
            if(c.getIdClan()==idClanaZaBrisanje){
                exist_member=true;
                iter.remove();

            }
        }
        if(exist_member==true){
            System.out.println("Clan sa ID "+idClanaZaBrisanje +" je uspesno obrisan!");
        }
        else {
            System.out.println("Clan sa ID "+idClanaZaBrisanje +" se ne nalazi na spisku clanova (ne postoji)!");

        }

        try{
            FileWriter fw = new FileWriter("clanovi.json");
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(gson.toJson(listaClanova));
            
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println("Greska prilikom upisa clana");
        }
        
    }
    
    public static void dopuniKredit(double dodatanKredit,int izabranClanZaDodatak,ArrayList<Clan> listaClanova){
        Gson gson = new Gson();
        Iterator<Clan> iter = listaClanova.iterator();
        boolean valid_member = false;
        Clan pom = null;
        while(iter.hasNext()){
            Clan c = iter.next();
            //setujemo novo stanje na racunu
            if(c.getIdClan()==izabranClanZaDodatak){
                 pom = c;
                double kredit = c.getStanjeNaRacunu();
                kredit+=dodatanKredit;
                valid_member=true;
                c.setStanjeNaRacunu(kredit);
            }
        }
        if(valid_member==true){
            System.out.println("Clanu : "+ pom.toString() + "kredit uspesno dopunjen!");
          //  System.out.println("Kredit uspesno dopunjen!");
        }
        else {
            System.out.println("Clan sa ID-ijem ne postoji "+izabranClanZaDodatak);
        }
        
        //ponovni upis u datoteku
        try{
            FileWriter fw = new FileWriter("clanovi.json");
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(gson.toJson(listaClanova));
            
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println("Greska prilikom upisa clana");
        }
        
        
    }
    public static boolean proveraDaLiPostojiID(int izabranClanZaDodatak,ArrayList<Clan> listaClanova){
        int postoji=0;
        for(Clan c:listaClanova){
            int id = c.getIdClan();
            if(id==izabranClanZaDodatak)
                postoji=1;
        }

        if(postoji==1){
            return true;
        }
        else
            return false;
    }
    
    //ovde dodati i objekat za trening
    //brise trening ostalim clanovima
    //uneti sam datum
    public static void dnevniTrening(int izborClana,ArrayList<Clan> clanovi){
        
        LocalDate datumIzdavanja = LocalDate.now();
        DnevnaKartica dnevnaKartica = new DnevnaKartica("dnevna kartica",300);
        Trening dnevniTrening = new Trening(dnevnaKartica,datumIzdavanja);
        System.out.println("\nCena dnevne karte je : "+dnevniTrening.getDnevnaKartica().getCenaKartice());
        boolean valid_member = false;
        boolean valid_value = false;
        for(Clan c:clanovi){
            //int=c.getIdClan();
            if(c.getIdClan()==izborClana){
                valid_member=true;
                double stanjeNaRacunu = c.getStanjeNaRacunu();
                double cenaKartice = dnevnaKartica.getCenaKartice();
                if(stanjeNaRacunu-cenaKartice>=0){
                    valid_value=true;
                    double novoStanje = dnevnaKartica.kupovinaKartice(stanjeNaRacunu, cenaKartice);
                    c.setStanjeNaRacunu(novoStanje);
                    c.setTrening(dnevniTrening);


                    
                }

            }
        }
            if(valid_member==true && valid_value==true ){
                System.out.println("Uspesno uplacen trening! ");
            }else if (valid_member==false){
                System.out.println("Nije validan clan (ne postoji u bazi!) ");


            } else if (valid_member==true && valid_value==false) {
                System.out.println("Nemate dovoljno kredita!");


            }

        try{
            Gson gson = new Gson();
            FileWriter fw = new FileWriter("clanovi.json");
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(gson.toJson(clanovi));
            
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void mesecniTrening(int izborClana,ArrayList<Clan> clanovi){
        
        LocalDate datumIzdavanja = LocalDate.now();
        MesecnaKartica mesecnaKartica = new MesecnaKartica("mesecna kartica",1000);
        Trening mesecnaClanarina = new Trening(mesecnaKartica,datumIzdavanja);
        System.out.println("\nCena mesecne karte je : "+mesecnaClanarina.getMesecnaKartica().getCenaKartice());
        boolean valid_member = false;
        boolean valid_value = false; // ima iznos?
        for(Clan c:clanovi){
            //int=c.getIdClan();
            if(c.getIdClan()==izborClana){
                valid_member=true;
                double stanjeNaRacunu = c.getStanjeNaRacunu();
                double cenaKartice = mesecnaKartica.getCenaKartice();
                if(stanjeNaRacunu-cenaKartice>=0){
                    valid_value=true;
                    double novoStanje = mesecnaKartica.kupovinaKartice(stanjeNaRacunu, cenaKartice);
                    c.setStanjeNaRacunu(novoStanje);
                    c.setTrening(mesecnaClanarina);

                    
                }

            }
        }
        if(valid_member==true && valid_value==true ){
            System.out.println("Uspesno uplacen trening! ");
        }else if (valid_member==false){
            System.out.println("Nije validan clan (ne postoji u bazi!) ");


        } else if (valid_member==true && valid_value==false) {
            System.out.println("Nemate dovoljno kredita!");


        }

        try{
            Gson gson = new Gson();
            FileWriter fw = new FileWriter("clanovi.json");
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(gson.toJson(clanovi));
            
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void proveraUslovaZaTrening(int izborClana, ArrayList<Clan> listaClanova ){
        ArrayList<Clan> listaClan = listaClanova;
        LocalDate danasnjiDatum = LocalDate.now();
        
        for(Clan c:listaClan){
            if(c.getIdClan()==izborClana){
                if(c.getTrening()==null){
                    System.out.println("Korisnik nema karticu za trening!");
                    break;
                }
                boolean obavestenje = false;
                if(c.getTrening().getDnevnaKartica()!=null){
                    if(c.getTrening().getDatumIzdavanja().equals(danasnjiDatum))

                        obavestenje=true;
                    //ima dnevnu
                    
                }
                if(obavestenje){
                    System.out.println("Korisnik ima uslova za trening! Dnevna kartica vazi danas.");
                }else {
                    System.out.println("Korisnik nema uslove za trening! Dnevna kartica vazi danas.");
                }
                if(c.getTrening().getMesecnaKartica()!=null){
                    
                    //ima mesecnu
                    if(c.getTrening().getDatumIzdavanja().getDayOfMonth()-danasnjiDatum.getDayOfMonth()==0){
                        System.out.println("Mesecna kartica je vazeca! Korisnik ima uslova za trening!");
                    }else {
                        System.out.println("Mesecna kartica nije vazeca! Korisnik nema uslova za trening!");
                    }
                }
            }
        }
        
    }
    
}
