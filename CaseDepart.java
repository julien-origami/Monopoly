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
public class CaseDepart extends Case{
    
    public CaseDepart(int position){
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
    	infoPanel.add(new JLabel("Case départ"));
    	infoPanel.add(new JLabel("En passant par cette case vous recevez 20000€"));
    	infoPanel.add(new JLabel("En vous arrétant dessus vous recevez le double"));
        return infoPanel; 
    }
    
    public void onThis (Joueur joueur){
    	super.onThis(joueur);
        joueur.setCagnotte(joueur.getCagnotte()+20000);
    }
    
    @Override
    public void paintComponent(Graphics g){
    	try {
        	Image img = ImageIO.read(this.getClass().getResource("depart.png"));
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), Window.getMainBackgroundColor(), this);
        } catch (IOException e) {
        	e.printStackTrace();
        }                
    }
}
