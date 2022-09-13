import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class CambioPokemon extends JPanel implements ActionListener{
		JTextArea mensaje;
		JPanel mainPanel;
		JButton cancel;
		List<String> poks;
		MiJFrame cont;

		public CambioPokemon(MiJFrame contenedor, List<String> poks) {
			cont=contenedor;
			this.poks=poks;
			initGUI();
		}

		private void initGUI() {
			mainPanel = new JPanel(new GridLayout(5,5));
			JPanel abajo= new JPanel();
			abajo.setLayout(new FlowLayout());

			
			JButton uno=new JButton();uno.setText(poks.get(0));uno.setBackground(Color.cyan);uno.setForeground(Color.black);uno.addActionListener(this);uno.setFont(new Font("Comic Sans MS", 1, 20));uno.setIcon(new ImageIcon("res/pokemon/"+poks.get(0)+".png"));uno.setActionCommand("0");uno.setRolloverIcon(new ImageIcon("res/pokemon/"+poks.get(0)+"_shiny.png"));uno.setContentAreaFilled(false);//uno.setBorder(new Border(Color.red));//uno.setBorderPainted(true);// 9 124 311 93
			JButton dos=new JButton();dos.setText(poks.get(1));dos.setBackground(Color.blue);dos.setForeground(Color.white);dos.addActionListener(this);dos.setFont(new Font("Comic Sans MS", 1, 20));dos.setIcon(new ImageIcon("res/pokemon/"+poks.get(1)+".png"));dos.setActionCommand("1");dos.setRolloverIcon(new ImageIcon("res/pokemon/"+poks.get(1)+"_shiny.png"));// 327 31 311 93
			JButton tres=new JButton();tres.setText(poks.get(2));tres.setBackground(Color.blue);tres.setForeground(Color.white);tres.addActionListener(this);tres.setFont(new Font("Comic Sans MS", 1, 20));tres.setIcon(new ImageIcon("res/pokemon/"+poks.get(2)+".png"));tres.setActionCommand("2");tres.setRolloverIcon(new ImageIcon("res/pokemon/"+poks.get(2)+"_shiny.png"));// 327 124 311 93
			JButton cuatro=new JButton();cuatro.setText(poks.get(3));cuatro.setBackground(Color.blue);cuatro.setForeground(Color.white);cuatro.addActionListener(this);cuatro.setFont(new Font("Comic Sans MS", 1, 20));cuatro.setIcon(new ImageIcon("res/pokemon/"+poks.get(3)+".png"));cuatro.setActionCommand("3");cuatro.setRolloverIcon(new ImageIcon("res/pokemon/"+poks.get(3)+"_shiny.png"));// 327 217 311 93
			JButton cinco=new JButton();cinco.setText(poks.get(4));cinco.setBackground(Color.blue);cinco.setForeground(Color.white);cinco.addActionListener(this);cinco.setFont(new Font("Comic Sans MS", 1, 20));cinco.setIcon(new ImageIcon("res/pokemon/"+poks.get(4)+".png"));cinco.setActionCommand("4");cinco.setRolloverIcon(new ImageIcon("res/pokemon/"+poks.get(4)+"_shiny.png"));// 327 310 311 93
			JButton seis=new JButton();seis.setText(poks.get(5));seis.setBackground(Color.blue);seis.setForeground(Color.white);seis.addActionListener(this);seis.setFont(new Font("Comic Sans MS", 1, 20));seis.setIcon(new ImageIcon("res/pokemon/"+poks.get(5)+".png"));seis.setActionCommand("5");seis.setRolloverIcon(new ImageIcon("res/pokemon/"+poks.get(5)+"_shiny.png"));// 327 403 311 93
			
			mainPanel.add(new JLabel(""));mainPanel.add(dos);
			mainPanel.add(uno);mainPanel.add(tres);
			mainPanel.add(new BotonPokemon());mainPanel.add(cuatro);
			mainPanel.add(new JLabel(""));mainPanel.add(cinco);
			mainPanel.add(new JLabel(""));mainPanel.add(seis);
			mainPanel.setBackground(Color.decode("#00800F"));
			mainPanel.setPreferredSize(new Dimension(633, 450));
			
			
			mensaje = new JTextArea ("\n      Elige un pokemon");
			mensaje.setFocusable(false);
			mensaje.setAlignmentY(CENTER_ALIGNMENT);
			mensaje.setPreferredSize(new Dimension(475, 83));
			mensaje.setFont(new Font("Comic Sans MS", 1, 20));
			cancel = new JButton("Salir");cancel.setBackground(Color.red);cancel.setForeground(Color.white);
			cancel.setMinimumSize(new Dimension(147, 83));
			cancel.setPreferredSize(new Dimension(147, 83));
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//CambioPokemon.this.setVisible(false);
					cont.inicio();
				}
			});
			
			abajo.setBackground(Color.decode("#00800F"));
			abajo.add(mensaje);
			abajo.add(cancel);
			
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.add(mainPanel);
			this.add(abajo);

			this.setBackground(Color.green);
			this.setOpaque(false);
			this.setSize(640, 574);//640 574 
			this.setPreferredSize(new Dimension(623, 533));//640 540
			this.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			cont.cambiarPokemon(e.getActionCommand(),cancel.isEnabled());
		}
}
