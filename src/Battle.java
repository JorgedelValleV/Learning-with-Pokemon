import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Battle extends JComponent{
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private int actual_turn;
	private MiJFrame cont;
	private boolean RED=false;
	public Battle(Pokemon pokemon1,Pokemon pokemon2, MiJFrame contenedor) {
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
		this.actual_turn = 0;
		this.cont=contenedor;
		this.setPreferredSize(new Dimension(400, 400));
	}
	public void pintaRED() {
		RED=true;
		repaint();
	}
	public void pintaPok() {
		RED=false;
		repaint();
	}
	///////////////////////////////////////////////////////////////////////////////////////
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		g.setFont(new Font("Comic Sans MS", 1, 20));
		g.drawImage(new ImageIcon("res/battle_bg/battle_bg_2.png").getImage(), 0, 0, getWidth(), getHeight(), this);
    	
		if(RED) {
			ImageIcon i= new ImageIcon("res/blue.gif");
			g.drawImage(i.getImage(), 335,42,275 ,275, this);
		}
		else {
    		g.drawString(pokemon2.getName(), 20, 40);
    		g.drawString("Lv:"+pokemon2.getLevel(), 225, 40);
    		g.drawString((int)pokemon2.getCurrent_hp()+"/"+pokemon2.getStats().get(constants.HP), 130, 110);
    		g.drawImage(new ImageIcon("res/gui/life_bar_enemy.png").getImage(), 20,50,272,75, this);
    		g.drawImage(new ImageIcon("res/pokemon/"+pokemon2.getName().toLowerCase()+".png").getImage(), 335,42,275 ,275, this);
    		float t2 = (pokemon2.getCurrent_hp()/pokemon2.getStats().get(constants.HP));
    		Color color = Color.green;
            if (t2 < 0.5)
                color = Color.orange;
            if (t2 < 0.15)
                color = Color.red;
    		g.setColor(color);
            g.fillRect(103, 57,(int)(170*t2) , 20);
            g.setColor(Color.black);
		}
    		
    	g.drawString(pokemon1.getName(), 343, 348);//330 338
    	g.drawString("Lv:"+pokemon1.getLevel(), 535, 348);//530 338
    	g.drawString((int)pokemon1.getCurrent_hp()+"/"+pokemon1.getStats().get(constants.HP), 435, 410);
		g.drawImage(new ImageIcon("res/gui/life_bar_player.png").getImage(), 315,353,300,80 ,this);
		g.drawImage(new ImageIcon("res/pokemon/"+pokemon1.getName().toLowerCase()+"_back.png").getImage(), 0,220,340 ,330,this);
		
		float t1 = (pokemon1.getCurrent_hp()/pokemon1.getStats().get(constants.HP));
		
        Color color = Color.green;
        if (t1 < 0.5)
            color = Color.orange;
        if (t1 < 0.15)
            color = Color.red;
        g.setColor(color);
        g.fillRect(406, 352, (int)(180*t1), 17);
        g.setColor(Color.black);
	}
	///////////////////////////////////////////////////////////////////////////////////////
	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
		repaint();
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
		repaint();
	}

	public boolean is_finished() {
		boolean finished = this.pokemon1.getCurrent_hp() <= 0 || this.pokemon2.getCurrent_hp() <= 0;
		if (finished)
		    this.print_winner();
		return finished;
	}
	private void print_winner() {
		if (this.pokemon1.getCurrent_hp() <= 0 && 0< this.pokemon2.getCurrent_hp())
            System.out.println(this.pokemon2.getName() + " won in " + this.actual_turn+" turns!!");
		else if (this.pokemon2.getCurrent_hp() <= 0 && 0 < this.pokemon1.getCurrent_hp())
        	System.out.println(this.pokemon1.getName() + " won in " + this.actual_turn +" turns!!");
        else
        	System.out.println("DOUBLE KO!");
	}
	public void execute_turn(Turn turn) {
		// Logic to execute a turn inside a battle
        Command command1 = turn.getCommand1();
        Command command2 = turn.getCommand2();
        Attack attack1 = null;
        Attack attack2 = null;
        if (command1.action.containsKey(constants.DO_ATTACK)) {
        	attack1 = this.pokemon1.getAttacks().get(command1.action.get(constants.DO_ATTACK));
        	attack1.setPP(attack1.getPP()-1);
        }
        if (command2.action.containsKey(constants.DO_ATTACK)) {
            attack2 = this.pokemon2.getAttacks().get(command2.action.get(constants.DO_ATTACK));
    		attack2.setPP(attack2.getPP()-1);
    	}
        // Execute attacks
//        this.pokemon2.setCurrent_hp(this.pokemon2.getCurrent_hp()- this.compute_damage(attack1, this.pokemon1, this.pokemon2)); ;
//        if (this.pokemon2.getCurrent_hp() > 0)
//            this.pokemon1.setCurrent_hp(this.pokemon1.getCurrent_hp() - this.compute_damage(attack2, this.pokemon2, this.pokemon1));
        if(attack1==null) {
        	this.cont.mostrarAtaques(this.pokemon2.getName(), attack2.getName());
    		this.pokemon1.setCurrent_hp(this.pokemon1.getCurrent_hp()- this.compute_damage(attack2, this.pokemon2, this.pokemon1)); ;
        }
        else {
        	if(this.pokemon1.getStats().get(constants.SPEED)>this.pokemon2.getStats().get(constants.SPEED)) {
        		this.cont.mostrarAtaques(this.pokemon1.getName(), attack1.getName());
        		this.pokemon2.setCurrent_hp(this.pokemon2.getCurrent_hp()- this.compute_damage(attack1, this.pokemon1, this.pokemon2)); ;
        		if (this.pokemon2.getCurrent_hp() > 0) {
        			this.cont.mostrarAtaques(this.pokemon2.getName(), attack2.getName());
        			this.pokemon1.setCurrent_hp(this.pokemon1.getCurrent_hp() - this.compute_damage(attack2, this.pokemon2, this.pokemon1));
        		}
        		else {
        			this.cont.mostrarDebilitado(this.pokemon2.getName());
        			
        		}
        	
        		if(this.pokemon1.getCurrent_hp() <= 0) {
        			this.cont.mostrarDebilitado(this.pokemon1.getName());
        			
        		}
        	}
        	else {
        		this.cont.mostrarAtaques(this.pokemon2.getName(), attack2.getName());
        		this.pokemon1.setCurrent_hp(this.pokemon1.getCurrent_hp()- this.compute_damage(attack2, this.pokemon2, this.pokemon1)); ;
        		if (this.pokemon1.getCurrent_hp() > 0) {
        			this.cont.mostrarAtaques(this.pokemon1.getName(), attack1.getName());
        			this.pokemon2.setCurrent_hp(this.pokemon2.getCurrent_hp() - this.compute_damage(attack1, this.pokemon1, this.pokemon2));
        		}
        		else {
        			this.cont.mostrarDebilitado(this.pokemon1.getName());
        			
        		}
        	
        		if(this.pokemon2.getCurrent_hp() <= 0) {
        			this.cont.mostrarDebilitado(this.pokemon2.getName());
        			
        		}
        	}
        }
        
        this.pokemon2.setCurrent_hp(Math.max(0, this.pokemon2.getCurrent_hp()));
        this.pokemon1.setCurrent_hp(Math.max(0, this.pokemon1.getCurrent_hp()));

        this.actual_turn += 1;
        this.cont.mostrarSiguiente();

	}

	public void print_current_status() {
		repaint();
		System.out.println(this.pokemon1.getName() + " has " + this.pokemon1.getCurrent_hp() + " left!");
		System.out.println(this.pokemon2.getName() + " has " + this.pokemon2.getCurrent_hp() + " left!");
	}
	private float compute_damage(Attack attack, Pokemon pok1, Pokemon pok2) {
		float aux = ((2*pok1.getLevel())/5) + 2;
		float powerFactor = aux * attack.getPower();
		if (attack.getCategory() == constants.PHYSICAL) {
			System.out.println("Physical attack!"+ pok1.getStats());
		    powerFactor *= (float)(pok1.getStats().get(constants.ATTACK))/(float)(pok2.getStats().get(constants.DEFENSE));
		}
		else if (attack.getCategory() == constants.SPECIAL){
			System.out.println("Special attack!"+ pok1.getStats());
		    powerFactor *= (float)(pok1.getStats().get(constants.SPATTACK))/(float)(pok2.getStats().get(constants.SPDEFENSE));
		}
		else {
			System.out.println("Boost attack!"+ pok1.getStats());
			switch(attack.getPower()) {
			case constants.ATTACKn:pok1.getStats().put(constants.ATTACK,2*pok1.getStats().get(constants.ATTACK));//Math.min(4*statorig,statact))
			case constants.DEFENSEn:pok1.getStats().put(constants.DEFENSE,2*pok1.getStats().get(constants.DEFENSE));
			case constants.SPATTACKn:pok1.getStats().put(constants.SPATTACK,2*pok1.getStats().get(constants.SPATTACK));
			case constants.SPDEFENSEn:pok1.getStats().put(constants.SPDEFENSE,2*pok1.getStats().get(constants.SPDEFENSE));
			case constants.SPEEDn:pok1.getStats().put(constants.SPEED,2*pok1.getStats().get(constants.SPEED));
			}
			
		}
		
		//print("POWER FACTOR", powerFactor)
		float damage_without_modifier = powerFactor/50.0f + 2.0f;
		//print("DAMAGE NO MODIFIERS", damage_without_modifier)
		//print("DAMAGE MODIFIER", self.compute_damage_modifier(attack, self.pokemon1, self.pokemon2))
		float finalDamage = 0;
		boolean fallar=false;
		Random rnd = new Random();
		//System.out.println(attack.getPrecision());
		double d = (float)(attack.getPrecision())/100.0;
		//System.out.println(d);
        if (rnd.nextDouble() > d) {
        	this.cont.mostrarFallo();
            fallar = true;
        }
        else {
        	finalDamage = damage_without_modifier * this.compute_damage_modifier(attack, pok1, pok2);
        	System.out.println("FINAL DAMAGE "+ finalDamage+ pok1.getName()+ " to "+ pok2.getName());
        }
		return (fallar||attack.getCategory() == constants.BOOST) ? 0 : finalDamage;
	}
	private float compute_damage_modifier(Attack attack, Pokemon pok1, Pokemon pok2) {
		//Compute STAB
		float stab = 1;
        if (attack.getType() == pok1.getType1() || (pok1.getType2()!=null && attack.getType() == pok1.getType2())) {
        	System.out.println("HAS STAB");
            stab = 1.5f;
        }
        //Compute Type effectiveness
        float effectiveness1 = constants.TYPE_CHART[pok2.getType1()][attack.getType()];
        float effectiveness2 = 1;
        if (pok2.getType2()!=null)
            effectiveness2 = constants.TYPE_CHART[pok2.getType2()][attack.getType()];
        float effectiveness_final = effectiveness1 * effectiveness2;
        
        //Compute Critical
        float critical = 1;
        Random rnd = new Random();
        if (rnd.nextDouble() <= 0.1) {
        	System.out.println(pok1.getName()+ " did a critical attack!!");
            critical = 1.5f;
            cont.mostrarCritico();
        }
        if(effectiveness_final<1)this.cont.mostrarNoEficaz();
        else if(effectiveness_final>1)this.cont.mostrarEficaz();
        else if(effectiveness_final==0.0)this.cont.mostrarNoAfecta(pok2.getName());
		return stab * effectiveness_final * critical;
	}
	public class Turn{
		private Command command1;
		
		private Command command2;

		public Turn() {
			 this.command1=null;
			 this.command2=null;
		 }
		public boolean can_start() {
			return this.command1 !=null && this.command2 !=null;
		}
		public Command getCommand1() {
			return command1;
		}
		public void setCommand1(Command command1) {
			this.command1 = command1;
		}
		public Command getCommand2() {
			return command2;
		}
		public void setCommand2(Command command2) {
			this.command2 = command2;
		}
	}
	public class Command{
		private Map<String,Integer> action;

		public Command(String action,int n) {
			 this.action=new HashMap<String,Integer>();
			 this.action.put(action, n);
		 }
		
	}
}
