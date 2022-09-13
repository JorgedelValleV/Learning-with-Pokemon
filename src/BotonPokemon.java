import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class BotonPokemon extends JButton {

	private boolean mouseOver = false;

	private String name = "Blastoise",
			lifemax = "289", 
			currentlife = "289", 
			level = "100",
			img ="blastoise",
			fondo="res/fondito.png";

	public BotonPokemon() {
		init();
	}

	public BotonPokemon(Pokemon p) {
		name = p.getName();
		lifemax = Integer.toString(p.getStats().get(constants.HP));
		currentlife = Integer.toString((int)p.getCurrent_hp());
		img = name.toLowerCase();
		init();
	}

	private void init() {
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusable(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mouseOver = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mouseOver = false;
				repaint();
			}
		});
		this.setPreferredSize(new Dimension(311, 93));
		this.setLayout(new GridLayout(1, 2));
		this.setBackground(Color.gray);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 1));
		panel1.setOpaque(false);
		JPanel panel4 = new JPanel();
		panel4.setOpaque(false);
		JPanel panel6 = new JPanel();
		panel6.setOpaque(false);
		panel1.add(panel4);
		panel1.add(panel6);
		Text nivel = new Text("Lv."+level);
		nivel.setSize(18);
		nivel.setTextColor(Color.white);
		panel1.add(nivel);
		this.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3, 1));
		Text nombre = new Text(name.toUpperCase());
		nombre.setSize(18);
		nombre.setTextColor(Color.white);
		panel2.add(nombre);
		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		panel2.add(panel3);
		Text vida = new Text(currentlife+"/"+lifemax,SwingConstants.CENTER);
		vida.setSize(18);
		vida.setTextColor(Color.white);
		panel2.add(vida);
		panel2.setOpaque(false);
		JPanel panel5 = new JPanel();
		panel5.setOpaque(false);
		add(panel2);

	}

	public void paint(Graphics g) {

		Color borderColor = new Color(20, 88, 72);
		
		if(Integer.parseInt(currentlife)>0) {
			Image fon = new ImageIcon(fondo).getImage();//img
			g.drawImage(fon, 0,0,getWidth(), getHeight(), this);
		}
		else {
			g.setColor(Color.red);
			g.fillRoundRect(0, 1, getWidth() - 1, getHeight() - 2, 15, 15);
		}
		
		
		if (mouseOver) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.red);
			g2.drawRoundRect(1, 2, getWidth() - 2, getHeight() - 4, 15, 15);
		}
		// */
		g.setColor(Color.red);
		g.drawRoundRect(0, 1, getWidth() - 1, getHeight() - 2, 15, 15);
		
		
		Image image = new ImageIcon("res/pokemon/"+ img +".png").getImage();//img
		g.drawImage(image, 20, -10	, 100, 100, this);
			
		float f= (float)(Integer.parseInt(currentlife))/(float)(Integer.parseInt(lifemax));
		int t = (int) (150*f);
		Color color = Color.green;
        if (f < 0.5)
            color = Color.orange;
        if (f < 0.15)
            color = Color.red;
		g.setColor(color);
		g.fillRect(150, 44,t , 20);
        g.setColor(Color.black);
        g.fillRect(120, 44,30 , 20);
        g.setColor(Color.red);
        g.drawString("HP", 129, 58);
        

		super.paint(g);
	}
}