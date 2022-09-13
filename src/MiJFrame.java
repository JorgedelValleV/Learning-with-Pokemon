import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MiJFrame extends JFrame{

	JPanel mainPanel;
	JPanel logPanel;;
	EjemploTeclado componenteTeclado;
	MenuPanel menu;
	ChangePokemon cambiar;
	Mochililla bag;
	Battle battle;
	List<String> RED;
	List<String> BLUE;
	List<Pokemon> RED_pok;
	List<Pokemon> BLUE_pok;
	List<Attack> attacks;
	boolean stopped;
	int debBLUE=0;
	int debRED =0;
//	Timer tiempo;
//	TimerTask task;
	Clip sonido; 
	public MiJFrame() {
		super("Battle Frontier");
		try {
			sonido= AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("musikilla.wav")));
			
			
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		battle=new Battle(null,null,this);
		elegirPokemon();
		//initPokemon();
		initAllStats();
		initGUI();
		//startAnimation();
	}
	public Battle getBattle() {
		return this.battle;
	}
//	public void animar() {
//		this.battle.repaint();
//	}
//	public void startAnimation() {
//		 tiempo = new Timer();
//	     task = new TimerTask() {
//
//	    	 @Override
//	    	 public void run() {
//	    		 animar();
//	    	 }
//	     };
//	     tiempo.schedule(task, 0, 400);
//	}
	private void elegirPokemon() {
		List<String> agua=new ArrayList<String>();
		List<String> fuego=new ArrayList<String>();
		List<String> planta=new ArrayList<String>();
		List<String> azul=new ArrayList<String>();
		
		
//		RED=new ArrayList<String>();
//		BLUE=new ArrayList<String>();
//		RED.add("Blastoise");RED.add("Feraligatr");RED.add("Swampert");RED.add("Articuno");RED.add("Zapdos");RED.add("Moltres");
//		BLUE.add("Charizard");BLUE.add("Typhlosion");BLUE.add("Blaziken");BLUE.add("Suicune");BLUE.add("Raikou");BLUE.add("Entei");
		agua.add("Blastoise");agua.add("Feraligatr");agua.add("Swampert");agua.add("Articuno");agua.add("Zapdos");agua.add("Moltres");
		fuego.add("Charizard");fuego.add("Typhlosion");fuego.add("Blaziken");fuego.add("Suicune");fuego.add("Raikou");fuego.add("Entei");
		planta.add("Venusaur");planta.add("Meganium");planta.add("Sceptile");planta.add("Kyogre");planta.add("Rayquaza");planta.add("Groudon");
		azul.add("Blastoise");azul.add("Pidgeot");azul.add("Exeggutor");azul.add("Rhydon");azul.add("Arcanine");azul.add("Alakazam");
		RED=agua;
		BLUE=azul;
	}
	public void inicio() {
		logPanel.setVisible(false);
		setContentPane(mainPanel);
		mainPanel.setVisible(true);
		sonido.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void setRedPok(List<String> l) {
		List<String> intro=new ArrayList<String>();
		for(String s:l) {
			intro.add(s);
		}
		RED=intro;
		battle=new Battle(null,null,this);
		initAllStats();
		initGUI();
	}
	public void cambiarPokemon(String s,boolean turno) {
		//componenteTeclado.setPok1(s);
		menu.mostrar("\n\nRED HA RETIRADO A "+battle.getPokemon1().getName().toUpperCase());
		battle.setPokemon1(RED_pok.get(Integer.parseInt(s)));
		menu.ponerEnCola("\n\nADELANTE "+battle.getPokemon1().getName().toUpperCase());
		menu.cambiarAtaques();
		if(turno)this.makeTurn(4);
		//menu.mostrar("\n\nRED HA SACADO A "+battle.getPokemon1().getName().toUpperCase());
		inicio();
	}
	public void usarObjeto(String s) {
		inicio();
		//menu.mostrar("\n\nRED HA USADO "+s);
		menu.ponerEnCola("\n\nRED HA USADO "+s);
	}
	public void abrirMochila() {
		setContentPane(bag);
		bag.setVisible(true);
		mainPanel.setVisible(false);
	}
	public void abrirPokemon() {
		setContentPane(cambiar);
		cambiar.setVisible(true);
		mainPanel.setVisible(false);
		cambiar.cambiaBotones();
	}
	public void debilitadoRED() {
		cambiar.desabilitarCancel();
		cambiar.desabilitarAct();
		++debRED;
		if(debRED<6) {
			abrirPokemon();
		}
		else menu.ponerEnCola("\n\nBLUE HA GANADO EL COMBATE");
	}
	public void debilitadoBLUE() {	
		battle.pintaRED();
		++debBLUE;
		if(debBLUE<6) {
			battle.setPokemon2(BLUE_pok.get(debBLUE));
			menu.ponerEnCola("\n\nBLUE HA SACADO A "+battle.getPokemon2().getName().toUpperCase());
		}
		else menu.ponerEnCola("\n\nRED HA GANADO EL COMBATE");
	}
	public void mostrarAtaques(String pok,String ata) {
		menu.ponerEnCola("\n\n "+pok.toUpperCase()+" HA USADO "+ata.toUpperCase());
	}
	public void mostrarDebilitado(String pok) {
		menu.ponerEnCola("\n\n "+pok.toUpperCase()+" SE HA DEBILITADO");
	}
	public void mostrarFallo() {
		menu.ponerEnCola("\n\n PERO HA FALLADO...");
	}
	public void mostrarNoAfecta(String s) {
		menu.ponerEnCola("\n\n NO AFECTA A " + s.toUpperCase());
	}
	public void mostrarNoEficaz() {
		menu.ponerEnCola("\n\n NO ES MUY EFICAZ...");
	}
	public void mostrarEficaz() {
		menu.ponerEnCola("\n\n ES MUY EFICAZ");
	}
	public void mostrarCritico() {
		menu.ponerEnCola("\n\n UN GOLPE CRITICO");
	}
	public void mostrarSiguiente() {
		menu.mostrarSiguienteMensaje();
	}
	
	private void initGUI() {
		cambiar= new ChangePokemon(this,RED_pok);
		bag = new Mochililla(this);
		menu=new MenuPanel(this);
		menu.mostrar("\n\nRED HA SACADO A "+battle.getPokemon1().getName().toUpperCase());
		menu.ponerEnCola("\n\nBLUE HA SACADO A "+battle.getPokemon2().getName().toUpperCase());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		componenteTeclado = new EjemploTeclado(RED.get(0),BLUE.get(0));
		componenteTeclado.setPreferredSize(new Dimension(400, 400));
		
		mainPanel.add(battle);
		mainPanel.add(menu);
		
		JScrollPane scroll = new JScrollPane(new Pantalla_LogIn(this));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(30, 177, 316, 472);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setPreferredSize (new Dimension(0,0)); 
		scroll.getVerticalScrollBar().setUnitIncrement(15);
		
		logPanel = new JPanel();
		logPanel.setLayout(new BorderLayout());
		logPanel.add(scroll,BorderLayout.CENTER);
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case 'b':
					menu.volver();
					break;
				case 'a':
					//menu.mostrar("\n\nQUE DEBERIA HACER "+battle.getPokemon1().getName().toUpperCase()+"?");
					menu.mostrarSiguienteMensaje();
					break;
				case KeyEvent.VK_LEFT:
					break;
				case KeyEvent.VK_RIGHT:
					break;
				case KeyEvent.VK_UP:
					break;
				case KeyEvent.VK_DOWN:
					break;
				default:
				}
			}
		});
		addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				menu.mostrarSiguienteMensaje();
				System.out.println(e.getX()+", "+ e.getY());
				System.out.println(MiJFrame.this.getContentPane());
			}
			public void mouseExited(MouseEvent e) {
				requestFocus();
			}
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
		this.getRootPane().setDefaultButton(menu.getAtacar());
		this.setSize(640, 574);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(600, 200);
		this.setIconImage(new ImageIcon("res/icono.png").getImage());
		this.requestFocus();
		//setContentPane(mainPanel);
		setContentPane(logPanel);
	}

	public void makeTurn(int index) {

		Battle.Turn turn = this.battle.new Turn();
		if(index<4) {
			System.out.println("Using attack"+ index);
			turn.setCommand1(this.battle.new Command(constants.DO_ATTACK, index));//{DO_ATTACK: index}
		}
		if(index==4) {
			System.out.println("Opening pokemon");
			turn.setCommand1(this.battle.new Command(constants.DO_CHANGE, index));//{DO_ATTACK: index}
		}
		if(index==5) {
			System.out.println("Opening bag");
			turn.setCommand1(this.battle.new Command(constants.DO_ITEM, index));//{DO_ATTACK: index}
		}
        
        turn.setCommand2(this.battle.new Command(constants.DO_ATTACK,0));//{DO_ATTACK: 0}
        
        if (turn.can_start()) {
        	// Execute turn
            this.battle.execute_turn(turn);
            this.battle.print_current_status();
        }
	}

	private void initAllStats() {
//		for(String s:RED) {
//			initPokemonStats(s);
//		}
//		for(String s:BLUE) {
//			initPokemonStats(s);
//		}
		initAttacks();
		RED_pok=new ArrayList<Pokemon>();
		BLUE_pok=new ArrayList<Pokemon>();
		int j=0;
    	JSONObject jo;
		try {
			jo = new JSONObject(new JSONTokener(new FileInputStream(new File("res/db/pokemons.txt"))));
			for(int i =0;i<BLUE.size();++i) {
				Pokemon uno=initPokemonStats(RED.get(i),jo);
				Pokemon dos=initPokemonStats(BLUE.get(i),jo);

				List<Attack> l1=new ArrayList<Attack>();
				List<Attack> l2=new ArrayList<Attack>();
				for(int k =0;k<4;++k) {
					l1.add(attacks.get(j));
					++j;
				}
				for(int k =0;k<4;++k) {
					l2.add(attacks.get(j));
					++j;
				}
				uno.setAttacks(l1);
				dos.setAttacks(l2);
				if(i==0) {
					this.battle.setPokemon1(uno);	        
			        this.battle.setPokemon2(dos);
				}
				RED_pok.add(uno);
				BLUE_pok.add(dos);
			}
		} catch (JSONException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Pokemon initPokemonStats(String s,JSONObject jo) {
		Pokemon pokemon=null;
        try {
			JSONObject jp=jo.getJSONObject(s);
			Integer type2=null;
			if(jp.has("type2"))type2= jp.getInt("type2");
			pokemon = new Pokemon(s, 100, jp.getInt("type1"), type2);
			
			Map<String,Integer> baseStats =new HashMap<String,Integer>();
	        
	        baseStats.put(constants.HP,jp.getInt(constants.HP));
	        baseStats.put(constants.ATTACK,jp.getInt(constants.ATTACK));
	        baseStats.put(constants.DEFENSE,jp.getInt(constants.DEFENSE));
	        baseStats.put(constants.SPATTACK,jp.getInt(constants.SPATTACK));
	        baseStats.put(constants.SPDEFENSE,jp.getInt(constants.SPDEFENSE));
	        baseStats.put(constants.SPEED,jp.getInt(constants.SPEED));
	        pokemon.setBaseStats(baseStats);

	        
	        Map<String,Integer> auxev =new HashMap<String,Integer>();
	        Map<String,Integer> auxiv =new HashMap<String,Integer>();
	        
	        auxev.put(constants.HP,0);
	        auxev.put(constants.ATTACK,0);
	        auxev.put(constants.DEFENSE,0);
	        auxev.put(constants.SPATTACK,0);
	        auxev.put(constants.SPDEFENSE,0);
	        auxev.put(constants.SPEED,0);
	        pokemon.setEv(auxev);
	        
	        auxiv.put(constants.HP,21);
	        auxiv.put(constants.ATTACK,21);
	        auxiv.put(constants.DEFENSE,21);
	        auxiv.put(constants.SPATTACK,21);
	        auxiv.put(constants.SPDEFENSE,21);
	        auxiv.put(constants.SPEED,21);
	        pokemon.setIv(auxiv);

	        
	        pokemon.compute_stats();
	        System.out.println(pokemon.getStats().toString());
	        pokemon.setCurrent_hp(pokemon.getStats().get(constants.HP));
	        System.out.println(pokemon.getCurrent_hp() +", "+pokemon.getStats().get(constants.HP));
	        return pokemon;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pokemon;
	}
	private void initPokemon() {
        try {
			JSONObject jo = new JSONObject(new JSONTokener(new FileInputStream(new File("res/db/pokemons.txt"))));
			
			JSONObject jp1=jo.getJSONObject(RED.get(0));
			Integer type2=null;
			if(jp1.has("type2"))type2= jp1.getInt("type2");
			Pokemon pokemon1 = new Pokemon(RED.get(0), 100, jp1.getInt("type1"), type2);
			
			JSONObject jp2=jo.getJSONObject(BLUE.get(0));
			Integer type22=null;
			if(jp2.has("type2"))type22= jp2.getInt("type2");
			Pokemon pokemon2 = new Pokemon(BLUE.get(0), 100, jp2.getInt("type1"), type22);
			
			Map<String,Integer> baseStats1 =new HashMap<String,Integer>();
	        Map<String,Integer> baseStats2 =new HashMap<String,Integer>();
	        
	        baseStats1.put(constants.HP,jp1.getInt(constants.HP));
	        baseStats1.put(constants.ATTACK,jp1.getInt(constants.ATTACK));
	        baseStats1.put(constants.DEFENSE,jp1.getInt(constants.DEFENSE));
	        baseStats1.put(constants.SPATTACK,jp1.getInt(constants.SPATTACK));
	        baseStats1.put(constants.SPDEFENSE,jp1.getInt(constants.SPDEFENSE));
	        baseStats1.put(constants.SPEED,jp1.getInt(constants.SPEED));
	        pokemon1.setBaseStats(baseStats1);
	        
	        baseStats2.put(constants.HP,jp2.getInt(constants.HP));
	        baseStats2.put(constants.ATTACK,jp2.getInt(constants.ATTACK));
	        baseStats2.put(constants.DEFENSE,jp2.getInt(constants.DEFENSE));
	        baseStats2.put(constants.SPATTACK,jp2.getInt(constants.SPATTACK));
	        baseStats2.put(constants.SPDEFENSE,jp2.getInt(constants.SPDEFENSE));
	        baseStats2.put(constants.SPEED,jp2.getInt(constants.SPEED));
	        pokemon2.setBaseStats(baseStats2);
	        
	        Map<String,Integer> aux1ev =new HashMap<String,Integer>();
	        Map<String,Integer> aux1iv =new HashMap<String,Integer>();
	        Map<String,Integer> aux2ev =new HashMap<String,Integer>();
	        Map<String,Integer> aux2iv =new HashMap<String,Integer>();
	        
	        aux1ev.put(constants.HP,0);
	        aux1ev.put(constants.ATTACK,0);
	        aux1ev.put(constants.DEFENSE,0);
	        aux1ev.put(constants.SPATTACK,0);
	        aux1ev.put(constants.SPDEFENSE,0);
	        aux1ev.put(constants.SPEED,0);
	        pokemon1.setEv(aux1ev);
	        
	        aux1iv.put(constants.HP,21);
	        aux1iv.put(constants.ATTACK,21);
	        aux1iv.put(constants.DEFENSE,21);
	        aux1iv.put(constants.SPATTACK,21);
	        aux1iv.put(constants.SPDEFENSE,21);
	        aux1iv.put(constants.SPEED,21);
	        pokemon1.setIv(aux1iv);
	        
	        aux2ev.put(constants.HP,0);
	        aux2ev.put(constants.ATTACK,0);
	        aux2ev.put(constants.DEFENSE,0);
	        aux2ev.put(constants.SPATTACK,0);
	        aux2ev.put(constants.SPDEFENSE,0);
	        aux2ev.put(constants.SPEED,0);
	        pokemon2.setEv(aux2ev);
	        
	        aux2iv.put(constants.HP,21);
	        aux2iv.put(constants.ATTACK,21);
	        aux2iv.put(constants.DEFENSE,21);
	        aux2iv.put(constants.SPATTACK,21);
	        aux2iv.put(constants.SPDEFENSE,21);
	        aux2iv.put(constants.SPEED,21);
	        pokemon2.setIv(aux1iv);
	        
	        pokemon1.compute_stats();
	        pokemon2.compute_stats();
	        System.out.println(pokemon1.getStats().toString());
	        System.out.println(pokemon2.getStats().toString());
	        pokemon1.setCurrent_hp(pokemon1.getStats().get(constants.HP));
	        pokemon2.setCurrent_hp(pokemon2.getStats().get(constants.HP));
	        this.battle.setPokemon1(pokemon1);	        
	        this.battle.setPokemon2(pokemon2);
	        System.out.println(pokemon1.getCurrent_hp() +", "+pokemon1.getStats().get(constants.HP));
		} catch (JSONException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initAttacks() {
		attacks=new ArrayList<Attack>();
		//lista de ataues de todos los pokemon
		//4 de red 4 de blue etc
		// nombre tipo esp/fisico pp potencia precision
		
//		attacks.add(new Attack("Escaldar",10,constants.SPECIAL,15,80,100));attacks.add(new Attack("Rayo",12,constants.SPECIAL,15,90,70));attacks.add(new Attack("Pulso Dragon",15,constants.SPECIAL,15,80,100));attacks.add(new Attack("Energibola",11,constants.SPECIAL,10,85,100));
//		attacks.add(new Attack("Lanzallamas",9,constants.SPECIAL,15,90,100));attacks.add(new Attack("Garra Dragon",15,constants.PHYSICAL,15,75,100));attacks.add(new Attack("Onda Certera",1,constants.SPECIAL,5,120,70));attacks.add(new Attack("Vuelo",2,constants.PHYSICAL,15,90,95));
//		attacks.add(new Attack("Hidroariete",10,constants.PHYSICAL,10,85,100));attacks.add(new Attack("Triturar",16,constants.PHYSICAL,15,80,100));attacks.add(new Attack("Puño Hielo",14,constants.SPECIAL,15,80,100));attacks.add(new Attack("Danza Dragon",15,constants.BOOST,10,constants.SPEEDn,100));
//		attacks.add(new Attack("Humareda",9,constants.SPECIAL,15,80,100));attacks.add(new Attack("Estallido",9,constants.SPECIAL,5,150,100));attacks.add(new Attack("Onda Certera",1,constants.SPECIAL,5,120,70));attacks.add(new Attack("Energibola",11,constants.SPECIAL,10,85,100));
//		attacks.add(new Attack("Terremoto",4,constants.PHYSICAL,10,100,100));attacks.add(new Attack("Cascada",10,constants.PHYSICAL,10,80,100));attacks.add(new Attack("Fuerza Bruta",1,constants.PHYSICAL,5,120,100));attacks.add(new Attack("Puño Hielo",14,constants.SPECIAL,15,80,100));
//		attacks.add(new Attack("Envite Igneo",9,constants.PHYSICAL,15,120,100));attacks.add(new Attack("Fuerza Bruta",1,constants.PHYSICAL,5,120,100));attacks.add(new Attack("Roca Afilada",5,constants.PHYSICAL,5,100,80));attacks.add(new Attack("Danza Espada",0,constants.BOOST,20,constants.ATTACKn,100));
//		attacks.add(new Attack("Rayo hielo",14,constants.SPECIAL,10,90,100));attacks.add(new Attack("Huracan",2,constants.SPECIAL,10,110,70));attacks.add(new Attack("Onda Certera",1,constants.SPECIAL,5,120,70));attacks.add(new Attack("Liofilizacion",14,constants.SPECIAL,20,70,100));
//		attacks.add(new Attack("Paz mental",13,constants.BOOST,20,constants.SPDEFENSEn,100));attacks.add(new Attack("Escaldar",10,constants.SPECIAL,15,80,100));attacks.add(new Attack("Paranormal",13,constants.SPECIAL,20,80,100));attacks.add(new Attack("Rayo hielo",14,constants.SPECIAL,10,90,100));
//		attacks.add(new Attack("Pico taladro",2,constants.PHYSICAL,20,80,100));attacks.add(new Attack("Rayo",12,constants.SPECIAL,15,90,100));attacks.add(new Attack("Onda Ignea",9,constants.SPECIAL,15,95,90));attacks.add(new Attack("Poder Oculto",14,constants.SPECIAL,15,60,100));
//		attacks.add(new Attack("Rayo",12,constants.SPECIAL,15,90,100));attacks.add(new Attack("Paz mental",13,constants.BOOST,20,constants.SPDEFENSEn,100));attacks.add(new Attack("Esfera aural",1,constants.SPECIAL,20,80,100));attacks.add(new Attack("Poder Oculto",14,constants.SPECIAL,15,60,100));
//		attacks.add(new Attack("Llamarada",9,constants.SPECIAL,5,110,85	));attacks.add(new Attack("Huracan",2,constants.SPECIAL,10,110,70));attacks.add(new Attack("Tajo Aereo",2,constants.SPECIAL,15,90,95));attacks.add(new Attack("Lanzallamas",9,constants.SPECIAL,15,90,100));
//		attacks.add(new Attack("Envite Igneo",9,constants.PHYSICAL,15,120,100));attacks.add(new Attack("Roca Afilada",5,constants.PHYSICAL,5,100,80));attacks.add(new Attack("Fuego Sagrado",9,constants.PHYSICAL,5,100,95));attacks.add(new Attack("Velocidad extrema",0,constants.PHYSICAL,5,80,100));
		
		List<Attack >fuego=new ArrayList<Attack>();
		List<Attack >agua=new ArrayList<Attack>();
		List<Attack >planta=new ArrayList<Attack>();
		agua.add(new Attack("Escaldar",10,constants.SPECIAL,15,80,100));		agua.add(new Attack("Rayo",12,constants.SPECIAL,15,90,100));			agua.add(new Attack("Pulso Dragon",15,constants.SPECIAL,15,80,100));	agua.add(new Attack("Energibola",11,constants.SPECIAL,10,85,100));
		agua.add(new Attack("Hidroariete",10,constants.PHYSICAL,10,85,100));	agua.add(new Attack("Triturar",16,constants.PHYSICAL,15,80,100));		agua.add(new Attack("Puño Hielo",14,constants.SPECIAL,15,80,100));		agua.add(new Attack("Danza Dragon",15,constants.BOOST,10,constants.SPEEDn,100));
		agua.add(new Attack("Terremoto",4,constants.PHYSICAL,10,100,100));		agua.add(new Attack("Cascada",10,constants.PHYSICAL,10,80,100));		agua.add(new Attack("Fuerza Bruta",1,constants.PHYSICAL,5,120,100));	agua.add(new Attack("Puño Hielo",14,constants.SPECIAL,15,80,100));
		agua.add(new Attack("Rayo hielo",14,constants.SPECIAL,10,90,100));		agua.add(new Attack("Huracan",2,constants.SPECIAL,10,110,70));			agua.add(new Attack("Onda Certera",1,constants.SPECIAL,5,120,70));		agua.add(new Attack("Liofilizacion",14,constants.SPECIAL,20,70,100));
		agua.add(new Attack("Pico taladro",2,constants.PHYSICAL,20,80,100));	agua.add(new Attack("Rayo",12,constants.SPECIAL,15,90,100));			agua.add(new Attack("Onda Ignea",9,constants.SPECIAL,15,95,90));		agua.add(new Attack("Poder Oculto",14,constants.SPECIAL,15,60,100));
		agua.add(new Attack("Llamarada",9,constants.SPECIAL,5,110,85));			agua.add(new Attack("Huracan",2,constants.SPECIAL,10,110,70));			agua.add(new Attack("Tajo Aereo",2,constants.SPECIAL,15,90,95));		agua.add(new Attack("Lanzallamas",9,constants.SPECIAL,15,90,100));
		fuego.add(new Attack("Lanzallamas",9,constants.SPECIAL,15,90,100));		fuego.add(new Attack("Garra Dragon",15,constants.PHYSICAL,15,75,100));	fuego.add(new Attack("Onda Certera",1,constants.SPECIAL,5,120,70));		fuego.add(new Attack("Vuelo",2,constants.PHYSICAL,15,90,95));
		fuego.add(new Attack("Humareda",9,constants.SPECIAL,15,80,100));		fuego.add(new Attack("Estallido",9,constants.SPECIAL,5,150,100));		fuego.add(new Attack("Onda Certera",1,constants.SPECIAL,5,120,70));		fuego.add(new Attack("Energibola",11,constants.SPECIAL,10,85,100));
		fuego.add(new Attack("Envite Igneo",9,constants.PHYSICAL,15,120,100));	fuego.add(new Attack("Fuerza Bruta",1,constants.PHYSICAL,5,120,100));	fuego.add(new Attack("Roca Afilada",5,constants.PHYSICAL,5,100,80));	fuego.add(new Attack("Danza Espada",0,constants.BOOST,20,constants.ATTACKn,100));
		fuego.add(new Attack("Escaldar",10,constants.SPECIAL,15,80,100));		fuego.add(new Attack("Paranormal",13,constants.SPECIAL,20,80,100));		fuego.add(new Attack("Rayo hielo",14,constants.SPECIAL,10,90,100));		fuego.add(new Attack("Paz mental",13,constants.BOOST,20,constants.SPDEFENSEn,100));
		fuego.add(new Attack("Rayo",12,constants.SPECIAL,15,90,100));			fuego.add(new Attack("Esfera aural",1,constants.SPECIAL,20,80,100));	fuego.add(new Attack("Poder Oculto",14,constants.SPECIAL,15,60,100));	fuego.add(new Attack("Paz mental",13,constants.BOOST,20,constants.SPDEFENSEn,100));
		fuego.add(new Attack("Envite Igneo",9,constants.PHYSICAL,15,120,100));	fuego.add(new Attack("Roca Afilada",5,constants.PHYSICAL,5,100,80));	fuego.add(new Attack("Fuego Sagrado",9,constants.PHYSICAL,5,100,95));	fuego.add(new Attack("Velocidad extrema",0,constants.PHYSICAL,5,80,100));

		for(int i= 0;i<6;++i) {
			for(int j=0;j<4;++j) {
				attacks.add(agua.get(4*i+j));
			}
			for(int j=0;j<4;++j) {
				attacks.add(fuego.get(4*i+j));
			}
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MiJFrame();
			}
		});
	}

}
