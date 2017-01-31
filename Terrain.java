package Monopoly;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Terrain extends Propriete{
    
    private int[] taxeWithHome;
    private int housePrice;
	private TerrainGroup terrainGroup;
	private int nbHouse = 0;
    
    public Terrain(int position, int priceForBuy, int hypothequePrice, int[] taxe, String name, String type, int housePrice, TerrainGroup terrainGroup){
        super(position, priceForBuy, hypothequePrice, taxe[0], name, type);
        this.taxeWithHome = taxe;
        this.housePrice = housePrice;
        setTerrainGroup(terrainGroup);
        this.setDefaultBackground(terrainGroup.getColorGroup());
    }
    
    public void taxePlayer(Joueur joueur){
        System.out.println(joueur.getName()+" doit payer "+getTaxe()+"€ à"+this.getProprietaire().getName());
        System.out.println(joueur.getName()+" avait "+joueur.getCagnotte()+"€ et "+this.getProprietaire().getName()+" avait "+this.getProprietaire().getCagnotte());
        this.payTaxe(joueur);
        System.out.println(joueur.getName()+" a maintenant "+joueur.getCagnotte()+"€ et "+this.getProprietaire().getName()+" a "+this.getProprietaire().getCagnotte());
    }
    
    public void buy(Joueur joueur){
        joueur.depenseCagnotte(this.getPriceForBuy());
        this.setProprietaire(joueur);
        newTaxe(joueur);
    }
    
    public void setTerrainGroup(TerrainGroup terrainGroup){
        this.terrainGroup = terrainGroup;
        terrainGroup.addTerrain(this);
    }
    
    public void payTaxe(Joueur joueur){
    	if(this.terrainGroup.getCanBuildHome() && this.nbHouse == 0){
    		this.proprietaire.encaisseCagnotte(joueur.depenseCagnotte(this.taxeWithHome[nbHouse]*2));
    	}
    	else{
    		this.proprietaire.encaisseCagnotte(joueur.depenseCagnotte(this.taxeWithHome[nbHouse]));
    	}	
    }
    
    
    public void buyHouse(){
    	if(this.nbHouse<4){
    		this.proprietaire.depenseCagnotte(housePrice);
    		nbHouse++;	
    	}
    }
    
    
    public void sellHouse(){
    	if(this.nbHouse>0){
    		this.proprietaire.encaisseCagnotte(housePrice);
    		nbHouse--;	
    	}
    }
    
    
    private void newTaxe(Joueur joueur){
        terrainGroup.setCanBuildHome(joueur);
    }
    
    public void resetProprietaire(){
        super.resetProprietaire();
        terrainGroup.setCanBuildHome(false);
        nbHouse = 0;
    }
    
    public JPanel createCenterPanel(){
    	JPanel infoPanel = new JPanel();
    	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
    	infoPanel.setBackground(new Color(240, 248, 255));
    	Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
    	infoPanel.setBorder(border);
    	infoPanel.add(new JLabel(this.getName()));
        if(proprietaire==null){
        	infoPanel.add(new JLabel("Cette proporiété coute : "+priceForBuy+"€"));
        }else{
        	if(proprietaire!=Tours.getJoueur()){
        		infoPanel.add(new JLabel("Cette proporiété est à : "+proprietaire.getName()));
        		infoPanel.add(new JLabel("En tombant sur cette propriété vous devez payer : "+taxeWithHome[nbHouse]+"€"));
        	}
        	else{
        		infoPanel.add(new JLabel("Cette propriété est à vous"));
        		infoPanel.add(new JLabel("Cette vous rapporte "+taxeWithHome[nbHouse]+"€"));
        		infoPanel.add(actionProprieteButton());
        	}
        }
        
        JPanel infoTerrainPanel = new JPanel();
        infoTerrainPanel.setLayout(new BoxLayout(infoTerrainPanel, BoxLayout.PAGE_AXIS));
        infoTerrainPanel.setBorder(border);
        infoTerrainPanel.add(new JLabel("Prix de vente de cette Propriété : "+this.hypothequePrice+"€"));
        infoTerrainPanel.add(new JLabel("-----------------------------------------"));
        infoTerrainPanel.add(new JLabel("Sans maison : "+this.taxeWithHome[0]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 1 maison : "+this.taxeWithHome[1]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 2 maison : "+this.taxeWithHome[2]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 3 maison : "+this.taxeWithHome[3]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 1 hôtel : "+this.taxeWithHome[4]+"€"));
        infoPanel.add(infoTerrainPanel);
        return infoPanel;
    }
        
        
    public JPanel actionProprieteButton(){
    	JPanel actionPanel = new JPanel();
    	actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.PAGE_AXIS));
    	if(terrainGroup.getThereAreHouse()){
    		if(nbHouse>0){
    			actionPanel.add(new JLabel("Vous avez "+nbHouse+" maison sur ce terrain."));
    			JButton sellButton = new JButton("Vendre une maison");
        		sellButton.addActionListener(new ActionListener() { 
            		public void actionPerformed(ActionEvent e) { 
            			sellHouse();
            			Window.addConsoleText("Vous avez vendu une maison de "+getName());
            			Window.setCenterPanel(createCenterPanel());
            		}
            	});
        		if(nbHouse<4){
        			JButton buyButton = new JButton("Acheter un maison");
            		buyButton.addActionListener(new ActionListener() { 
                		public void actionPerformed(ActionEvent e) { 
                			buyHouse();
                			if(nbHouse==4){
                				Window.addConsoleText("Vous avez maintenant un hotel sur "+getName());
                			}
                			else{
                				Window.addConsoleText("Vous avez acheté une maison sur "+getName());	
                			}
                			Window.setCenterPanel(createCenterPanel());
                		}
                	});
            		actionPanel.add(buyButton);
        		}
        		actionPanel.add(sellButton);
    		}else{
    			actionPanel.add(new JLabel("Vous n'avez pas de maison sur ce terrain."));
    			JButton buyButton = new JButton("Acheter un maison");
        		buyButton.addActionListener(new ActionListener() { 
            		public void actionPerformed(ActionEvent e) { 
            			buyHouse();
            			Window.addConsoleText("Vous avez acheté une maison sur "+getName());	
            			Window.setCenterPanel(createCenterPanel());
            		}
            	});
        		actionPanel.add(buyButton);
    		}
    	}else{
    		if(terrainGroup.getCanBuildHome()){
    			actionPanel.add(new JLabel("Vous n'avez pas de maison sur ce terrain."));
    			JButton buyButton = new JButton("Acheter un maison");
        		buyButton.addActionListener(new ActionListener() { 
            		public void actionPerformed(ActionEvent e) { 
            			buyHouse();
            			Window.addConsoleText("Vous avez acheté une maison sur "+getName());	
            			Window.setCenterPanel(createCenterPanel());
            		}
            	});
        		actionPanel.add(buyButton);
    		}else{
    			actionPanel.add(new JLabel("Achetez toutes les propriétés pour construire des maisons."));
    		}
    		JButton sellButton = new JButton("Vendre la propriete");
    		sellButton.addActionListener(new ActionListener() { 
        		public void actionPerformed(ActionEvent e) { 
        			sellPropriete();
        			Window.addConsoleText("Vous avez vendu "+getName());
        			Window.setCenterPanel(createCenterPanel());
        		}
        	});
    		actionPanel.add(sellButton);
    	}
    	return actionPanel;
    }
    
    public int getNbHouse(){
    	return this.nbHouse;
    }
    
}
