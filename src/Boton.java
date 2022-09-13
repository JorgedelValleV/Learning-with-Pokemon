

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;



public class Boton extends JButton{
	//Para usarlo: new Boton(texto) o new Boton(texto, imagen)
	//setBackColor(Color rgb) y setTextColor(Color) cambian los colores de fondo y texto

	private Color backColor = Color.WHITE;
	private Color textColor = new Color(20,88,72),
			borderColor = new Color(20,88,72);
	private boolean mouseOver = false, border = false;
	
	public Boton(String s) {
		super(s);
		Font fuente = new Font("SansSerif", 1, 20);
		setFont(fuente);
		setForeground(textColor);
		setBorderPainted(false); 
		setContentAreaFilled(false);
		setFocusable(true);
		this.setFocusPainted(false);
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
		addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				mouseOver = true;
            	repaint();
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				mouseOver = false;
            	repaint();
			}
			
		});
	}
	
	public Boton(String s, String imagen) {
		super(s);
		setIcon(new ImageIcon(imagen));
		setBorderPainted(false); 
		setContentAreaFilled(false);
		setFocusable(false);
	}
	public void setTam(int n) {
		Font fuente = new Font("SansSerif", 1, n);
		setFont(fuente);
	}
	public void setBackColor(Color c) {
		backColor = c;
	}

	public void setTextColor(Color c) {
		textColor = c;
	}
	
	public void setBorde(Color c) {
		border = true;
		if(c != null) {
			borderColor=c;
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(backColor);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		if(mouseOver) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.setColor(borderColor);
			g2.drawRoundRect(1, 2, getWidth()-2, getHeight()-4, 15, 15);
		}
		if(border) {
			g.setColor(borderColor);
			g.drawRoundRect(0, 1, getWidth()-1, getHeight()-2, 15, 15);
		}
		super.paint(g);
	}
}
