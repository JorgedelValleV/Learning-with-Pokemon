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

public class BotonAtaque extends JButton {

	private boolean mouseOver = false;

	private String name = "Placaje",
			ppmax = "20",
			ppact = "20",
			img ="normal";
	private int t = 0;

	public BotonAtaque() {
		init();
	}
	public BotonAtaque(Attack a) {
		name = a.getName();
		ppmax = Integer.toString(a.getmaxPP());
		ppact = Integer.toString(a.getPP());
		t=a.getType();
		img = constants.TYPES[t].toLowerCase();
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
		this.setPreferredSize(new Dimension(260, 50));
		this.setLayout(new GridLayout(2, 1));
		this.setBackground(Color.orange);
		this.setOpaque(false);////////////////////////////////////////////
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1));
		panel1.setOpaque(false);
		Text nivel = new Text(name,SwingConstants.CENTER);
		nivel.setSize(18);
		nivel.setTextColor(Color.black);
		panel1.add(nivel);
		this.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 3));
		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		panel2.add(panel3);
		Text nombre = new Text("PP",SwingConstants.CENTER);
		nombre.setSize(18);
		nombre.setTextColor(Color.black);
		panel2.add(nombre);
		Text rest = new Text(ppact+"/"+ppmax,SwingConstants.CENTER);
		rest.setSize(18);
		rest.setTextColor(Color.black);
		panel2.add(rest);
		panel2.setOpaque(false);
		add(panel2);

	}

	public void paint(Graphics g) {

		//Color borderColor = new Color(20, 88, 72);
		Color borderColor = constants.colorillo[t];
		Color fondocolor = new Color(237, 237, 174);
		
		g.setColor(fondocolor);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		//g.fillRect(0, 0, getWidth(), getHeight());
		
		Image image = new ImageIcon("res/tipos/"+img+".png").getImage();//img
		g.drawImage(image, 5, 23, 70, 28, this);     
		
		if (mouseOver) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(borderColor);
			g2.drawRoundRect(1, 2, getWidth() - 2, getHeight() - 4, 15, 15);
		}
		
		g.setColor(borderColor);
		g.drawRoundRect(0, 1, getWidth() - 1, getHeight() - 2, 15, 15);

		super.paint(g);
	}
}