package Monopoly;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public abstract class Propriete extends Case{
    
	protected int priceForBuy;
    protected int hypothequePrice;
    protected int taxe;
    protected String name;
    protected Joueur proprietaire;
    protected int taxeInitiale;
    protected String type;
    
    public Propriete(int position, int priceForBuy, int hypothequePrice, int taxe, String name, String type){
        super(position);
        this.priceForBuy = priceForBuy;
        this.hypothequePrice = hypothequePrice;
        this.taxe = taxe;
        this.taxeInitiale=taxe;
        this.name = name;
        this.type = type;
        this.proprietaire = null;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
            	Window.setCenterPanel(createCenterPanel());
            }
        });
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
        		infoPanel.add(new JLabel("En tombant sur cette propriété vous devez payer : "+taxe+"€"));
        	}
        	else{
        		infoPanel.add(new JLabel("Cette propriété est à vous"));
        		infoPanel.add(new JLabel("En tombant sur votre propriété cela vous rapporte "+taxe+"€"));
        		infoPanel.add(actionProprieteButton());
        	}
        }
        return infoPanel; 
    }
    
    
    public void onThis (Joueur joueur){
    	super.onThis(joueur);
        if(this.getProprietaire()==null){
            if(joueur.canBuy(this.getPriceForBuy())==true){
            	this.buyPropriete(joueur);
            }
        }
        else if(this.getProprietaire().getName()!=joueur.getName()){
            taxePlayer(joueur);
        }
        else{
            Window.addConsoleText(joueur.getName()+" vous etes sur votre Propriété!!");
        }

    }
    
    public JPanel actionProprieteButton(){
    	JButton sellButton = new JButton("Vendre la propriete");
		sellButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			sellPropriete();
    			Window.addConsoleText("Vous avez vendu "+getName());
    			Window.setCenterPanel(createCenterPanel());
    		}
    	});
		JPanel actionPanel = new JPanel();
		actionPanel.add(sellButton);
    	return actionPanel;
    }
    
    public void buyPropriete(Joueur joueur){
    	Window.addConsoleText(joueur.getName()+" voulez vous acheter "+this.getName()+" pour "+this.getPriceForBuy()+" € ?");
    	Window.setCustomButtonLabel(this.setPanelButton());
    }
    
    public JPanel setPanelButton(){
    	JPanel jp = new JPanel();
    	JButton buyPropriete = new JButton("Acheter cette propriété");
    	buyPropriete.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			buy(Tours.getJoueur());
    			Window.addConsoleText("Vous venez d'acheter ce terrain!");
    			Window.setInfoPlayer(Tours.getJoueur().getName(), Tours.getJoueur().getCagnotte());
    			Window.setCustomButtonLabel(Window.resetPanelButton());
    		}
    	});
    	JButton refusePropriete = new JButton("Refuser cette propriété");
    	refusePropriete.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			Window.addConsoleText("Vous n'avez pas acheté ce terrain!");
    			Window.setCustomButtonLabel(Window.resetPanelButton());
    		}
    	});
    	jp.add(buyPropriete);
    	jp.add(refusePropriete);
    	return jp;
    }
    
    public abstract void taxePlayer(Joueur joueur);
    
    public abstract void buy(Joueur joueur);
    
    public int getPriceForBuy(){
        return priceForBuy;
    }
    
    public void sellPropriete(){
    	proprietaire.encaisseCagnotte(this.hypothequePrice);
    	Window.setInfoPlayer(proprietaire.getName(), proprietaire.getCagnotte());
    	this.resetProprietaire();
    }
    
    public int getHypothequePrice(){
        return hypothequePrice;
    }
    
    public int getTaxe(){
        return taxe;
    }
    
    public void setTaxe(int newTaxe){
        this.taxe = newTaxe;
    }
    
    public String getType(){
        return type;
    }
    
    public String getName(){
        return name;
    }
    
    public Joueur getProprietaire(){
        return proprietaire;
    }
    
    public void setProprietaire(Joueur proprietaire){
        this.proprietaire = proprietaire;
        proprietaire.addPropriete(this);
    }
    
    public void payTaxe(Joueur joueur){
        proprietaire.encaisseCagnotte(joueur.depenseCagnotte(taxe));
    }
    
    public void resetProprietaire(){
        proprietaire=null;
        taxe=taxeInitiale;
    }
    
}
