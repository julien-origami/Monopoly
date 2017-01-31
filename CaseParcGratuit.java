package Monopoly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class CaseParcGratuit  extends Case{
	
	public int cagnotteParc;
    
    public CaseParcGratuit(int position){
        super(position);
        this.cagnotteParc = 0;
        this.remove(colorPanel);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
            	Window.setCenterPanel(createCenterPanel());
            }
        });
    }
    
    public void onThis (Joueur joueur){
    	super.onThis(joueur);
    	Window.addConsoleText("Vous venez etes tombé sur le parc Gratuit! vous encaissez "+cagnotteParc+"€");
    	if(this.cagnotteParc>0){
    		joueur.encaisseCagnotte(this.cagnotteParc);
    		Window.setInfoPlayer(joueur.getName(), joueur.getCagnotte());
            this.resetCagnotte();
    	}
    }
    
    @Override
    public void paintComponent(Graphics g){
        try {
        	Image img = ImageIO.read(this.getClass().getResource("parcGratuit.png"));
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), Window.getMainBackgroundColor(), this);
        } catch (IOException e) {
        	e.printStackTrace();
        }                
    }
    
    public void resetCagnotte(){
    	this.cagnotteParc = 0;
    }
    
    public void addCagnotte(int encaisse){
    	this.cagnotteParc += encaisse;
    }
    
    public JPanel createCenterPanel(){
    	JPanel infoPanel = new JPanel();
    	
    	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
    	infoPanel.setBackground(new Color(240, 248, 255));
    	Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
    	infoPanel.setBorder(border);
    	infoPanel.add(new JLabel("Parc Gratuit"));
    	infoPanel.add(new JLabel("La cagnotte du parc est de "+cagnotteParc+"€"));
        return infoPanel; 
    }
}
