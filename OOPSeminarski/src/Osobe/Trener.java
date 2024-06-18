
package Osobe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trener extends Osoba{
    private int idTrener;
    
    public Trener(String ime, String prezime, int idTrener) {
        super(ime, prezime);
        this.idTrener=idTrener;
    }

    public Trener(String ime, String prezime) {
        super(ime, prezime);
    }

     
    
    public int getIdTrener() {
        return idTrener;
    }

    @Override
    public String toString() {
        return "{Ime: "+super.ime+", Prezime:"+super.prezime+", ID trenera:"+idTrener+"}";
    }
    
    public static ArrayList<Trener> citaj(String imeDatoteke){
        Gson gson = new Gson();
        ArrayList<Trener> treneri = new ArrayList<>();

                try{
                    FileReader fr = new FileReader(imeDatoteke);
                    BufferedReader br = new BufferedReader(fr);
                    String ispis="";
                    String red="";
                    while((red=br.readLine())!=null){
                        ispis+=red+"\n";
                    }
                    treneri = gson.fromJson(ispis, new TypeToken<ArrayList<Trener>>(){}.getType());
                    
                    br.close();
                    fr.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        return treneri;
    }


    
    
}
