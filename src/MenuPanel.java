import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class MenuPanel extends JPanel implements ActionListener{
	MiJFrame cont;
	JTextArea mensaje;
	JPanel izq;
	JPanel der;
	JPanel ataques;
	JPanel opciones;
	JButton cancel;
	JButton atacar;
	JButton mochila;
	JButton pokemons;
	JButton huir;
	Queue<String>restantes;
	BotonAtaque uno;
	BotonAtaque dos;
	BotonAtaque tre;
	BotonAtaque cua;
	public MenuPanel(MiJFrame contenedor) {
		cont=contenedor;
		init();
		restantes=new ArrayDeque<>();
	}
	public String getMensaje() {
		return mensaje.getText();
	}
	public void volver() {
		ataques.setVisible(false);
		der.setVisible(false);
		//cancel.setVisible(false);
		
		izq.setVisible(true);
		opciones.setVisible(true);
		
		this.add(izq);
		this.add(opciones);
	}
	public void mostrar(String s) {
		mensaje.setText(s);		
	}
	public void ponerEnCola(String s) {
		restantes.add(s);
	}
	public void mostrarSiguienteMensaje() {
		String s=restantes.poll();
		if(s!=null) {
			mostrar(s);
			if(s.endsWith(" SE HA DEBILITADO")) {
				if(s.startsWith("\n\n "+this.cont.getBattle().getPokemon1().getName().toUpperCase()))this.cont.debilitadoRED();
				else this.cont.debilitadoBLUE();
			}
		}
		else {
			mostrar("\n\n¿QUE DEBERIA HACER "+cont.getBattle().getPokemon1().getName().toUpperCase()+"?");
			this.cont.getBattle().pintaPok();
		}
	}
	public void cambiarAtaques() {
		uno= new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(0));uno.setActionCommand("0");uno.addActionListener(this);
		dos = new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(1));dos.setActionCommand("1");dos.addActionListener(this);
		tre = new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(2));tre.setActionCommand("2");tre.addActionListener(this);
		cua = new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(3));cua.setActionCommand("3");cua.addActionListener(this);
		
		ataques=new JPanel(new GridLayout(2,2));
		ataques.setPreferredSize(new Dimension(420, 74));
		ataques.add(uno);
		ataques.add(dos);
		ataques.add(tre);
		ataques.add(cua);
		
		if(cont.getBattle().getPokemon1().getAttacks().get(0).getPP()<=0)uno.setEnabled(false);
		if(cont.getBattle().getPokemon1().getAttacks().get(1).getPP()<=0)dos.setEnabled(false);
		if(cont.getBattle().getPokemon1().getAttacks().get(2).getPP()<=0)tre.setEnabled(false);
		if(cont.getBattle().getPokemon1().getAttacks().get(3).getPP()<=0)cua.setEnabled(false);
	}
	private void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setSize(620,74);
		this.setBackground(Color.gray);
		this.setOpaque(true);
		
		izq = new JPanel();
		mensaje = new JTextArea();//RED HA SACADO A
		mensaje.setPreferredSize(new Dimension(250, 74));
		mensaje.setMinimumSize(new Dimension(250, 74));
		mensaje.setFocusable(false);
		izq.setAlignmentX(CENTER_ALIGNMENT);
		izq.setAlignmentY(CENTER_ALIGNMENT);
		izq.add(mensaje);
		izq.setBackground(Color.white);
		izq.setPreferredSize(new Dimension(250, 74));
		
		der = new JPanel();
		der.setAlignmentX(CENTER_ALIGNMENT);
		der.setBackground(Color.white);
		der.setLayout(null);
		cancel = new JButton("Salir	");cancel.setBackground(Color.blue);cancel.setForeground(Color.white);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//cont.inicio();
				MenuPanel.this.volver();
			}
		});
		cancel.setBounds(12, 15, 80, 80);
		//cancel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.darkGray, 2), "") );
		der.add(cancel);
//		System.out.println(getHeight());
		

//		uno = new BotonAtaque(new Attack("Escaldar",10,constants.SPECIAL,15,80,100));
//		dos = new BotonAtaque(new Attack("Rayo",12,constants.SPECIAL,15,90,100));
//		tre = new BotonAtaque(new Attack("Pulso Dragon",15,constants.SPECIAL,15,80,100));
//		cua = new BotonAtaque(new Attack("Energibola",11,constants.SPECIAL,10,85,100));
		
//		uno= new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(0));
//		dos = new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(1));
//		tre = new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(2));
//		cua = new BotonAtaque(cont.getBattle().getPokemon1().getAttacks().get(3));
//		ataques=new JPanel(new GridLayout(2,2));
//		ataques.setPreferredSize(new Dimension(420, 74));
//		ataques.add(uno);
//		ataques.add(dos);
//		ataques.add(tre);
//		ataques.add(cua);
		cambiarAtaques();
		
		opciones=new JPanel(new GridLayout(2,2));
		opciones.setPreferredSize(new Dimension(320, 74));
		atacar=new JButton("Lucha");atacar.setBackground(Color.red);atacar.setFont(new Font("Comic Sans MS", 1, 20));atacar.setForeground(Color.white);
		mochila=new JButton("Mochila");mochila.setBackground(Color.decode("#FF8000"));mochila.setFont(new Font("Comic Sans MS", 1, 20));mochila.setForeground(Color.white);
		pokemons=new JButton("Pokemons");pokemons.setBackground(Color.decode("#008000"));pokemons.setFont(new Font("Comic Sans MS", 1, 20));pokemons.setForeground(Color.white);
		huir=new JButton("Huir");huir.setBackground(Color.blue);huir.setFont(new Font("Comic Sans MS", 1, 20));huir.setForeground(Color.white);
		
		atacar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mensaje.getText().startsWith("\n\n¿QUE DEBERIA HACER")) {
					izq.setVisible(false);
					opciones.setVisible(false);
					cambiarAtaques();
					ataques.setVisible(true);
					der.setVisible(true);
					//cancel.setVisible(true);
					MenuPanel.this.add(ataques);
					MenuPanel.this.add(der);
					//MenuPanel.this.add(cancel);
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
					MenuPanel.this.ponerEnCola("\n\n         NO PUEDES HUIR");
					MenuPanel.this.mostrarSiguienteMensaje();
				}
			}
		});
		opciones.add(atacar);
		opciones.add(mochila);
		opciones.add(pokemons);
		opciones.add(huir);
		
		this.add(izq);
		this.add(opciones);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.cont.makeTurn(Integer.parseInt(e.getActionCommand()));
		this.volver();
	}
	public JButton getAtacar() {
		return atacar;
	}
	public JButton getMochila() {
		return mochila;
	}
	public JButton getPokemons() {
		return pokemons;
	}
	public JButton getHuir() {
		return huir;
	}
	
}
