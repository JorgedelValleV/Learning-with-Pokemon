
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class constants {
	//Pokemon stats
	public final static String HP = "hp";
	public final static String ATTACK = "attack";
	public final static String DEFENSE = "defense";
	public final static String SPATTACK = "spattack";
	public final static String SPDEFENSE = "spdefense";
	public final static String SPEED = "speed";

	public final static int HPn = 0;
	public final static int ATTACKn = 1;
	public final static int DEFENSEn = 2;
	public final static int SPATTACKn = 3;
	public final static int SPDEFENSEn = 4;
	public final static int SPEEDn = 5;
	
	//Commands
	public final static String DO_ATTACK = "attack";
	public final static String DO_ATTACK_SELECTION = "selected_attack";
	public final static String DO_CHANGE = "change_pokemon";
	public final static String DO_ITEM = "use_item";

	//Attack categories
	public final static String PHYSICAL = "physical";
	public final static String SPECIAL = "special";
	public final static String BOOST = "boost";
	public final static String[] NATURES = {
	    "Hardy",
	    "Lonely",
	    "Brave",
	    "Adamant",
	    "Naughty",
	    "Bold",
	    "Docile",
	    "Relaxed",
	    "Impish",
	    "Lax",
	    "Timid",
	    "Hasty",
	    "Serious",
	    "Jolly",
	    "Naive",
	    "Modest",
	    "Mild",
	    "Quiet",
	    "Bashful",
	    "Rash",
	    "Calm",
	    "Gentle",
	    "Sassy",
	    "Careful",
	    "Quirky"
	};
	/*Map< String,Integer> hm = new HashMap< String,Integer>(); 
	hm.put("HP", new Integer(1)); 
	hm.put(ATTACK, new Integer(1)); 
	hm.put(DEFENSE, new Integer(1)); 
	hm.put(SPATTACK, new Integer(1)); 
	hm.put(SPDEFENSE, new Integer(1)); 
	hm.put(SPEED, new Integer(1)); 
	
	Map<String,Float>[] NATURE_MATRIX = {
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1.1, DEFENSE: 0.9, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1.1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 0.9},
	    {HP: 1, ATTACK: 1.1, DEFENSE: 1, SPATTACK: 0.9, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1.1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 0.9, SPEED: 1},
	    
	    {HP: 1, ATTACK: 0.9, DEFENSE: 1.1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1.1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 0.9},
	    {HP: 1, ATTACK: 1, DEFENSE: 1.1, SPATTACK: 0.9, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1.1, SPATTACK: 1, SPDEFENSE: 0.9, SPEED: 1},
	    
	    {HP: 1, ATTACK: 0.9, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1.1},
	    {HP: 1, ATTACK: 1, DEFENSE: 0.9, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1.1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 0.9, SPDEFENSE: 1, SPEED: 1.1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 0.9, SPEED: 1.1},
	    
	    {HP: 1, ATTACK: 0.9, DEFENSE: 1, SPATTACK: 1.1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 0.9, SPATTACK: 1.1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1.1, SPDEFENSE: 1, SPEED: 0.9},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1.1, SPDEFENSE: 0.9, SPEED: 1},
	    
	    {HP: 1, ATTACK: 0.9, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1.1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 0.9, SPATTACK: 1, SPDEFENSE: 1.1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1.1, SPEED: 0.9},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 0.9, SPDEFENSE: 1.1, SPEED: 1},
	    {HP: 1, ATTACK: 1, DEFENSE: 1, SPATTACK: 1, SPDEFENSE: 1, SPEED: 1},
	};*/
	public final static float[][]NATURE_MATRIX= {
		{1,		1, 		1, 		1, 		1, 		1},
		{1,		1.1f, 	0.9f, 	1, 		1, 		1},
		{1,		1.1f, 	1, 		1, 		1, 		0.9f},
		{1,		1.1f, 	1, 		0.9f,	1, 		1},
		{1,		1.1f, 	1, 		1, 		0.9f, 	1},
		{1,		0.9f, 	1.1f, 	1, 		1, 		1},
		{1,		1, 		1, 		1, 		1, 		1},
		{1,		1, 		1.1f, 	1, 		1, 		0.9f},
		{1,		1, 		1.1f, 	0.9f, 	1, 		1},
		{1,		1, 		1.1f, 	1,		0.9f, 	1},
		{1,		0.9f, 	1, 		1, 		1, 		1.1f},
		{1,		1, 		0.9f, 	1, 		1, 		1.1f},
		{1,		1, 		1, 		1, 		1, 		1},
		{1,		1, 		1, 		0.9f, 	1, 		1.1f},
		{1,		1, 		1, 		1, 		0.9f,	1.1f},
		{1,		0.9f, 	1, 		1.1f, 	1, 		1},
		{1,		1, 		0.9f, 	1.1f, 	1, 		1},
		{1,		1, 		1, 		1.1f, 	1, 		0.9f},
		{1,		1, 		1, 		1, 		1, 		1},
		{1,		1, 		1, 		1.1f, 	0.9f, 	1},
		{1,		0.9f, 	1, 		1, 		1.1f, 	1},
		{1,		1, 		0.9f, 	1, 		1.1f, 	1},
		{1,		1, 		1, 		1, 		1.1f, 	0.9f},
		{1,		1, 		1, 		0.9f, 	1.1f, 	1},
		{1,		1, 		1, 		1, 		1, 		1}
	};
	public final static String[] TYPES = {
	    "Normal",//0
	    "Fighting",//1
	    "Flying",//2
	    "Poison",//3
	    "Ground",//4
	    "Rock",//5
	    "Bug",//6
	    "Ghost",//7
	    "Steel",//8
	    "Fire",//9
	    "Water",//10
	    "Grass",//11
	    "Electric",//12
	    "Psychic",//13
	    "Ice",//14
	    "Dragon",//15
	    "Dark",//16
	    "Fairy"//17
	};
	
	public final static Color[] colorillo = {
			Color.decode("#c1bbb1"),//0
		    Color.decode("#944e39"),//1
		    Color.decode("#95a7f3"),//2
		    Color.decode("#974998"),//3
		    Color.decode("#d9ba64"),//4
		    Color.decode("#b9a156"),//5
		    Color.decode("#aaba1f"),//6
		    Color.decode("#5f5fb1"),//7
		    Color.decode("#babac8"),//8
		    Color.red,//9
		    Color.blue,//10
		    Color.decode("#6abc2e"),//11
		    Color.yellow,//12
		    Color.pink,//13
		    Color.decode("#a2e7fd"),//14
		    Color.decode("#7860e2"),//15
		    Color.BLACK,//16
		    Color.decode("#f1abf1")//17
		};

	public final static float[][]TYPE_CHART = {
	    {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	    {1,1,2,1,1,.5f,.5f,1,1,1,1,1,1,2,1,1,.5f,2},
	    {1,.5f,1,1,0,2,.5f,1,1,1,1,.5f,2,1,2,1,1,1},
	    {1,.5f,1,.5f,2,1,.5f,1,1,1,1,.5f,1,2,1,1,1,.5f},
	    {1,1,1,.5f,1,.5f,1,1,1,1,2,2,0,1,2,1,1,1},
	    {.5f,2,.5f,.5f,2,1,1,1,2,.5f,2,2,1,1,1,1,1,1},
	    {1,.5f,2,1,.5f,2,1,1,1,2,1,.5f,1,1,1,1,1,1},
	    {0,0,1,.5f,1,1,.5f,2,1,1,1,1,1,1,1,1,2,1},
	    {.5f,2,.5f,0,2,.5f,.5f,1,.5f,2,1,.5f,1,.5f,.5f,.5f,1,.5f},
	    {1,1,1,1,2,2,.5f,1,.5f,.5f,2,.5f,1,1,.5f,1,1,.5f},
	    {1,1,1,1,1,1,1,1,.5f,.5f,.5f,2,2,1,.5f,1,1,1},
	    {1,1,2,2,.5f,1,2,1,1,2,.5f,.5f,.5f,1,2,1,1,1},
	    {1,1,.5f,1,2,1,1,1,.5f,1,1,1,.5f,1,1,1,1,1},
	    {1,.5f,1,1,1,1,2,2,1,1,1,1,1,.5f,1,1,2,1},
	    {1,2,1,1,1,2,1,1,2,2,1,1,1,1,.5f,1,1,1},
	    {1,1,1,1,1,1,1,1,1,.5f,.5f,.5f,.5f,1,2,2,1,2},
	    {1,2,1,1,1,1,2,.5f,1,1,1,1,1,0,1,1,.5f,2},
	    {1,.5f,1,2,1,1,.5f,1,2,1,1,1,1,1,1,0,.5f,1}
	};
}
	