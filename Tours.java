package Monopoly;

import java.util.ArrayList;

public class Tours{
    private static ArrayList<Joueur> joueurs;
    private static int indexPlayer;
    
    public static void add(ArrayList<Joueur> joueursNew){
        joueurs = new ArrayList<Joueur>(joueursNew);
    }
    
    public static ArrayList<Joueur> getJoueurs(){
        return joueurs;
    }
    
    public static Joueur getJoueur(){
        return joueurs.get(indexPlayer);
    }
    
    public static void avancerJoueurs(){
        ArrayList<Joueur> joueursNew = new ArrayList<Joueur>();
        for (Joueur joueur : joueurs){
            if(joueur.getCagnotte()>0){
                joueur.avancer();
                joueursNew.add(joueur);
            }
            else{
                joueur.die();
                Window.addConsoleText(joueur.getName()+" est éliminé");
                if(joueurs.size()==1){
                	Window.canJustClick();
                	Window.addConsoleText(joueurs.get(0).getName()+" a gagné la partie !");
                }
            }
        }
        joueurs = joueursNew;
    }
    
    public static void avancerCurrentPlayer(){
        Joueur joueur = joueurs.get(indexPlayer); 
        Controller.controllerMouvPoint(joueur);
		if(joueur.getCagnotte()>0){
			if(joueur.getNbTourInJail()>0){
				joueur.isInJail();
			}else{
				joueur.avancer();
			}
        }
        else{
            joueur.die();
            Window.addConsoleText(joueur.getName()+" est éliminé");
            joueurs.remove(indexPlayer);
            if(joueurs.size()==1){
            	Window.disableAllButton();
            	Window.addConsoleText(joueurs.get(0).getName()+" a gagné la partie !");
            }else{
            	finDuTour();
            }
        }
    }
    
    
    public static void finDuTour(){
    	Window.canLanceDe();
    	if(indexPlayer+1>=joueurs.size()){
			indexPlayer = 0;
		}else{
			indexPlayer++;
		}
    	nextPlayer();
    }
    
    public static void nextPlayer(){
    	Joueur joueur = joueurs.get(indexPlayer); 
    	Window.addConsoleText("\n"+joueur.getName()+" c'est à votre tour !");
    	Window.setInfoPlayer(joueur.getName(), joueur.getCagnotte());
    	if(joueur.getNbTourInJail()>0){
    		Window.addConsoleText("Si vous faites un double vous etes libéré!");
    	}
    }
}
