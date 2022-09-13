

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Pantalla_LogIn extends JPanel implements FocusListener, KeyListener{
	
	private Text error;
	private MiJFrame cont;
	private JSONObject jo;
	private List<HintTextField> lista;
//	private List<JTextArea> adelanto;
	private List<JScrollPane> advise;
	private List<DefaultListModel<String>> modelillos;
	private List<JList<String>> listitas;
	private List<String> list;	
	private Set<String> listilla;	
//	private DefaultListModel<String> modelo;
//	private JList<String> lstLista ;
//	private JScrollPane scb;
	private JPopupMenu menuPopup1; 
	private JButton iniciar;
	private ArrayList<Pantalla_A> atatata;
	private int a;
	public Pantalla_LogIn(MiJFrame ctrl) {
		cont=ctrl;
		a =-1;
		lista=new ArrayList<HintTextField>();
//		adelanto=new ArrayList<JTextArea>();
		advise=new ArrayList<JScrollPane>();
		modelillos = new ArrayList<DefaultListModel<String>>();
		listitas = new ArrayList<JList<String>>();
		atatata = new ArrayList<Pantalla_A>();
		initGui();
		try {
			jo = new JSONObject(new JSONTokener(new FileInputStream(new File("res/db/pokemons.txt"))));
		} catch (JSONException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listilla=jo.keySet();
	}

	protected void initGui() {
		// TODO Auto-generated method stub
		this.setLayout(null);

		error = new Text("");
		error.setSize(15);
		error.setTextColor(Color.RED);
		error.setBounds(360, 470, 273, 51);//20
		this.add(error);
		
		
		for(int i=0;i<6;++i) {
			Pantalla_A pa=new Pantalla_A(cont);
			pa.setVisible(false);
			pa.setBounds(10, 60*(i+1), 300, 200);
			this.add(pa);
			atatata.add(pa);
		}
		
		menuPopup1 = new JPopupMenu();
		JMenuItem menuItemCortar = new JMenuItem("Ataques");
		menuItemCortar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//hacerVisible seleccion de ataques
				for(int i =0;i<6;++i) {
					//if(lista.get(i).hasFocus()) {
					if(i==a) {
						atatata.get(i).setVisible(true);
						atatata.get(i).requestFocus();
					}
					else 
						atatata.get(i).setVisible(false);
				}
			}
		});
		menuPopup1.add(menuItemCortar);
		MiPopup mi=new MiPopup();
		
//		JTextArea uno=new JTextArea();uno.setVisible(false);uno.setBounds(130, 60, 180, 100);this.add(uno);adelanto.add(uno);
//		JTextArea dos=new JTextArea();dos.setVisible(false);dos.setBounds(130, 120, 180, 100);this.add(dos);adelanto.add(dos);
//		JTextArea tre=new JTextArea();tre.setVisible(false);tre.setBounds(130, 180, 180, 100);this.add(tre);adelanto.add(tre);
//		JTextArea cua=new JTextArea();cua.setVisible(false);cua.setBounds(130, 240, 180, 100);this.add(cua);adelanto.add(cua);
//		JTextArea cin=new JTextArea();cin.setVisible(false);cin.setBounds(130, 300, 180, 100);this.add(cin);adelanto.add(cin);
//		JTextArea sei=new JTextArea();sei.setVisible(false);sei.setBounds(130, 360, 180, 100);this.add(sei);adelanto.add(sei);
		
		
//		modelo = new DefaultListModel<String>();
//		lstLista = new JList<String>(modelo);
//		lstLista.addListSelectionListener(new MiList());
//		scb= new JScrollPane(lstLista);
//		scb.setBounds(130, 60, 180, 100);
//		scb.setVisible(false);
//		this.add(scb);
		
		for(int i=0;i<6;++i) {
			DefaultListModel<String> modelo = new DefaultListModel<String>();
			JList<String> lstLista = new JList<String>(modelo);
			lstLista.addListSelectionListener(new MiList());
			JScrollPane scb= new JScrollPane(lstLista);
			scb.setBounds(130, 60*(i+1), 180, 100);
			scb.setVisible(false);
			this.add(scb);
			advise.add(scb);
			modelillos.add(modelo);
			listitas.add(lstLista);
		}
		
		HintTextField pokemon1 = new HintTextField("Primer Pokemon");
		pokemon1.setBounds(330, 60, 273, 52);//53
		pokemon1.setHorizontalAlignment(JTextField.CENTER);
		this.add(pokemon1);
		pokemon1.addFocusListener(this);
		pokemon1.addKeyListener(this);
		pokemon1.addMouseListener(mi);

		HintTextField pokemon2 = new HintTextField("Segundo Pokemon");
		pokemon2.setBounds(330, 120, 273, 52);
		pokemon2.setHorizontalAlignment(JTextField.CENTER);
		this.add(pokemon2);
		pokemon2.addFocusListener(this);
		pokemon2.addKeyListener(this);
		pokemon2.addMouseListener(mi);
		
		HintTextField pokemon3 = new HintTextField("Tercer Pokemon");
		pokemon3.setBounds(330, 180, 273, 52);
		pokemon3.setHorizontalAlignment(JTextField.CENTER);
		this.add(pokemon3);
		pokemon3.addFocusListener(this);
		pokemon3.addKeyListener(this);
		pokemon3.addMouseListener(mi);
		
		HintTextField pokemon4 = new HintTextField("Cuarto Pokemon");
		pokemon4.setBounds(330, 240, 273, 52);
		pokemon4.setHorizontalAlignment(JTextField.CENTER);
		this.add(pokemon4);
		pokemon4.addFocusListener(this);
		pokemon4.addKeyListener(this);
		pokemon4.addMouseListener(mi);
		
		HintTextField pokemon5 = new HintTextField("Quinto Pokemon");
		pokemon5.setBounds(330, 300, 273, 52);
		pokemon5.setHorizontalAlignment(JTextField.CENTER);
		this.add(pokemon5);
		pokemon5.addFocusListener(this);
		pokemon5.addKeyListener(this);
		pokemon5.addMouseListener(mi);
		
		HintTextField pokemon6 = new HintTextField("Sexto Pokemon");
		pokemon6.setBounds(330, 360, 273, 52);
		pokemon6.setHorizontalAlignment(JTextField.CENTER);
		this.add(pokemon6);
		pokemon6.addFocusListener(this);
		pokemon6.addKeyListener(this);
		pokemon6.addMouseListener(mi);

		lista.add(pokemon1);lista.add(pokemon2);lista.add(pokemon3);lista.add(pokemon4);lista.add(pokemon5);lista.add(pokemon6);

		iniciar = new Boton("Let's Fight");
		iniciar.setBounds(330, 420, 274, 52);
		this.add(iniciar);

		iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				boolean sePuede=true;
				list=new ArrayList<String>();
				for(int i=0;i<6;++i) {
					lista.get(i).displayError(false);
					list.add(lista.get(i).getText());
					if(!jo.has(lista.get(i).getText())) {
						error.setText("Hay un pokemon que no existe");
						lista.get(i).displayError(true);
						sePuede=false;
					}
				}
				if(sePuede) {
					try {
						Pantalla_LogIn.this.setVisible(false);
						cont.setRedPok(list);
						cont.inicio();
					} catch (Exception e) {
						error.setText(e.getMessage());
					}
				}
				
//				pokemon1.displayError(false);
//				pokemon2.displayError(false);
//				if (pokemon1.getText().equals("") || pokemon2.getText().equals("")) {
//					error.setText("Hay un pokemon que no existe");
//					if (pokemon1.getText().equals(""))
//						pokemon1.displayError(true);
//					if (pokemon2.getText().equals(""))
//						pokemon2.displayError(true);
//					if (pokemon3.getText().equals(""))
//						pokemon3.displayError(true);
//					if (pokemon4.getText().equals(""))
//						pokemon4.displayError(true);
//					if (pokemon5.getText().equals(""))
//						pokemon5.displayError(true);
//					if (pokemon6.getText().equals(""))
//						pokemon6.displayError(true);
//				} else
//					try {
//						cont.inicio();
//					} catch (Exception e) {
//						error.setText(e.getMessage());
//					}
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		//ImageIcon image = new ImageIcon(("res/bruno.png"));
		ImageIcon image = new ImageIcon(("res/f.jpg"));
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, getWidth(), 0);
		//g.drawImage(image.getImage(), 400, 120, 200, 360,this);
		g.drawImage(image.getImage(), 0, 0, getWidth(),getHeight(),this);
		this.setOpaque(false);
		super.paint(g);
	}

	public void focusGained(FocusEvent e) {
//		if(e.getSource()==lista.get(0))scb.setVisible(true);
//		for(int i =0;i<6;++i) {
//			if(e.getSource()==lista.get(i))adelanto.get(i).setVisible(true);
//		}
		for(int i =0;i<6;++i) {
			if(e.getSource()==lista.get(i)) {
				advise.get(i).setVisible(true);
			}
			else atatata.get(i).setVisible(false);
		}
	}

	public void focusLost(FocusEvent e) {
//		if(e.getSource()==lista.get(0))scb.setVisible(true);
//		for(int i =0;i<6;++i) {
//			if(e.getSource()==lista.get(i))adelanto.get(i).setVisible(false);
//		}
		for(int i =0;i<6;++i) {
			if(e.getSource()==lista.get(i)) {
				advise.get(i).setVisible(false);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
//		if(KeyEvent.VK_ENTER!=e.getExtendedKeyCode()) {
//			for(int i =0;i<6;++i) {
//				if(e.getSource()==lista.get(i)) {
//					funcioncilla(lista.get(i),modelillos.get(i));
//				}
//			}
//		}
//		if(KeyEvent.VK_ENTER==e.getExtendedKeyCode()) {
//			for(int i =0;i<6;++i) {
//				if(e.getSource()==lista.get(i))
//					if(listitas.get(i).getSelectedIndex()!=0)listitas.get(i).setSelectedIndex(0);
//			}
//		}
			
	}

	public void keyReleased(KeyEvent e) {
//		if(e.getSource()==lista.get(0))funcion(lista.get(0),adelanto.get(0));
//		for(int i =1;i<6;++i) {
//			if(e.getSource()==lista.get(i))funcion(lista.get(i),adelanto.get(i));
//		}
		if(KeyEvent.VK_ENTER==e.getExtendedKeyCode()) {
			for(int i =0;i<6;++i) {
				if(e.getSource()==lista.get(i))
					if(listitas.get(i).getSelectedIndex()!=0) {
						listitas.get(i).setSelectedIndex(0);
						if(i<5)
							lista.get(i+1).requestFocus();
						else {
							//this.requestFocus();
							cont.getRootPane().setDefaultButton(iniciar);
							iniciar.requestFocus();
						}
					}
			}
		}
		else {
			for(int i =0;i<6;++i) {
				if(e.getSource()==lista.get(i)) {
					funcioncilla(lista.get(i),modelillos.get(i));
				}
			}
		}
	}
	public void keyTyped(KeyEvent e) {
//		if(KeyEvent.VK_ENTER!=e.getExtendedKeyCode()) {
//			for(int i =0;i<6;++i) {
//				if(e.getSource()==lista.get(i)) {
//					funcioncilla(lista.get(i),modelillos.get(i));
//				}
//			}
//		}
//		for(int i =0;i<6;++i) {
//			if(e.getSource()==lista.get(i)) {
//				funcioncilla(lista.get(i),modelillos.get(i));
//			}
//		}
	}
	private void funcioncilla(HintTextField input, DefaultListModel<String> m) {
		m.removeAllElements();
		String s = input.getText();
		for(String str:jo.keySet()) {
			boolean Empiezaigual=true;
			if(s.isEmpty()||s.length()>str.length())Empiezaigual=false;
			for(int i = 0;i<s.length() && Empiezaigual;++i) {
				if(s.charAt(i)!=str.charAt(i))Empiezaigual=false;
			}
			if(Empiezaigual)m.addElement(str);
		}
		
	}

//	private void funcion(HintTextField input, JTextArea ta) {
//		ta.setText("");
//		String s = input.getText();
//		String mostrar="";
//		for(String str:jo.keySet()) {
//			boolean Empiezaigual=true;
//			if(s.isEmpty()||s.length()>str.length())Empiezaigual=false;
//			for(int i = 0;i<s.length() && Empiezaigual;++i) {
//				if(s.charAt(i)!=str.charAt(i))Empiezaigual=false;
//			}
//			if(Empiezaigual)mostrar+=str+"\n";
//		}
//		ta.setText(mostrar);
//	}
	private class MiList implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for(int i =0;i<6;++i) {
				if(e.getSource()==listitas.get(i) && listitas.get(i).getSelectedIndices().length>0)
					lista.get(i).setText(listitas.get(i).getSelectedValue());
			}
		}
		
	}
	private class MiPopup implements MouseListener {

		public void mousePressed(MouseEvent e) {
			showPopup(e);
		}
		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}
		public void mouseExited(MouseEvent e) {	}
		public void mouseEntered(MouseEvent e) { }
		public void mouseClicked(MouseEvent e) { }

		private void showPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				//e.getComponent().requestFocus();
				for(int i =0;i<6;++i) {
					if(e.getSource() == lista.get(i)) {
						menuPopup1.show(e.getComponent(), e.getX(), e.getY());
						a=i;
					}
						
				}
				//menuPopup1.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}
