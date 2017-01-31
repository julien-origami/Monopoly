package Monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class FirstWindow extends JFrame{
	
	JPanel mainPanel;
	JPanel firstPanel;
	JPanel secondPanel;
	JButton validButton;
	JLabel errorReport;
	ArrayList<Joueur> joueurs;
	int nbPlayer = 0;
	ArrayList<Color> colors = new ArrayList<Color>();
	JTextField playerName;

	public FirstWindow(){
		super("MONOPOLY");
		
		/*Set Possible Colors*/
		colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        colors.add(Color.RED);
		/*End of Set Possible Colors*/
        
        joueurs = new ArrayList<Joueur>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        mainPanel = new JPanel();
        
        Color mainBackgroundColor =  new Color(205, 230, 208);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        JPanel logoPanel = new JPanel(){
        	@Override
            public void paintComponent(Graphics g){
            	try {
            		Image img = ImageIO.read(this.getClass().getResource("monopolyLogo.png"));
                	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                	e.printStackTrace();
                }                
            }
        };
        logoPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 80));
        
        mainPanel.add(logoPanel);
        JLabel playerJl = new JLabel("Les joueurs :");
        playerJl.setHorizontalAlignment(SwingConstants.CENTER);
        playerJl.setFont(new Font("SANS_SERIF", Font.CENTER_BASELINE, 15));
        playerJl.setBorder(new CompoundBorder(playerJl.getBorder(), new EmptyBorder(20,0,20,0)));
        JPanel panelTitre = new JPanel();
        panelTitre.setLayout(new FlowLayout());
        panelTitre.add(playerJl);
        panelTitre.setBackground(mainBackgroundColor);
        mainPanel.add(panelTitre);
        mainPanel.setBackground(mainBackgroundColor);
        
        /*########## First Box ##########*/
        	firstPanel = new JPanel();
        	firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.PAGE_AXIS));
        	firstPanel.setBackground(mainBackgroundColor);
        	mainPanel.add(firstPanel);
        /*########## End First Box ##########*/
	    
	        
	    /*########## Second Box ##########*/
	        secondPanel = new JPanel();
	        secondPanel.setBackground(mainBackgroundColor);
	        secondPanel.setLayout(new GridLayout(3, 1));
	        
	        /*Panel 2.2*/
	        JPanel addPlayerForm = new JPanel();
	        addPlayerForm.setBackground(mainBackgroundColor);
	        playerName = new JTextField(10);
	        addPlayerForm.add(playerName);
	        
	        validButton = new JButton("Ajouter ce joueur");
	        validButton.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) {  
	        		  if(getSameName()==false){
		        		  JPanel newPlayer = new JPanel();
		        		  newPlayer.setBackground(mainBackgroundColor);
		        		  joueurs.add(new Joueur(playerName.getText(), colors.get(nbPlayer)));
		        		  nbPlayer++;
		        		  newPlayer.add(new JLabel(playerName.getText()));
		        		  addToFirstPanel(newPlayer);
		        		  playerName.setText("");
		        		  if(nbPlayer>4){
		        			  validButton.setEnabled(false);
		        		  }
		        		  setAnErrorReport("");
	        		  }
	        		  else{
	        			  setAnErrorReport("Ce nom existe déjà !");
	        		  }
	        	  }
	        });
	        addPlayerForm.add(validButton);
	        /*End of Panel 2.2*/
	        JLabel nameJl = new JLabel("Entrez votre nom");
	        nameJl.setHorizontalAlignment(SwingConstants.CENTER);
	        nameJl.setFont(new Font("SANS_SERIF", Font.CENTER_BASELINE, 15));
	        nameJl.setBorder(new CompoundBorder(nameJl.getBorder(), new EmptyBorder(20,0,0,0)));
	        
	        errorReport = new JLabel("");
	        errorReport.setHorizontalAlignment(SwingConstants.CENTER);
	        errorReport.setFont(new Font("SANS_SERIF", Font.CENTER_BASELINE, 11));
	        errorReport.setForeground(Color.red);
	        
	        secondPanel.add(nameJl);
	        secondPanel.add(errorReport);
	        secondPanel.add(addPlayerForm);
	        
	        mainPanel.add(secondPanel);
        /*########## End Left Box ##########*/
	        
	        /*########## Third Box ##########*/
	        JPanel thirdPanel = new JPanel();
	        thirdPanel.setBackground(mainBackgroundColor);
	        /*Panel 3.3*/	        
	        JButton startGame = new JButton("Commencer la partie");
	        startGame.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) {   
	        		  if(joueurs.size()>1){
	        			  dispose();
		        		  Main.playTheGame(joueurs);
	        		  }else{
	        			  setAnErrorReport("Il faut ajouter au moins deux joueurs !");
	        		  }
	        	  }
	        });
	        thirdPanel.add(startGame);
	        
	        JButton restartPlayers = new JButton("Tout effacer");
	        restartPlayers.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) {   
	        		  joueurs.removeAll(joueurs);
	        		  nbPlayer = 0;
	        		  resetFirstPanel();
	        		  validButton.setEnabled(true);
	        		  setAnErrorReport("");
	        	  }
	        });
	        thirdPanel.add(restartPlayers);
	        /*End of Panel 3.3*/
	        
	        mainPanel.add(thirdPanel);
        /*########## End Third Box ##########*/
        
	    this.add(mainPanel);
        this.pack();
        this.setVisible(true);
	}
	
	public void addToFirstPanel(JPanel newPlayer){
		firstPanel.add(newPlayer);
		firstPanel.repaint();
		firstPanel.revalidate();
		this.pack();
	}
	
	public void resetFirstPanel(){
		firstPanel.removeAll();
		firstPanel.repaint();
		firstPanel.revalidate();
		this.pack();
	}
	
	public void setAnErrorReport(String newReport){
		errorReport.setText(newReport);
		secondPanel.repaint();
		secondPanel.revalidate();
		this.pack();
	}
	
	public boolean getSameName(){
		boolean isTrue = false;
		for(Joueur joueur : joueurs){
			if(joueur.getName().equals(playerName.getText())){
				isTrue = true;
			}
		}
		return isTrue;
	}
}
