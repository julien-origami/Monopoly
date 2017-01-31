package Monopoly;

import java.awt.Color;
import java.util.ArrayList;

public class TerrainGroup{
    
    private ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    private boolean canBuildHome;
    private Color colorGroup;
    
    public TerrainGroup(Color color){
        this.setCanBuildHome(false);
        this.colorGroup = color;
    }
    
    public ArrayList<Terrain> getTerrains(){
        return terrains;
    }
    
    
    public boolean setCanBuildHome(Joueur joueur){
        this.canBuildHome=true;
        for(int i = 0; i<terrains.size(); i++){
            if(terrains.get(i).getProprietaire()!=null){
                if(terrains.get(i).getProprietaire().getName()!=joueur.getName()){
                    this.canBuildHome=false;
                    break;
                }
            }
            else{
            	this.canBuildHome=false;
            	break;
            }
        }
        if(this.canBuildHome == true){
        	joueur.addTerrainGroup(this);
        }
        return this.canBuildHome;
    }
    
    public void addTerrain(Terrain terrain){
        terrains.add(terrain);
    }

	public boolean getCanBuildHome() {
		return canBuildHome;
	}

	public void setCanBuildHome(boolean canBuildHome) {
		this.canBuildHome = canBuildHome;
	}   
	
	public Color getColorGroup(){
		return this.colorGroup;
	}
	
	public boolean getThereAreHouse(){
		boolean thereAreHouse = false;
		if(canBuildHome){
			for(Terrain terrain : terrains){
				if(terrain.getNbHouse()>0){
					thereAreHouse = true;
				}
			}
		}
		return thereAreHouse;
	}
}
