import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class Mochililla extends JPanel {
	private JLabel itemSelect;
	private JList<String> lstLista;
	MiJFrame cont;

	
	public Mochililla(MiJFrame contenedor) {
		cont=contenedor;
		String[] valores = { "Pokeball", "Pocion", "Revivir", "Baya Aranja","Antiparalizar" 
				,"DefensaX", "Elixir", "Eter", "RestaurarTodo", "Hiperpocion"};

		itemSelect = new JLabel("");
		lstLista = new JList<String>(valores);
		lstLista.setFont(new Font("Comic Sans MS", 1, 20));
		JScrollPane scb = new JScrollPane(lstLista);
		scb.setPreferredSize(new Dimension(250,350));
		scb.setMaximumSize(new Dimension(250,350));
		scb.setFont(new Font("Comic Sans MS", 1, 20));
		
		//numero de filas que se muestran sin desplazamiento
		lstLista.setVisibleRowCount(4);

		lstLista.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				itemSelect.setText(lstLista.getSelectedValue());

			}
		});
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.black);
		btnSalir.setPreferredSize(new Dimension(50,50));
		btnSalir.setMinimumSize(new Dimension(50,50));
		btnSalir.setFont(new Font("Comic Sans MS", 1, 20));
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Mochililla.this.setVisible(false);
				cont.inicio();
			}
		});
		JButton btnUsar = new JButton("Usar");
		btnUsar.setForeground(Color.black);
		btnUsar.setPreferredSize(new Dimension(50,50));
		btnUsar.setMinimumSize(new Dimension(50,50));
		btnUsar.setFont(new Font("Comic Sans MS", 1, 20));
		btnUsar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Mochililla.this.setVisible(false);
				if(itemSelect.getText()!="")
					cont.usarObjeto(itemSelect.getText().toUpperCase());
			}
		});

		this.setPreferredSize(new Dimension(623,533));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JLabel jl=new JLabel();
		ImageIcon i = new ImageIcon("res/mochila.png");
		jl.setIcon (i);
		jl.setPreferredSize(new Dimension(250,250));
		jl.setMaximumSize(new Dimension(250,250));
		jl.setLocation(69,36);
		
		JLabel obl=new JLabel("Objeto seleccionado:");
		obl.setAlignmentY(CENTER_ALIGNMENT);
		obl.setBackground(Color.orange);
		obl.setPreferredSize(new Dimension(250,50));
		obl.setMaximumSize(new Dimension(250,50));
		obl.setFont(new Font("Comic Sans MS", 1, 20));
		itemSelect.setPreferredSize(new Dimension(250,50));
		itemSelect.setMaximumSize(new Dimension(250,50));
		itemSelect.setBackground(Color.orange);
		itemSelect.setFont(new Font("Comic Sans MS", 1, 20));
		
		JPanel pnl1=new JPanel();pnl1.setLayout(new BoxLayout(pnl1,BoxLayout.X_AXIS));
		JPanel pnl2=new JPanel();pnl2.setLayout(new BoxLayout(pnl2,BoxLayout.X_AXIS));pnl2.setAlignmentX(CENTER_ALIGNMENT);
		JPanel pnl3=new JPanel();pnl3.setLayout(new BoxLayout(pnl3,BoxLayout.X_AXIS));

		pnl1.add(jl);
		pnl1.add(scb);
		pnl2.add(obl);
		pnl2.add(itemSelect);
		pnl3.add(btnUsar);
		pnl3.add(btnSalir);
		
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);		
		
		this.setBackground(Color.orange);
		this.setOpaque(true);
		this.setSize(623, 533);
		this.setVisible(true);
	}
}