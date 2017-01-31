package Monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Case extends JPanel{
    //private String name;
    private int position;
    private Color defaultBackground = null;
    private DrawPanel drawPion;
    protected JPanel colorPanel;
    
    
    public Case(int position){
        this.position = 0; 
        this.setBackground(Window.getMainBackgroundColor());
        this.setLayout(new FlowLayout());
        colorPanel = new JPanel();
        colorPanel.setBackground(defaultBackground);
        colorPanel.setPreferredSize(new Dimension(54, 18));
        this.add(colorPanel);
        drawPion = new DrawPanel();
        drawPion.setPreferredSize(new Dimension(54, 27));
        this.add(drawPion);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JPanel infoPanel = new JPanel();
                infoPanel.add(new JLabel("Case n°"+position));
                infoPanel.setBackground(new Color(240, 248, 255));
                Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
            	infoPanel.setBorder(border);
                Window.setCenterPanel(infoPanel);    
              }
            
            public void mouseEntered(MouseEvent e) {
            	Border border = new MatteBorder(2, 2, 2, 2, Color.RED);
    	        setBorder(border);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	Border border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
    	        setBorder(border);
            }
        });
    }
    
    
    public void onRemovePoint(Joueur joueur){
    	drawPion.removeJoueur(joueur);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(58, 58);
    }
    
    
    public String toString(){
        return position+" ";
    }
    
    public void onThis (Joueur joueur){
        drawPion.addJoueur(joueur);
        drawPion.repaint();
        drawPion.revalidate();
    }
    
    
    public void setDefaultBackground(Color newColor){
    	defaultBackground = newColor;
    	colorPanel.setBackground(defaultBackground);
    	colorPanel.repaint();
    	colorPanel.revalidate();
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
}
