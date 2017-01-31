package Monopoly;

import java.awt.Color;
import java.util.ArrayList;

public class Main{
	
    public static void main(String[] args) {
        
        TerrainGroup magenta = new TerrainGroup(Color.MAGENTA);
        TerrainGroup bleuCiel = new TerrainGroup(Color.CYAN);
        TerrainGroup darkGray = new TerrainGroup(Color.DARK_GRAY);
        TerrainGroup orange = new TerrainGroup(Color.ORANGE);
        TerrainGroup rouge = new TerrainGroup(Color.RED);
        TerrainGroup jaune = new TerrainGroup(Color.YELLOW);
        TerrainGroup vert = new TerrainGroup(Color.GREEN);
        TerrainGroup bleu = new TerrainGroup(Color.BLUE);
        
        
        CarteAction carteAction = new CarteAction();
        
        ArrayList<Case> cases = new ArrayList<Case>();
        for(int i = 0; i<40; i++){
            switch (i){
                case 0:
                    cases.add(new CaseDepart(i));
                    break;
                case 1:
                    int[]taxe= new int[] {200,3000,9000,16000,25000};
                    //int position, int priceForBuy, int hypothequePrice, int[] taxe, String name, String type, int housePrice, TerrainGroup terrainGroup
                    cases.add(new Terrain(i, 6000, 3000, taxe, "Boulevard de belleville", "Terrain", 5000, magenta));
                    break;
                    
                case 2:
                	cases.add(new CaseCarte(i, "caisseCommunaute.png", "Caisse de communauté", carteAction));
                	break;
                    
                case 3:
                    taxe= new int[]{400,6000,18000,32000,45000};
                    cases.add(new Terrain(i, 6000, 3000, taxe, "Rue Lecourbe", "Terrain", 5000, magenta));
                    break;
                    
                case 4:
                	cases.add(new CaseImpot(i, "impotsTax.png", "Impôts sur le revenu", 20000));
                	break;
                    
                case 5:
                    cases.add(new Gare(i, 20000, 10000, 2500, "Gare Montparnasse", "Gare"));
                    break;
                case 6:
                    taxe= new int[]{600,9000,27000,40000,55000};
                    cases.add(new Terrain(i, 10000, 5000, taxe, "Rue de Vaugirard", "Terrain", 5000, bleuCiel));
                    break;
                    
                case 7:
                	cases.add(new CaseCarte(i, "chance.png", "Chance", carteAction));
                	break;
                    
                case 8:
                    taxe= new int[]{600,9000,27000,40000,55000};
                    cases.add(new Terrain(i, 10000, 5000, taxe, "Rue de Courcelles", "Terrain", 5000, bleuCiel));
                    break;
                    
                case 9:
                    taxe= new int[]{800,10000,30000,45000,60000};
                    cases.add(new Terrain(i, 12000, 6000, taxe, "Avenue de la République", "Terrain", 5000, bleuCiel));
                    break;
                
                case 10:
                    cases.add(new CasePrison(i));
                    break;
                    
                case 11:
                    taxe= new int[]{1000,15000,45000,62500,75000};
                    cases.add(new Terrain(i, 14000, 7000, taxe, "Boulevard de la Vilette", "Terrain", 10000, darkGray));
                    break;
                    
                case 12:
                    cases.add(new Compagnie(i, 15000, 7500, 400, "Compagnie d'élécticité", "Compagnie", "companyElec.png"));
                    break;
                    
                case 13:
                    taxe= new int[]{1000,15000,45000,62500,75000};
                    cases.add(new Terrain(i, 14000, 7000, taxe, "Avenue de Neuilly", "Terrain", 10000, darkGray));
                    break;
                    
                case 14:
                    taxe= new int[]{1200,18000,50000,70000,90000};
                    cases.add(new Terrain(i, 16000, 8000, taxe, "Rue de Paradis", "Terrain", 10000, darkGray));
                    break;
                case 15:
                    cases.add(new Gare(i, 20000, 10000, 2500, "Gare de Lyon", "Gare"));
                    break;
                case 16:
                    taxe= new int[]{1400,20000,55000,75000,95000};
                    cases.add(new Terrain(i, 18000, 9000, taxe, "Avenue Mozart", "Terrain", 10000, orange));
                    break;
                    
                case 17:
                	cases.add(new CaseCarte(i, "caisseCommunaute.png", "Caisse de communauté", carteAction));
                	break;
                    
                case 18:
                    taxe= new int[]{1400,20000,55000,75000,95000};
                    cases.add(new Terrain(i, 18000, 9000, taxe, "Boulevard Saint-Michel", "Terrain", 10000, orange));
                    break;
                    
                case 19:
                    taxe= new int[]{1600,22000,60000,80000,100000};
                    cases.add(new Terrain(i, 20000, 10000, taxe, "Place Pigalle", "Terrain", 10000, orange));
                    break;
                    
                case 20:
                	cases.add(new CaseParcGratuit(i));
                	break;
                    
                case 21:
                    taxe= new int[]{1800,25000,70000,87500,105000};
                    cases.add(new Terrain(i, 22000, 11000, taxe, "Avenue Matignon", "Terrain", 15000, rouge));
                    break;
                    
                case 22:
                	cases.add(new CaseCarte(i, "chance.png", "Chance", carteAction));
                	break;
                    
                case 23:
                    taxe= new int[]{1800,25000,70000,87500,105000};
                    cases.add(new Terrain(i, 22000, 11000, taxe, "Boulevard Malesherbes", "Terrain", 15000, rouge));
                    break;
                case 24:
                    taxe= new int[]{2000,30000,75000,92500,110000};
                    cases.add(new Terrain(i, 24000, 12000, taxe, "Avenue Henri-Martin", "Terrain", 15000, rouge));
                    break;
                case 25:
                    cases.add(new Gare(i, 20000, 10000, 2500, "Gare du Nord", "Gare"));
                    break;
                case 26:
                    taxe= new int[]{2200,33000,80000,97500,115000};
                    cases.add(new Terrain(i, 26000, 13000, taxe, "Faubourg Saint-Honore", "Terrain", 15000, jaune));
                    break;
                    
                case 27:
                    taxe= new int[]{2200,33000,80000,97500,115000};
                    cases.add(new Terrain(i, 26000, 13000, taxe, "Place de la bourse", "Terrain", 15000, jaune));
                    break;
                    
                case 28:
                    cases.add(new Compagnie(i, 15000, 7500, 400, "Compagnie d'eau", "Compagnie", "companyWater.png"));
                    break;
                    
                case 29:
                    taxe= new int[]{2400,36000,85000,102500,120000};
                    cases.add(new Terrain(i, 28000, 14000, taxe, "Rue Lafayette", "Terrain", 15000, jaune));
                    break;
                
                case 30:
                    cases.add(new CaseToPrison(i));
                    break;
                    
                case 31:
                    taxe= new int[]{2600,39000,90000,110000,127500};
                    cases.add(new Terrain(i, 30000, 15000, taxe, "Avenue de Breuteuil", "Terrain", 20000, vert));
                    break;
                    
                case 32:
                    taxe= new int[]{2600,39000,90000,110000,127500};
                    cases.add(new Terrain(i, 30000, 15000, taxe, "Avenue Foch", "Terrain", 20000, vert));
                    break;
                    
                case 33:
                	cases.add(new CaseCarte(i, "caisseCommunaute.png", "Caisse de communauté", carteAction));
                	break;
                    
                case 34:
                    taxe= new int[]{2800,45000,100000,120000,140000};
                    cases.add(new Terrain(i, 32000, 16000, taxe, "Boulevard des Capucines", "Terrain", 20000, vert));
                    break;
                    
                case 36:
                	cases.add(new CaseCarte(i, "chance.png", "Chance", carteAction));
                	break;
                    
                case 35:
                    cases.add(new Gare(i, 20000, 10000, 2500, "Gare Saint Lazar", "Gare"));
                    break;
                    
                case 37:
                    taxe= new int[]{3500,50000,110000,130000,150000};
                    cases.add(new Terrain(i, 35000, 17500, taxe, "Avenue des Champs-Elysées", "Terrain", 20000, bleu));
                    break;
                    
                case 38:
                	cases.add(new CaseImpot(i, "luxuryTax.png", "Taxe de luxe", 10000));
                	break;
                    
                case 39:
                    taxe= new int[]{5000,60000,140000,170000,200000};
                    cases.add(new Terrain(i, 40000, 20000, taxe, "Rue de la Paix", "Terrain", 20000, bleu));
                    break;
                default:
                    cases.add(new Case(i));
                    break;
            }
        }
        Controller.addCases(cases);
        
        new FirstWindow();
            
    }
    
    public static void playTheGame(ArrayList<Joueur> newJoueurs){
    	ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        
        joueurs = Des.tirageAuSort(newJoueurs);
        
        new Window(Controller.getCases());
        
        Tours.add(joueurs);
        
        for(Joueur joueur : joueurs){
        	Controller.controllerPosition(joueur);
        }
        
        Window.canLanceDe();
        Tours.nextPlayer();
    }
}
