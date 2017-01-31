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
public class CaseImpot extends Case {
	
	private String pathToPicFile;
	private String caseName;
	private int taxeOnThis;
    
    public CaseImpot(int position, String pathToPic, String caseName, int taxe){
        super(position);
        this.pathToPicFile = pathToPic;
        this.caseName = caseName;
        this.taxeOnThis = taxe;
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
    	infoPanel.add(new JLabel(this.caseName));
    	infoPanel.add(new JLabel("En tombant sur cette case vous devez payer "+this.taxeOnThis+"€"));
        return infoPanel; 
    }
    
    public void onThis (Joueur joueur){
    	super.onThis(joueur);
    	Window.canJustClick();
    	Window.addConsoleText("Vous devez payer "+this.taxeOnThis+"€");
        Window.setCustomButtonLabel(this.setPanelButton());
    }
	
	public JPanel setPanelButton(){
    	JPanel jp = new JPanel();
    	JButton getCard = new JButton("Payer "+this.taxeOnThis+"€");
    	getCard.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			Controller.addCagnotteToParcGratuit(Tours.getJoueur().depenseCagnotte(taxeOnThis));
    			Window.setCustomButtonLabel(Window.resetPanelButton());
    			Window.setInfoPlayer(Tours.getJoueur().getName(), Tours.getJoueur().getCagnotte());
    			if(Tours.getJoueur().getNbDouble()>0){
    				Window.canLanceDe();
    			}else{
    				Window.canContinue();
    			}
    		}
    	});
    	jp.add(getCard);
    	return jp;
    }
    
    @Override
    public void paintComponent(Graphics g){
    	try {
        	Image img = ImageIO.read(this.getClass().getResource(this.pathToPicFile));
        	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), Window.getMainBackgroundColor(), this);
        } catch (IOException e) {
        	e.printStackTrace();
        }                
    }
}

