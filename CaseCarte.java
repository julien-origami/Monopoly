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
public class CaseCarte extends Case{
	
	private String pathToPicFile;
	private String typeOfCarte;
	private CarteAction carteAction;
	
	public CaseCarte(int position, String pathFile, String typeOfCarte, CarteAction carteAction) {
		super(position);
		this.carteAction = carteAction;
		this.typeOfCarte = typeOfCarte;
		this.pathToPicFile = pathFile;
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
    	infoPanel.add(new JLabel(typeOfCarte));
        return infoPanel; 
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
	
	public void onThis (Joueur joueur){
    	super.onThis(joueur);
    	Window.canJustClick();
    	Window.addConsoleText("Vous devez tirez une carte "+typeOfCarte);
    	Window.setCustomButtonLabel(this.setPanelButton());
    }
	
	public JPanel setPanelButton(){
    	JPanel jp = new JPanel();
    	JButton getCard = new JButton("Tirez une carte");
    	getCard.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			Window.setCustomButtonLabel(Window.resetPanelButton());
    			carteAction.doAction();
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
}
