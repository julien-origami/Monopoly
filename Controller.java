package Monopoly;

import java.util.ArrayList;

public class Controller{
    private static ArrayList<Case> cases;
    
    public static void controllerPosition(Joueur joueur){
        Case caseControl = cases.get(joueur.getPosition());
        caseControl.onThis(joueur);
    }
    
    public static void jailButton(Joueur joueur){
    	if(joueur.getPosition()==10){
    		CasePrison caseControl = (CasePrison)cases.get(10);
            caseControl.setPlayerFree(joueur);
    	}
    }
    
    public static void controllerMouvPoint(Joueur joueur){
        Case caseControl = cases.get(joueur.getPosition());
        caseControl.onRemovePoint(joueur);
    }
    
    public static void addCases(ArrayList<Case> casesNew){
        cases = new ArrayList<Case>(casesNew);
    }
    
    public static ArrayList<Case> getCases(){
        return cases;
    }
    
    public static void addCagnotteToParcGratuit(int encaisse){
    	CaseParcGratuit parcGratuit = (CaseParcGratuit)cases.get(20);
    	parcGratuit.addCagnotte(encaisse);
    }
}
