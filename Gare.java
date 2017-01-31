package Monopoly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Gare extends Propriete{
    
    private int[] taxeNbGare = {2500, 5000, 10000, 20000};
    
    public Gare(int position, int priceForBuy, int hypothequePrice, int taxe, String name, String type){
        super(position, priceForBuy, hypothequePrice, taxe, name, type);
        this.remove(colorPanel);
    }
    
    @Override
    public void paintComponent(Graphics g){
        try {
        	Image img = ImageIO.read(this.getClass().getResource("gareMonopoly.png"));
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), Window.getMainBackgroundColor(), this);
        } catch (IOException e) {
        	e.printStackTrace();
        }                
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
        		infoPanel.add(new JLabel("En tombant sur cette propriété vous devez payer : "+this.getTaxe()+"€"));
        	}
        	else{
        		infoPanel.add(new JLabel("Cette propriété est à vous"));
        		infoPanel.add(new JLabel("Cette vous rapporte "+this.getTaxe()+"€"));
        		infoPanel.add(actionProprieteButton());
        	}
        }
        
        JPanel infoTerrainPanel = new JPanel();
        infoTerrainPanel.setLayout(new BoxLayout(infoTerrainPanel, BoxLayout.PAGE_AXIS));
        infoTerrainPanel.setBorder(border);
        infoTerrainPanel.add(new JLabel("Prix de vente de cette Propriété : "+this.hypothequePrice+"€"));
        infoTerrainPanel.add(new JLabel("-----------------------------------------"));
        infoTerrainPanel.add(new JLabel("Avec 1 Gare : "+this.taxeNbGare[0]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 2 Gares : "+this.taxeNbGare[1]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 3 Gares : "+this.taxeNbGare[2]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 4 Gares : "+this.taxeNbGare[3]+"€"));
        infoPanel.add(infoTerrainPanel);
        return infoPanel;
    }
    
    private void newTaxe(Joueur joueur){
        ArrayList<Propriete> gares = joueur.getProprietesType(this.getType());
        for(int i = 0; i<gares.size(); i++){
            gares.get(i).setTaxe(taxeNbGare[gares.size()-1]);
        }
    }
    
}
