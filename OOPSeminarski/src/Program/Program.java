
package Program;



import Izuzeci.KorisnickoImeZauzeto;
import Osobe.Admin;
import Osobe.Clan;
import Osobe.Trener;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Program {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        Gson gson = new Gson();
        Random r = new Random();
        System.out.println("-----Dobrodosli u teretanu-----");
            int opcija=0;
        
            boolean again =true;            
            int izlaz=0;
            
            System.out.println("Unesite opciju kao broj");
            
            while(izlaz==0){
                
                System.out.println("1.Logovanje admin");
                System.out.println("2.Dodaj admina");
                System.out.println("3.Uputstvo za koriscenje programa");
                System.out.println("4.Izlaz");
                //provera da li je broj
                while(again){
                    try{
                        System.out.println("Unesite vasu opciju:");
                        opcija = s.nextInt();
                        again=false;
                    }catch(Exception ex){
                        System.out.println("Niste uneli broj!");
                        s.next();
                    }
                }
                again=true;
                
                switch(opcija){
                    case 1:
                        System.out.println("1.Logovanje admin");
                        System.out.println("Izabrali ste admin logovanje.");
                        
                        System.out.println("Unesite korisnicko ime:");
                        s.nextLine();
                        String korisnickoIme = s.nextLine();
                        
                        System.out.println("Unesite lozinku:");
                        String lozinka = s.nextLine();
                        //LOGOVANJE ADMINA
                        ArrayList<Admin> admini = new ArrayList<>();
                        admini = Admin.citanjeAdmina("admini.json");
                        
                        int nadjen=0;
                        for(Admin a:admini){
                            if(a.proveri(korisnickoIme, lozinka)){
                                System.out.println("Dobrodosli "+korisnickoIme);
                                nadjen++;
                            }
                        }
                        if(nadjen==0){
                            System.out.println("Niste uneli odgovarajuce korisnicko ime ili lozinku. Pokusajte ponovo.");
                        }
                        if(nadjen>0){
                            System.out.println("-----Dobrodosli u glavni meni-----");
                            int drugiIzlaz=0;
                            int opcija2=0;
                            while(drugiIzlaz==0){
                                System.out.println("Izaberite opcije:");
                                System.out.println("1.Clanovi");
                                System.out.println("2.Trening (mesecne kartice)");
                                System.out.println("3.Cenovnik");
                                System.out.println("4.Proizvodi");
                                System.out.println("5.Log out");
                                while(again){
                                    try{
                                        System.out.println("Unesite vasu opciju:");
                                        opcija2 = s.nextInt();
                                        again=false;
                                    }catch(Exception ex){
                                        System.out.println("Niste uneli broj!");
                                        s.next();
                                    }
                                }
                                again=true;
                                
                                switch(opcija2){
                                    case 1:
                                        int treciIzlaz=0;
                                        System.out.println("Clanovi:");
                                        int opcija3=0;
                                        while(treciIzlaz==0){
                                            System.out.println("1.Spisak clanova");
                                            System.out.println("2.Dodaj clana");
                                            System.out.println("3.Obrisi clana");
                                            System.out.println("4.Dopuni kredit");
                                            System.out.println("5.Izlaz");
                                            while(again){
                                                try{
                                                    System.out.println("Unesite vasu opciju:");
                                                    opcija3 = s.nextInt();
                                                    again=false;
                                                }catch(Exception ex){
                                                    System.out.println("Niste uneli broj!");
                                                    s.next();
                                                }
                                            }
                                            again=true;
                                            switch(opcija3){
                                                case 1:
                                                    System.out.println("1.Spisak clanova i trenera");
                                                    ArrayList<Clan> listaClanova = new ArrayList<>();
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    for(Clan c:listaClanova){
                                                        System.out.println(c);
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.println("2.Dodaj clana");
                                                    //procitas sve trenere i ponnudis mu
                                                    //pitas dalje za ime prezime, kredit koji uplacuje
                                                    //vrstu kartice
                                                    //id random
                                                    ArrayList<Trener> treneri = Trener.citaj("treneri.json");
                                                    //ispis trenera
                                                    for(Trener t:treneri){
                                                        System.out.println(t);
                                                    }
                                                    System.out.println("Ukucajte broj id-a trenera koji zelite da vas trenira(0 ako ne zelite trenera): 1 -"+treneri.size());
                                                    int izborTrenera=0;
                                                    while(again){
                                                        try{
                                                            izborTrenera = s.nextInt();
                                                            
                                                            for(Trener t:treneri){
                                                                if(t.getIdTrener()==izborTrenera ){
                                                                    again=false;
                                                                    System.out.println("Izabrali ste trenera");
                                                                }
                                                            }
                                                            if(izborTrenera==0)
                                                            {
                                                                System.out.println("Ne zelite trenera");
                                                                break;
                                                            }
                                                            if(again==true)
                                                                System.out.println("Izabrani trener ne postoji");
                                                            
                                                            
                                                        }catch(Exception ex){
                                                            System.out.println("Niste uneli broj!");
                                                            s.next();
                                                        }
                                                    }
                                                    again=true;
                                                    //nadjem trenera koji je izabran i napravim objekat te klase
                                                    
                                                    Trener izabraniTrener = new Trener(null,null,0);
                                                    
                                                    for(Trener t:treneri){
                                                        if(t.getIdTrener()==izborTrenera){
                                                            izabraniTrener = new Trener(t.getIme(),t.getPrezime(),t.getIdTrener());
                                                        }
                                                    }
                                                    //trebam da nadovezem clana na txt listu
                                                    //sta ako ima 2 ista clana
                                                    //scanner nije hteo da radi u klasi pa sam u mainu odradio
                                                    System.out.println("Unesite ime novog clana:");
                                                    s.nextLine();
                                                    String imeClana = s.nextLine();
                                                    System.out.println("Unesite prezime novog clana:");
                                                    String prezimeClana = s.nextLine();
                                                    //ako ima vec id nadji drugi
                                                    //sledeca logika ucitati listu clanova uzeti poslednjeg clana i njegov id povecati za 1
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    
                                                    int idClana=(listaClanova.get(listaClanova.size()-1).getIdClan()+1);
                                                    System.out.println("Koliko zelite da uplatite na racun?");
                                                    double stanjeNaRacunu=0;
                                                    try{

                                                         stanjeNaRacunu = s.nextDouble();
                                                    }
                                                    catch (Exception e){
                                                        System.out.println("Greska setovano stanje je 0");
                                                    }

                                                    
                                                    //pravljenje novog clana


                                                    //listaClanova = new ArrayList<>();
                                                    
                                                    if(izborTrenera>0){
                                                        Clan noviClan = new Clan(imeClana,prezimeClana,idClana,stanjeNaRacunu,izabraniTrener,null);
                                                        listaClanova.add(noviClan);
                                                        Clan.novClanUpis("clanovi.json", listaClanova);
                                                        System.out.println("Novi clan je dodat!");
                                                    }
                                                    else{
                                                        Clan noviClan = new Clan(imeClana,prezimeClana,idClana,stanjeNaRacunu,null,null);
                                                        listaClanova.add(noviClan);
                                                        Clan.novClanUpis("clanovi.json", listaClanova);
                                                        System.out.println("Novi clan je dodat!");
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("3.Obrisi clana");
                                                    System.out.println("Izaberite id clana za brisanje");
                                                    //citanje liste clanova
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    for(Clan c:listaClanova){
                                                        System.out.println(c);
                                                    }
                                                    int idClanaZaBrisanje = s.nextInt();
                                                    Clan.brisiClana(idClanaZaBrisanje,listaClanova);
                                                  //  System.out.println("Clan je obrisan");//greska na primer unesemo neku vrednost
                                                    break;
                                                case 4:
                                                    System.out.println("4.Dopuni kredit");
                                                    System.out.println("Izaberite clana kome zelite dopuniti kredit (njegov id):");
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    System.out.println("\nSpisak clanova:\n");
                                                    for(Clan c:listaClanova){

                                                        System.out.println(c);
                                                    }
                                                    System.out.println("Unesite id clana: ");
                                                    //PROVERA DA LI SMO UNELI BROJ
                                                    int izabranClanZaDodatak=0 ;
                                                    while(again){
                                                        try{
                                                            izabranClanZaDodatak = s.nextInt();
                                                            again=false;
                                                        }catch(Exception ex){
                                                            System.out.println("Niste uneli broj!");
                                                            s.next();
                                                        }
                                                    }
                                                    again=true;
                                                    
                                                    //provera da li postoji clan/////////
                                                    if(Clan.proveraDaLiPostojiID(izabranClanZaDodatak,listaClanova)){
                                                        //provera da je uneta cifra
                                                        System.out.println("Unesite cifru:");
                                                        double dodatanKredit = 0;
                                                        while(again){
                                                            try{
                                                                dodatanKredit = s.nextDouble();
                                                                again=false;
                                                            }catch(Exception ex){
                                                                System.out.println("Niste uneli cifru! Pokusajte ponovo.");
                                                                s.next();
                                                            }
                                                        }
                                                        again=true;
                                                        
                                                        Clan.dopuniKredit(dodatanKredit,izabranClanZaDodatak,listaClanova);

                                                    }
                                                    else
                                                        System.out.println("Clan ne postoji");
                                                    
                                                    
                                                    break;
                                                case 5:
                                                    treciIzlaz++;
                                                    break;
                                                default:
                                                    System.out.println("Niste uneli ponudjen broj");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Izaberite trening");
                                        int cetvrtiIzlaz=0;
                                        int opcija4=0;
                                        while(cetvrtiIzlaz==0){
                                            //pravim objekat dnevni tr +citam sve clanove i upisujem u listu gde vadim
                                            //jedan po jedan i dodajem im trening tj pravim novi objekat sa clan sa treningom i
                                            //upisujem u posebnu datoteku mozda(??) + oduzimam novcanostanje od cene treninga
                                            System.out.println("1.Trening(proverava da li ima uslova za trening)");
                                            System.out.println("2.Uplati dnevni trening");
                                            System.out.println("3.Uplati mesecnu karticu");
                                            System.out.println("4.Nazad");
                                            while(again){
                                                try{
                                                    System.out.println("Unesite vasu opciju:");
                                                    opcija4 = s.nextInt();
                                                    again=false;
                                                }catch(Exception ex){
                                                    System.out.println("Niste uneli broj!");
                                                    s.next();
                                                }
                                            }
                                            again=true;
                                            switch(opcija4){
                                                case 1:
                                                    System.out.println("provera da li ima uslova za trening");
                                                    System.out.println("Izaberite id clana za trening:");
                                                    ArrayList<Clan> listaClanova = new ArrayList<>();
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    for(Clan c:listaClanova){
                                                        System.out.println(c);
                                                    }
                                                    //PROVERA DA LI SMO UNELI BROJ
                                                    int izabranClanZaTrening=0 ;
                                                    while(again){
                                                        try{
                                                            izabranClanZaTrening = s.nextInt();
                                                            again=false;
                                                        }catch(Exception ex){
                                                            System.out.println("Niste uneli broj!");
                                                            s.next();
                                                        }
                                                    }
                                                    again=true;
                                                    //provera da li korisniku vazi dnevna i mesecna kartica
                                                    if(Clan.proveraDaLiPostojiID(izabranClanZaTrening,listaClanova)==true){
                                                        //treba da citam listu clanova da bih video da li je id dobar
                                                        //pozivamo staticku metodu za upis dnevnog treninga
                                                        Clan.proveraUslovaZaTrening(izabranClanZaTrening, listaClanova);
                                                    }
                                                    else{
                                                        System.out.println("Clan ne postoji");
                                                        break;
                                                    }
                                                    
                                                    cetvrtiIzlaz++;
                                                    break;
                                                case 2:
                                                    System.out.println("Izaberite id clana za dnevni trening:");
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    for(Clan c:listaClanova){
                                                        System.out.println(c);
                                                    }
                                                    //PROVERA DA LI SMO UNELI BROJ
                                                    int izabranClanZaDnevniTrening=0 ;
                                                    while(again){
                                                        try{
                                                            izabranClanZaDnevniTrening = s.nextInt();
                                                            again=false;
                                                        }catch(Exception ex){
                                                            System.out.println("Niste uneli broj!");
                                                            s.next();
                                                        }
                                                    }
                                                    again=true;
                                                    //provera da li postoji clan/////////
                                                    if(Clan.proveraDaLiPostojiID(izabranClanZaDnevniTrening,listaClanova)==true){
                                                        //treba da citam listu clanova da bih video da li je id dobar
                                                        //pozivamo staticku metodu za upis dnevnog treninga
                                                        Clan.dnevniTrening(izabranClanZaDnevniTrening, listaClanova);
                                                      //  System.out.println("Korisniku je upisan dnevni trening.");
                                                        
                                                    }
                                                    else{
                                                        System.out.println("Clan ne postoji");
                                                        break;
                                                    }
                                                    
                                                    cetvrtiIzlaz++;
                                                    break;
                                                case 3:
                                                    System.out.println("Izaberite id clana za izdavanje mesecne karte:");
                                                    listaClanova = Clan.citajClanove("clanovi.json");
                                                    for(Clan c:listaClanova){
                                                        System.out.println(c);
                                                    }
                                                    //PROVERA DA LI SMO UNELI BROJ
                                                    int izabranClanZaMesecniTrening=0 ;
                                                    while(again){
                                                        try{
                                                            izabranClanZaMesecniTrening = s.nextInt();
                                                            again=false;
                                                        }catch(Exception ex){
                                                            System.out.println("Niste uneli broj!");
                                                            s.next();
                                                        }
                                                    }
                                                    again=true;
                                                    //provera da li postoji clan/////////
                                                    if(Clan.proveraDaLiPostojiID(izabranClanZaMesecniTrening,listaClanova)==true){
                                                        //treba da citam listu clanova da bih video da li je id dobar
                                                        //pozivamo staticku metodu za upis dnevnog treninga
                                                        Clan.mesecniTrening(izabranClanZaMesecniTrening, listaClanova);
                                                        //System.out.println("Korisniku je upisan mesecni trening.");
                                                    }
                                                    else{
                                                        System.out.println("Clan ne postoji");
                                                        break;
                                                    }
                                                    cetvrtiIzlaz++;
                                                    break;
                                                case 4:
                                                    cetvrtiIzlaz++;
                                                    break;
                                                    default:

                                                    System.out.println("Niste uneli ponudjenu opciju");
                                                    break;
                                            }
                                            
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Cenovnik");
                                        System.out.println("\nDnevna karta - 300rsd");
                                        System.out.println("\nMesecna karta - 1000rsd");
                                        break;
                                    case 4:
                                        System.out.println("Proizvodi");
                                        System.out.println("WHEY protein - 1kg -2000rsd");
                                        System.out.println("CREATIN - 1kg - 1500rsd");
                                        System.out.println("Proteinske čokoladice - 1kom - 200rsd\n");
                                        break;
                                    case 5:
                                        drugiIzlaz++;
                                        break;
                                    default:
                                        System.out.println("Niste uneli ponudjenu opciju");
                                        break;
                                        
                                        
                                }
                                
                            }
                        }
                        break;
                        
                    case 2:
                        System.out.println("Unesite korisnicko ime za novog admina:");
                        s.nextLine();
                        String korisnickoIme2=s.nextLine();
                        System.out.println("Unesite lozinku za novog admina:");
                        String lozinka2=s.nextLine();
                        if(korisnickoIme2.trim().equals("") || lozinka2.trim().equals("")){
                            System.out.println("Korisnicko ime mora da ima znakove u sebi kao i lozinka !");
                        }else {
                            try{
                                int nadjen2=0;
                                ArrayList<Admin> listaAdmina = new ArrayList<>();
                                listaAdmina = Admin.citanjeAdmina("admini.json");

                                for(Admin a:listaAdmina){
                                    if(a.getKorisnickoIme().equals(korisnickoIme2)){
                                        nadjen2=1;
                                        throw new KorisnickoImeZauzeto();
                                    }
                                }
                                //upis admina ako nije pronadjen
                                if(nadjen2==0){
                                    Admin a = new Admin(korisnickoIme2,lozinka2);
                                    listaAdmina.add(a);
                                    System.out.println(a.toString() +" je dodat!");
                                }
                                Admin.upisAdmina("admini.json", listaAdmina);


                            }catch(Exception e){
                                e.printStackTrace();
                            }


                        }

                        
                        break;
                        
                    case 3:
                        System.out.println("3.Uputstvo za koriscenje programa");
                        System.out.println("\nDa bi ste mogli pristupiti aplikaciji, trebate imati administratorski nalog.\n");
                        
                        break;
                    
                    case 4:
                        System.out.println("4.Izlaz");
                        izlaz++;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Niste uneli ponudjen broj!");
                        break;
                      }  
                        
                
                
            }
       
        
        
        
        
        
    }
}
