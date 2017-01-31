package Monopoly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class CasePrison extends Case{
	
	public CasePrison(int position){
        super(position);
        this.remove(colorPanel);
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
    	infoPanel.add(new JLabel("Prison"));
        return infoPanel; 
    }
    
    public void onThis (Joueur joueur){
    	super.onThis(joueur);
        if(joueur.getNbTourInJail()>0){
        	joueur.newTourInJail(true);
        	if(joueur.getNbTourInJail()>4){
        		joueur.newTourInJail(false);
        		Window.addConsoleText("Vous avez du payer 5000€");
        		joueur.depenseCagnotte(5000);
        		Controller.addCagnotteToParcGratuit(5000);
        	}
        	else{
        		setPlayerFree(joueur);
        	}
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        try {
        	Image img = ImageIO.read(this.getClass().getResource("prison.png"));
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), Window.getMainBackgroundColor(), this);
        } catch (IOException e) {
        	e.printStackTrace();
        }                
    }
    
    
    public void setPlayerFree(Joueur joueur){
    	Window.addConsoleText("Payez 5000€ ou essayez de faire un double au prochain tour");
    	Window.setCustomButtonLabel(setPanelButtonFree());
    }
    
    public JPanel setPanelButtonFree(){
    	JPanel jp = new JPanel();
    	JButton buyPropriete = new JButton("Payer 5000€");
    	buyPropriete.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			Window.addConsoleText("Vous êtes libérés de prison");
    			Tours.getJoueur().newTourInJail(false);
    			Tours.getJoueur().depenseCagnotte(5000);
    			Controller.addCagnotteToParcGratuit(5000);
    			Window.setInfoPlayer(Tours.getJoueur().getName(), Tours.getJoueur().getCagnotte());
    			Window.setCustomButtonLabel(Window.resetPanelButton());
    			Window.canContinue();
    		}
    	});
    	jp.add(buyPropriete);
    	return jp;
    }
}
