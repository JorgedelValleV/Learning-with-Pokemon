import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Menu extends JPanel{
	MiJFrame cont;
	JTextArea mensaje;
	JTextArea pp;
	JPanel p;
	JPanel p2;
	JPanel ataques;
	JPanel opciones;
	Queue<String>restantes;
	public Menu(MiJFrame contenedor) {
		cont=contenedor;
		init();
		restantes=new ArrayDeque<>();
	}
	public void volver() {
		ataques.setVisible(false);
		p2.setVisible(false);
		p.setVisible(true);
		opciones.setVisible(true);
		this.add(p);
		this.add(opciones);
	}
	public void mostrar(String s) {
		mensaje.setText(s);		
	}
	public void ponerEnCola(String s) {
		restantes.add(s);
	}
	public void mostrarSiguienteMensaje(String act) {
		String s=restantes.poll();
		if(s!=null) {
			mostrar(s);
		}
		else mostrar("\n\n¿QUE DEBERIA HACER "+act+"?");
	}
	private void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(Color.gray);
		this.setOpaque(true);
		
		p = new JPanel();
		mensaje = new JTextArea();//RED HA SACADO A
		mensaje.setPreferredSize(new Dimension(250, 74));
		mensaje.setMinimumSize(new Dimension(250, 74));
		mensaje.setFocusable(false);
		p.setAlignmentX(CENTER_ALIGNMENT);
		p.setAlignmentY(CENTER_ALIGNMENT);
		p.add(mensaje);
		p.setBackground(Color.white);
		p.setPreferredSize(new Dimension(250, 74));
		
		p2 = new JPanel();
		p2.setAlignmentX(CENTER_ALIGNMENT);
		String text = "\n\nPP   20/20\nTIPO  /  Normal\n" ;
		pp= new JTextArea(text);
		pp.setPreferredSize(new Dimension(200, 74));
		pp.setFocusable(false);
		p2.add(pp);
		p2.setBackground(Color.white);
		p2.setPreferredSize(new Dimension(320, 74));
		
		ataques=new JPanel(new GridLayout(2,2));
		//ataques=new JPanel();
		//ataques.setLayout(null);
		ataques.setPreferredSize(new Dimension(420, 74));
		Image i=new ImageIcon("res/tipos/"+"electric"+".png").getImage().getScaledInstance(77, 30, Image.SCALE_SMOOTH);
		JButton uno = new JButton("Escaldar");uno.setIcon(new ImageIcon("res/tipos/"+"agua"+".png"));//uno.setFont(new Font("Comic Sans MS", 1, 20));
		JButton dos = new JButton("Rayo");dos.setIcon(new ImageIcon(i));//dos.setFont(new Font("Comic Sans MS", 1, 20));
		JButton tre = new JButton("Pulso Dragon");tre.setIcon(new ImageIcon("res/tipos/"+"dragon"+".png"));//tre.setFont(new Font("Comic Sans MS", 1, 20));
		JButton cua = new JButton("Energibola");cua.setIcon(new ImageIcon("res/tipos/"+"planta"+".png"));//cua.setFont(new Font("Comic Sans MS", 1, 20));
		ataques.add(uno);
		ataques.add(dos);
		ataques.add(tre);
		ataques.add(cua);
		
		opciones=new JPanel(new GridLayout(2,2));
		opciones.setPreferredSize(new Dimension(320, 74));
		JButton atacar=new JButton("Lucha");atacar.setBackground(Color.red);atacar.setFont(new Font("Comic Sans MS", 1, 20));atacar.setForeground(Color.white);
		JButton mochila=new JButton("Mochila");mochila.setBackground(Color.decode("#FF8000"));mochila.setFont(new Font("Comic Sans MS", 1, 20));mochila.setForeground(Color.white);
		JButton pokemons=new JButton("Pokemons");pokemons.setBackground(Color.decode("#008000"));pokemons.setFont(new Font("Comic Sans MS", 1, 20));pokemons.setForeground(Color.white);
		JButton huir=new JButton("Huir");huir.setBackground(Color.blue);huir.setFont(new Font("Comic Sans MS", 1, 20));huir.setForeground(Color.white);
		
		atacar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mensaje.getText().startsWith("\n\n¿QUE DEBERIA HACER")) {
					p.setVisible(false);
					opciones.setVisible(false);
					ataques.setVisible(true);
					p2.setVisible(true);
					Menu.this.add(ataques);
					Menu.this.add(p2);
				}
			}
			
		});
		mochila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mensaje.getText().startsWith("\n\n¿QUE DEBERIA HACER")) {
					cont.abrirMochila();
				}
			}
			
		});
		pokemons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mensaje.getText().startsWith("\n\n¿QUE DEBERIA HACER")) {
					cont.abrirPokemon();
				}
			}
			
		});
		huir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(mensaje.getText().startsWith("\n\n¿QUE DEBERIA HACER")) {
					Menu.this.ponerEnCola("\n\n         NO PUEDES HUIR");
				}
			}
		});
		opciones.add(atacar);
		opciones.add(mochila);
		opciones.add(pokemons);
		opciones.add(huir);
		
		this.add(p);
		this.add(opciones);
	}
}
