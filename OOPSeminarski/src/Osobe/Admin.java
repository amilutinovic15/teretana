
package Osobe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {
    private String korisnickoIme;
    private String lozinka;

    public Admin(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    @Override
    public String toString() {
        return "Admin{" + "korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + '}';
    }
    
    public boolean proveri(String korisnickoIme,String lozinka){
        return this.korisnickoIme.equals(korisnickoIme) && this.lozinka.equals(lozinka);
    }
    
    public static ArrayList<Admin> citanjeAdmina(String imeDatoteke){
        Gson gson = new Gson();
        ArrayList<Admin> admini = new ArrayList<>();
        String ispis="";
        try{
            FileReader fr = new FileReader(imeDatoteke);
            BufferedReader br = new BufferedReader(fr);
            String tmp="";
            while((tmp=br.readLine())!=null){
                ispis+=tmp;
            }
            
            admini = gson.fromJson(ispis, new TypeToken<ArrayList<Admin>>(){}.getType());
            
            br.close();
            fr.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admini;
    }
    
    public static void upisAdmina(String fileName, ArrayList<Admin> admini){
        Gson gson = new Gson();

        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(gson.toJson(admini));

            bw.close();
            fw.close();
        }
           catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }


            
            

    }
}
