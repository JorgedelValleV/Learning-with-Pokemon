import java.awt.Color;
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

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Pantalla_A extends JPanel{
	private ArrayList<HintTextField> lista;
	private ArrayList<DefaultListModel<String>> modelillos;
	private ArrayList<JList<String>> listitas;
	private ArrayList<JScrollPane> advise;
	private List<String> list;
	private JSONObject jo;
	private Text error;
	private Boton iniciar;
	private MiJFrame cont;

	public Pantalla_A(MiJFrame conte) {
		cont=conte;
		lista=new ArrayList<HintTextField>();
		modelillos = new ArrayList<DefaultListModel<String>>();
		listitas = new ArrayList<JList<String>>();
		advise=new ArrayList<JScrollPane>();
		initGui();
		try {
			jo = new JSONObject(new JSONTokener(new FileInputStream(new File("res/db/ataques.txt"))));
		} catch (JSONException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void initGui() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		MiMouse mi= new MiMouse();
		this.addMouseListener(mi);
		
		MiFocus mi1=new MiFocus();
		MiKey mi2=new MiKey();
		
		error = new Text("");
		error.setSize(15);
		error.setTextColor(Color.RED);
		error.setBounds(25, 180, 200, 20);//20
		this.add(error);
				
		for(int i=0;i<4;++i) {
			DefaultListModel<String> modelo = new DefaultListModel<String>();
			JList<String> lstLista = new JList<String>(modelo);
			lstLista.addListSelectionListener(new MiList());
			JScrollPane scb= new JScrollPane(lstLista);
			if(i==0)scb.setBounds(10, 53, 200, 83);
			if(i==1)scb.setBounds(10, 96, 200, 83);
			if(i==2)scb.setBounds(10, 10, 200, 83);
			if(i==3)scb.setBounds(10, 53, 200, 83);
			scb.setVisible(false);
			this.add(scb);
			advise.add(scb);
			modelillos.add(modelo);
			listitas.add(lstLista);
		}
		
		HintTextField attack1 = new HintTextField("Primer Ataque");
		attack1.setBounds(10, 10, 200, 40);//53
		attack1.setHorizontalAlignment(JTextField.CENTER);
		this.add(attack1);
		attack1.addFocusListener(mi1);
		attack1.addKeyListener(mi2);

		HintTextField attack2 = new HintTextField("Segundo Ataque");
		attack2.setBounds(10, 53, 200, 40);
		attack2.setHorizontalAlignment(JTextField.CENTER);
		this.add(attack2);
		attack2.addFocusListener(mi1);
		attack2.addKeyListener(mi2);
		
		HintTextField attack3 = new HintTextField("Tercer Ataque");
		attack3.setBounds(10, 96, 200, 40);
		attack3.setHorizontalAlignment(JTextField.CENTER);
		this.add(attack3);
		attack3.addFocusListener(mi1);
		attack3.addKeyListener(mi2);
		
		HintTextField attack4 = new HintTextField("Cuarto Ataque");
		attack4.setBounds(10, 139, 200, 40);
		attack4.setHorizontalAlignment(JTextField.CENTER);
		this.add(attack4);
		attack4.addFocusListener(mi1);
		attack4.addKeyListener(mi2);


		lista.add(attack1);lista.add(attack2);lista.add(attack3);lista.add(attack4);

		iniciar = new Boton("Añadir");
		iniciar.setBounds(220, 76, 70, 40);
		iniciar.setTam(11);
		this.add(iniciar);

		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean sePuede=true;
				list=new ArrayList<String>();
				for(int i=0;i<4;++i) {
					lista.get(i).displayError(false);
					list.add(lista.get(i).getText());
					if(!jo.has(lista.get(i).getText())) {
						error.setText("Hay un ataque no existe");
						lista.get(i).displayError(true);
						sePuede=false;
					}
				}
				if(sePuede) {
					try {
						Pantalla_A.this.setVisible(false);
						//cont.setRedPok(list);
						//cont.inicio();
					} catch (Exception e) {
						error.setText(e.getMessage());
					}
				}
			}
		});

	}
	private class MiList implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			for(int i =0;i<4;++i) {
				if(e.getSource()==listitas.get(i) && listitas.get(i).getSelectedIndices().length>0)
					lista.get(i).setText(listitas.get(i).getSelectedValue());
			}
		}
		
	}
	private class MiFocus implements FocusListener{
		public void focusGained(FocusEvent e) {
			for(int i =0;i<4;++i) {
				if(e.getSource()==lista.get(i)) {
					advise.get(i).setVisible(true);
					if(i==0) {lista.get(1).setVisible(false);lista.get(2).setVisible(false);}
					if(i==1) {lista.get(2).setVisible(false);lista.get(3).setVisible(false);}
					if(i==2) {lista.get(0).setVisible(false);lista.get(1).setVisible(false);}
					if(i==3) {lista.get(1).setVisible(false);lista.get(2).setVisible(false);}
				}
			}
		}
		public void focusLost(FocusEvent e) {
			for(int i =0;i<4;++i) {
				if(e.getSource()==lista.get(i)){
					advise.get(i).setVisible(false);
					if(i==0) {lista.get(1).setVisible(true);lista.get(2).setVisible(true);}
					if(i==1) {lista.get(2).setVisible(true);lista.get(3).setVisible(true);}
					if(i==2) {lista.get(0).setVisible(true);lista.get(1).setVisible(true);}
					if(i==3) {lista.get(1).setVisible(true);lista.get(2).setVisible(true);}
				}
			}
		}
	}
	private class MiKey implements KeyListener{
		public void keyPressed(KeyEvent e) {}

		public void keyReleased(KeyEvent e) {
			if(KeyEvent.VK_ENTER==e.getExtendedKeyCode()) {
				for(int i =0;i<4;++i) {
					if(e.getSource()==lista.get(i)) {
						if(lista.get(i).hasFocus())System.out.println(1);
						if(listitas.get(i).getSelectedIndex()!=0) {
							if(lista.get(i).hasFocus())System.out.println(2);
							listitas.get(i).setSelectedIndex(0);
							if(lista.get(i).hasFocus())System.out.println(3);
//							if(i<3)
//								lista.get(i+1).requestFocus();
//							else {
//								cont.getRootPane().setDefaultButton(iniciar);
//								iniciar.requestFocus();
//							}
							if(i==0) {lista.get(1).requestFocus();if(lista.get(1).hasFocus())System.out.println(4);}
							if(i==1)lista.get(2).requestFocus();
							if(i==2)lista.get(3).requestFocus();
							if(i==3){
								cont.getRootPane().setDefaultButton(iniciar);
								iniciar.requestFocus();
							}
						}
					}
				}
			}
			else {
				for(int i =0;i<4;++i) {
					if(e.getSource()==lista.get(i)) {
						funcioncilla(lista.get(i),modelillos.get(i));
					}
				}
			}
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

		public void keyTyped(KeyEvent e) {}
	}
	private class MiMouse implements MouseListener {

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {
			Pantalla_A.this.requestFocus();
		}
		public void mouseExited(MouseEvent e) {	}
		public void mouseEntered(MouseEvent e) { }
		public void mouseClicked(MouseEvent e) { }
	}
}
