import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class ChangePokemon extends JPanel implements ActionListener{
	JTextArea mensaje;
	JPanel mainPanel;
	JButton cancel;
	List<Pokemon> poks;
	MiJFrame cont;
	
	BotonPokemon uno;
	BotonPokemon dos;
	BotonPokemon tre;
	BotonPokemon cua;
	BotonPokemon cin;
	BotonPokemon sei;
	String act;
	public ChangePokemon(MiJFrame contenedor, List<Pokemon> poks) {
		cont=contenedor;
		this.poks=poks;
		act="0";
		initGUI();
	}
	public void cambiaBotones() {
		BotonPokemon unoa= new BotonPokemon(poks.get(0));unoa.setBounds(uno.getBounds());unoa.setActionCommand("0");unoa.addActionListener(this);unoa.setEnabled(uno.isEnabled());uno.setVisible(false);uno=unoa;add(uno);
		BotonPokemon dosa= new BotonPokemon(poks.get(1));dosa.setBounds(dos.getBounds());dosa.setActionCommand("1");dosa.addActionListener(this);dosa.setEnabled(dos.isEnabled());dos.setVisible(false);dos=dosa;add(dos);
		BotonPokemon trea= new BotonPokemon(poks.get(2));trea.setBounds(tre.getBounds());trea.setActionCommand("2");trea.addActionListener(this);trea.setEnabled(tre.isEnabled());tre.setVisible(false);tre=trea;add(tre);
		BotonPokemon cuaa= new BotonPokemon(poks.get(3));cuaa.setBounds(cua.getBounds());cuaa.setActionCommand("3");cuaa.addActionListener(this);cuaa.setEnabled(cua.isEnabled());cua.setVisible(false);cua=cuaa;add(cua);
		BotonPokemon cina= new BotonPokemon(poks.get(4));cina.setBounds(cin.getBounds());cina.setActionCommand("4");cina.addActionListener(this);cina.setEnabled(cin.isEnabled());cin.setVisible(false);cin=cina;add(cin);
		BotonPokemon seia= new BotonPokemon(poks.get(5));seia.setBounds(sei.getBounds());seia.setActionCommand("5");seia.addActionListener(this);seia.setEnabled(sei.isEnabled());sei.setVisible(false);sei=seia;add(sei);
	}
	private void initGUI() {
		this.setLayout(null);
		this.setBackground(Color.decode("#827bdb"));
		this.setOpaque(true);
		this.setVisible(true);
		
		mensaje = new JTextArea ("\n      Elige un pokemon");
		mensaje.setFocusable(false);
		mensaje.setAlignmentY(CENTER_ALIGNMENT);
		//mensaje.setPreferredSize(new Dimension(475, 83));
		mensaje.setFont(new Font("Comic Sans MS", 1, 20));
		mensaje.setBounds(10, 430, 475, 83);
		mensaje.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "",TitledBorder.LEFT, TitledBorder.TOP) );
		add(mensaje);
		
		cancel = new JButton("Salir");cancel.setBackground(Color.red);cancel.setForeground(Color.white);
		//cancel.setMinimumSize(new Dimension(147, 83));
		//cancel.setPreferredSize(new Dimension(147, 83));
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//CambioPokemon.this.setVisible(false);
				cont.inicio();
			}
		});
		cancel.setBounds(490, 430, 130, 83);
		add(cancel);
		
		//FORMA ESMERALDA
//		BotonPokemon uno= new BotonPokemon(poks.get(0));uno.setBounds(0, 93, 311, 93);add(uno);
//		BotonPokemon dos= new BotonPokemon(poks.get(1));dos.setBounds(313, 0, 311, 93);add(dos);
//		BotonPokemon tre= new BotonPokemon(poks.get(2));tre.setBounds(313, 93, 311, 93);add(tre);
//		BotonPokemon cua= new BotonPokemon(poks.get(3));cua.setBounds(313, 186, 311, 93);add(cua);
//		BotonPokemon cin= new BotonPokemon(poks.get(4));cin.setBounds(313, 279, 311, 93);add(cin);
//		BotonPokemon sei= new BotonPokemon(poks.get(5));sei.setBounds(313, 372, 311, 93);add(sei);
		
		//FORMA DIAMANTE
		uno= new BotonPokemon(poks.get(0));uno.setBounds(0, 10, 311, 110);add(uno);uno.addActionListener(this);uno.setActionCommand("0");uno.setEnabled(false);
		dos= new BotonPokemon(poks.get(1));dos.setBounds(313, 20, 311, 110);add(dos);dos.addActionListener(this);dos.setActionCommand("1");
		tre= new BotonPokemon(poks.get(2));tre.setBounds(0, 130, 311, 110);add(tre);tre.addActionListener(this);tre.setActionCommand("2");
		cua= new BotonPokemon(poks.get(3));cua.setBounds(313, 140, 311, 110);add(cua);cua.addActionListener(this);cua.setActionCommand("3");
		cin= new BotonPokemon(poks.get(4));cin.setBounds(0, 250, 311, 110);add(cin);cin.addActionListener(this);cin.setActionCommand("4");
		sei= new BotonPokemon(poks.get(5));sei.setBounds(313, 260, 311, 110);add(sei);sei.addActionListener(this);sei.setActionCommand("5");
	}
	public void desabilitarCancel() {
		cancel.setEnabled(false);		
	}
	public void desabilitarAct() {
		switch(act) {
		case "0":uno.setEnabled(false);break;
		case "1":dos.setEnabled(false);break;
		case "2":tre.setEnabled(false);break;
		case "3":cua.setEnabled(false);break;
		case "4":cin.setEnabled(false);break;
		case "5":sei.setEnabled(false);break;
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		cont.cambiarPokemon(e.getActionCommand(),cancel.isEnabled());
		cancel.setEnabled(true);
		
		int x=0;int y=0; int width=311; int height=110;
		
		switch(e.getActionCommand()) {
		case "0":{
			x=(int)uno.getBounds().getX();
			y=(int)uno.getBounds().getY();
			width=(int)uno.getBounds().getWidth();
			height=(int)uno.getBounds().getHeight();
			uno.setBounds(0,0,311,110);
			uno.setEnabled(false);
		}break;
		case "1":{
			x=(int)dos.getBounds().getX();
			y=(int)dos.getBounds().getY();
			width=(int)dos.getBounds().getWidth();
			height=(int)dos.getBounds().getHeight();			
			dos.setBounds(0,0,311,110);
			dos.setEnabled(false);
		}break;
		case "2":{
			x=(int)tre.getBounds().getX();
			y=(int)tre.getBounds().getY();
			width=(int)tre.getBounds().getWidth();
			height=(int)tre.getBounds().getHeight();			
			tre.setBounds(0,0,311,110);
			tre.setEnabled(false);
		}break;
		case "3":{
			x=(int)cua.getBounds().getX();
			y=(int)cua.getBounds().getY();
			width=(int)cua.getBounds().getWidth();
			height=(int)cua.getBounds().getHeight();			
			cua.setBounds(0,0,311,110);
			cua.setEnabled(false);
		}break;
		case "4":{
			x=(int)cin.getBounds().getX();
			y=(int)cin.getBounds().getY();
			width=(int)cin.getBounds().getWidth();
			height=(int)cin.getBounds().getHeight();
			cin.setBounds(0,0,311,110);
			cin.setEnabled(false);
		}break;
		case "5":{
			x=(int)sei.getBounds().getX();
			y=(int)sei.getBounds().getY();
			width=(int)sei.getBounds().getWidth();
			height=(int)sei.getBounds().getHeight();
			sei.setBounds(0,0,311,110);
			sei.setEnabled(false);
		}break;
		}
		switch(act) {
		case "0":uno.setBounds(x,y,width,height);uno.setEnabled(true);break;
		case "1":dos.setBounds(x,y,width,height);dos.setEnabled(true);break;
		case "2":tre.setBounds(x,y,width,height);tre.setEnabled(true);break;
		case "3":cua.setBounds(x,y,width,height);cua.setEnabled(true);break;
		case "4":cin.setBounds(x,y,width,height);cin.setEnabled(true);break;
		case "5":sei.setBounds(x,y,width,height);sei.setEnabled(true);break;
		}
		act=e.getActionCommand();
		
	}
}
