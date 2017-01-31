package Monopoly;

import java.awt.Color;
import java.util.ArrayList;


public class Joueur{
    private String name;
    private int position;
    private int cagnotte;
    private Color pionColor;
    private ArrayList<Propriete> proprietes= new ArrayList<Propriete>();
    private ArrayList<TerrainGroup> terrainGroups = new ArrayList<TerrainGroup>();
    private int dernierLance;
    final int initialCagnotte = 130000;
    
    private int nbTourInJail = 0;
    private int nbDouble = 0;
    
    public Joueur(String nom, Color couleur){
        this.name = nom;
        this.position = 0;
        this.cagnotte = initialCagnotte;
        this.pionColor = couleur;
    }
    
    public int getNbDouble(){
    	return nbDouble;
    }
    
    
    public String toString(){
        return name+" "+position+" "+cagnotte+"€";
    }
    
    
    
    
    public void avancer(){
        if(nbTourInJail == 0){
        	dernierLance = Des.lancerDes();
	        if(dernierLance < 0){
	        	nbDouble++;
	        	dernierLance = -dernierLance;
	        	if(nbDouble>=3){
	        		Window.addConsoleText("Vous avez fait trop de doubles.. Direction la case prison!");
	        		this.position = 10;
	        		Window.canContinue();
	        		this.newTourInJail(true);
	        		nbDouble=0;
	        	}
	        	else{
	        		Window.addConsoleText("Avancer de "+dernierLance+" cases");
	        		if(this.position + dernierLance >=40){
		        		this.position = this.position + dernierLance - 40;
		        		cagnotte += 20000;
		    		}
		    		else{
		    			this.position = this.position + dernierLance;
		    		}
	        	}
	        }
	        else{
	        	nbDouble=0;
	        	Window.canContinue();
	        	Window.addConsoleText("Avancer de "+dernierLance+" cases");
	    		if(this.position + dernierLance >=40){
	        		this.position = this.position + dernierLance - 40;
	        		cagnotte += 20000;
	    		}
	    		else{
	    			this.position = this.position + dernierLance;
	    		}
	        }
        }
        Controller.controllerPosition(this);
    }
    
    public void isInJail(){
        if(nbTourInJail > 0){
        	dernierLance = Des.lancerDes();
        	if(dernierLance<0){
        		Window.addConsoleText("Sauvé! Vous avez fait un double");
        		this.newTourInJail(false);
        		dernierLance = -dernierLance;
        		this.position = this.position + dernierLance;
        	}
        	else{
        		Window.addConsoleText("Dommage.. ce n'est pas un double..");
        		Window.canContinue();
        	}
        	Controller.controllerPosition(this);
        }
    }
    
    
    public void addPropriete(Propriete newPropriete){
        proprietes.add(newPropriete);
    }
    
    
    
    
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Color getColor(){
        return this.pionColor;
    }
    
    public int getDernierLance(){
        return this.dernierLance;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public int getNbTourInJail(){
        return this.nbTourInJail;
    }
    
    public void newTourInJail(boolean isTrue){
        if(isTrue){
        	this.nbTourInJail++;
        }
        else{
        	this.nbTourInJail = 0;
        }
    	
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public int getCagnotte(){
        return this.cagnotte;
    }
    
    public void setCagnotte(int cagnotte){
        this.cagnotte = cagnotte;
    }
    
    public int depenseCagnotte(int depense){
    	Window.addConsoleText(this.getName()+" a dépensé "+depense+"€");
        if(cagnotte-depense>=0){
            cagnotte -= depense;
        }
        else{
            depense=cagnotte;
            cagnotte=0;
        }
        return depense;
    }
    
    public void encaisseCagnotte(int encaisse){
    	Window.addConsoleText(this.getName()+" a encaissé "+encaisse+"€");
        cagnotte += encaisse;
    }
    
    
    public boolean canBuy(int proprietePrice){
        if(cagnotte-proprietePrice<=0){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void die(){
    	Controller.controllerMouvPoint(this);
        for(int i = 0; i<proprietes.size(); i++){
            proprietes.get(i).resetProprietaire();
        }
    }
    
    public ArrayList<Propriete> getProprietesType(String type){
        ArrayList<Propriete> proprietesSameType= new ArrayList<Propriete>();
        for(int i = 0; i<proprietes.size(); i++){
            if(proprietes.get(i).getType().equals(type)){
                proprietesSameType.add(proprietes.get(i));
            }
        }
        return proprietesSameType;
    }
    
    public void addTerrainGroup(TerrainGroup terrainGroup){
    	this.terrainGroups.add(terrainGroup);
    }
    
    public ArrayList<TerrainGroup> getTerrainGroup(){
    	if(this.terrainGroups.size()>0){
    		return this.terrainGroups;
    	}else{
    		return null;
    	}
    }
}
