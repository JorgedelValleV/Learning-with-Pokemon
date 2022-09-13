import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

public class EjemploTeclado extends JComponent {
	
	String pok1;
	String pok2;

	public EjemploTeclado(String pok1,String pok2) {
		this.pok1=pok1;
		this.pok2=pok2;
	}
	
	public void setPok1(String pok1) {
		this.pok1 = pok1;
		repaint();
	}
	public void setPok2(String pok2) {
		this.pok2 = pok2;
		repaint();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		g.setFont(new Font("Comic Sans MS", 1, 20));
		g.drawImage(new ImageIcon("res/battle_bg/battle_bg_2.png").getImage(), 0, 0, getWidth(), getHeight(), this);
    	
    	g.drawString(pok2, 20, 40);
    	g.drawString("Lv:"+100, 225, 40);
    	g.drawString(289+"/"+289, 130, 110);
		g.drawImage(new ImageIcon("res/gui/life_bar_enemy.png").getImage(), 20,50,272,75, this);
		g.drawImage(new ImageIcon("res/pokemon/"+pok2.toLowerCase()+".png").getImage(), 335,42,275 ,275, this);
		
    	g.drawString(pok1, 343, 348);//330 338
    	g.drawString("Lv:"+100, 535, 348);//530 338
    	g.drawString(289+"/"+289, 435, 410);
		g.drawImage(new ImageIcon("res/gui/life_bar_player.png").getImage(), 315,353,300,80 ,this);
		g.drawImage(new ImageIcon("res/pokemon/"+pok1.toLowerCase()+"_back.png").getImage(), 0,220,340 ,330,this);
		
		g.setColor(Color.green);
        g.fillRect(103, 57, 170, 20);
        g.setColor(Color.GREEN);
        g.fillRect(406, 352, 180, 17);
        g.setColor(Color.black);
	}
}