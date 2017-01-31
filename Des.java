package Monopoly;

import java.util.ArrayList;

public class Des{
    public static ArrayList<Joueur> tirageAuSort(ArrayList<Joueur> joueurs){
        ArrayList<Joueur> finalJoueurs = new ArrayList<Joueur>();
        int initialSize = joueurs.size();
        while(finalJoueurs.size()<initialSize){
            int toFinalJoueurs = aleatoirNumber(0, joueurs.size()-1);
            finalJoueurs.add(joueurs.get( toFinalJoueurs ));
            joueurs.remove( joueurs.get( toFinalJoueurs ) );
        }
        return finalJoueurs;
    }
    
    
    public static int aleatoirNumber(int minimum, int maximum){
        double min = Math.ceil(minimum);
        double max = Math.floor(maximum);
        double res = Math.floor(Math.random() * (max - min +1)) + min;
        return (int) res;
    }
    
    
    public static int lancerDes(){
        int de1;
        int de2;
        int res = 0;
        de1 = aleatoirNumber(1,6);
        de2 = aleatoirNumber(1,6);
        res += de1 + de2;
        if(de1==de2){
        	res = -res;
            Window.addConsoleText("Vous avez fait Un double");
        }
        Window.addConsoleText("Dé1 = "+de1+"          Dé2 = "+de2);
        return (int) res;
    }
}
