package Monopoly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Window extends JFrame{
	
	private static JPanel centerPanel;
	private static JTextArea consoleText;
	private static JPanel customButtonLabel;
	private static JPanel gameBoxRight;
	private static JTextField namePlayer;
	private static JTextField cagnottePlayer;
	private static JButton bLanceDe;
	private static JButton bContinue;
	private static JButton abandonButton;
	private Color mainBackgroundColor =  getMainBackgroundColor();
	
	
	public static Color getMainBackgroundColor(){
		return new Color(205, 230, 208);
	}
	
	public static void addConsoleText(String addString){
		consoleText.setText(consoleText.getText()+addString+"\n"); 
	}
	
	public static void setCenterPanel(JPanel newPanel){
		centerPanel.removeAll();
		centerPanel.add(newPanel);
		centerPanel.repaint(); 
		centerPanel.revalidate(); 
	}
	
	public static JPanel resetPanelButton(){
    	return new JPanel();
    }
	
	public static void setInfoPlayer(String name, int cagnotte){
		namePlayer.setText(name);
		cagnottePlayer.setText(cagnotte+"€");
		gameBoxRight.repaint(); 
		gameBoxRight.revalidate(); 
	}
	
	public static void setCustomButtonLabel(JPanel newPanel){
		customButtonLabel.removeAll();
		customButtonLabel.add(newPanel);
		customButtonLabel.repaint(); 
		customButtonLabel.revalidate(); 
	}
	
	public static void canLanceDe(){
		bLanceDe.setEnabled(true);
		bContinue.setEnabled(false);
		centerPanel.repaint(); 
		centerPanel.revalidate(); 
	}
	
	public static void canContinue(){
		bLanceDe.setEnabled(false);
		bContinue.setEnabled(true);
		centerPanel.repaint(); 
		centerPanel.revalidate(); 
	}
	
	public static void canJustClick(){
		bLanceDe.setEnabled(false);
		bContinue.setEnabled(false);
		centerPanel.repaint(); 
		centerPanel.revalidate(); 
	}
	
	public static void disableAllButton(){
		abandonButton.setEnabled(false);
		bLanceDe.setEnabled(false);
		bContinue.setEnabled(false);
		centerPanel.repaint(); 
		centerPanel.revalidate(); 
	}
	
	
	public Window(ArrayList<Case> cases){
		super("MONOPOLY");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.setLayout(new GridLayout(1, 2));
        
        /*########## Left Box ##########*/
        	JPanel gameBoxLeft = new JPanel();
        
	        /*###### Carton de jeux Nord ######*/
	        JPanel nord = new JPanel();
	        nord.setLayout(new GridLayout(1,11));
	        for (int row = 0; row < 11; row++) {
	        	Case cellPane = cases.get(row);
	            Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
	            cellPane.setBorder(border);
	            nord.add(cellPane);
	        }
	        /*###### End Carton Nord ######*/
	        
	        
	        /*###### Carton de jeux Est ######*/
	        JPanel est = new JPanel();
	        est.setLayout(new GridLayout(9,1));
	        for (int row = 11; row < 20; row++) {
	        	Case cellPane = cases.get(row);
	            Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
	            cellPane.setBorder(border);
	            est.add(cellPane);
	        }
	        /*###### End Carton Est ######*/
	        
	        
	        /*###### Carton de jeux Sud ######*/
	        JPanel sud = new JPanel();
	        sud.setLayout(new GridLayout(1,11));
	        for (int row = 30; row > 19; row--) {
	        	Case cellPane = cases.get(row);
	            Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
	            cellPane.setBorder(border);
	            sud.add(cellPane);
	        }
	        /*###### End Carton Sud ######*/
	        
	        
	        /*###### Carton de jeux Ouest ######*/
	        JPanel ouest = new JPanel();
	        ouest.setLayout(new GridLayout(9,1));
	        for (int row = 39; row > 30; row--) {
	        	Case cellPane = cases.get(row);
	            Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
	            cellPane.setBorder(border);
	            ouest.add(cellPane);
	        }
	        /*###### End Carton Ouest ######*/
	        
	        centerPanel = new JPanel();
	        centerPanel.setBackground(mainBackgroundColor);
	        gameBoxLeft.setLayout(new BorderLayout());
	        gameBoxLeft.add(nord, BorderLayout.NORTH);
	        gameBoxLeft.add(sud, BorderLayout.SOUTH);
	        gameBoxLeft.add(est, BorderLayout.EAST);
	        gameBoxLeft.add(ouest, BorderLayout.WEST);
	        gameBoxLeft.add(centerPanel, BorderLayout.CENTER);
        /*########## End Left Box ##########*/
	    
	        
	    /*########## Right Box ##########*/
	        gameBoxRight = new JPanel();
	        
	        /*###### First JPanel ######*/
	        JPanel firstRight = new JPanel();
	        Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
	        firstRight.setBorder(border);
	        consoleText = new JTextArea("####### Bienvenue au Monopoly #######\n\n");
	        consoleText.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(consoleText);
	        scrollPane.setPreferredSize(new Dimension(500, 150));
	        bLanceDe = new JButton("Lancer les dés");
	        bLanceDe.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		  setCustomButtonLabel(resetPanelButton());  
	        		  Tours.avancerCurrentPlayer();
	        	  }
	        });
	        firstRight.setLayout(new BoxLayout(firstRight, BoxLayout.Y_AXIS));
	        firstRight.add(scrollPane);
	        firstRight.add(bLanceDe);
	        /*###### End First JPanel ######*/
	        
	        /*###### Second JPanel ######*/
	        JPanel secondRight = new JPanel();
	        secondRight.setBorder(border);
	        secondRight.setLayout(new GridLayout(2, 1));
	        namePlayer = new JTextField();
	        namePlayer.setEditable(false);
	        cagnottePlayer = new JTextField();
	        cagnottePlayer.setEditable(false);
	        customButtonLabel = new JPanel();
	        JPanel infoPanel = new JPanel();
	        infoPanel.setLayout(new GridLayout(2, 1));
	        JPanel jp11 = new JPanel();
	        jp11.add(new JLabel("Nom du joueur : "));
	        jp11.add(namePlayer);
	        JPanel jp12 = new JPanel();
	        jp12.add(new JLabel("Cagnotte du joueur : "));
	        jp12.add(cagnottePlayer);
	        infoPanel.add(jp11);
	        infoPanel.add(jp12);
	        secondRight.add(infoPanel);
	        secondRight.add(customButtonLabel);
	        /*###### End Second JPanel ######*/
	        
	        /*###### Third JPanel ######*/
	        JPanel thirdRight = new JPanel();
	        thirdRight.setBorder(border);
	        abandonButton = new JButton("Abandonner");
	        abandonButton.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		    Tours.getJoueur().setCagnotte(0);
	        		    Tours.avancerCurrentPlayer();
	        		    setCenterPanel(resetPanelButton());
	        		    setCustomButtonLabel(resetPanelButton());
	        	  }
	        });
	        thirdRight.add(abandonButton);
	        
	        bContinue = new JButton("Fin Du Tour");
	        bContinue.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		    Tours.finDuTour();
	        		    setCenterPanel(resetPanelButton());
	        		    setCustomButtonLabel(resetPanelButton());
	        	  }
	        });
	        thirdRight.add(bContinue);
	        /*###### End Third JPanel ######*/
	        
	        gameBoxRight.setLayout(new GridLayout(3, 1));
	        gameBoxRight.add(firstRight);
	        gameBoxRight.add(secondRight);
	        gameBoxRight.add(thirdRight);
        /*########## End Left Box ##########*/
        
	        
        this.getContentPane().add(gameBoxLeft);
        this.getContentPane().add(gameBoxRight);
        this.pack();
        this.setVisible(true);
	}
}
