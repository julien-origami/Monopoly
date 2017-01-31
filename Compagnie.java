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
public class Compagnie extends Propriete{
    
    private int[] multiplicateurTaxe = {400, 1000};
    private String pathToPicFile;
    
    public Compagnie(int position, int priceForBuy, int hypothequePrice, int taxe, String name, String type, String pathToPic){
        super(position, priceForBuy, hypothequePrice, taxe, name, type);
        this.pathToPicFile = pathToPic;
        this.remove(colorPanel);
    }
    
    @Override
    public void paintComponent(Graphics g){
        try {
        	Image img = ImageIO.read(this.getClass().getResource(pathToPicFile));
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), Window.getMainBackgroundColor(), this);
        } catch (IOException e) {
        	e.printStackTrace();
        }                
    }

    public void taxePlayer(Joueur joueur){
        Window.addConsoleText(joueur.getName()+" doit payer "+getTaxe()+"x"+joueur.getDernierLance()+"="+getTaxe()*joueur.getDernierLance()+"€ à"+this.getProprietaire().getName());
        this.payTaxe(joueur);
        Window.addConsoleText("Il vous reste maintenant "+joueur.getCagnotte()+"€");
    }
    
    
    public void buy(Joueur joueur){
        joueur.depenseCagnotte(this.getPriceForBuy());
        this.setProprietaire(joueur);
        newTaxe(joueur);
    }
    
    private void newTaxe(Joueur joueur){
        ArrayList<Propriete> gares = joueur.getProprietesType(this.getType());
        for(int i = 0; i<gares.size(); i++){
            gares.get(i).setTaxe(multiplicateurTaxe[gares.size()-1]);
        }
    }
    
    public void payTaxe(Joueur joueur){
        this.getProprietaire().encaisseCagnotte(joueur.depenseCagnotte(this.getTaxe()*joueur.getDernierLance()));
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
        infoTerrainPanel.add(new JLabel("Avec 1 Compagnie : dernier lancé x "+this.multiplicateurTaxe[0]+"€"));
        infoTerrainPanel.add(new JLabel("Avec 2 Compagnies : dernier lancé x "+this.multiplicateurTaxe[1]+"€"));
        infoPanel.add(infoTerrainPanel);
        return infoPanel;
    }
}
