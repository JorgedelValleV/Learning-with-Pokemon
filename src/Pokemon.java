

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class Pokemon {
	private Integer type2;
	private Integer type1;
	private int level;
	private String name;
	private List<Attack> attacks;
	private Map<String, Integer>stats;
	private Map<String, Integer>baseStats;
	private Map<String, Integer>ev;
	private Map<String, Integer>iv;
	private int current_status;//quemar,paralizar,dormir,envenenar
	private float current_hp;
	private int nature;
	
	public Pokemon(String name, int level, Integer type1, Integer type2) {
		this.type2 = type2;
		this.type1 = type1;
		this.level = level;
		this.name = name;
		this.attacks = new ArrayList<Attack>();
		this.stats = new HashMap<String, Integer>();
		this.baseStats = new HashMap<String, Integer>();
		this.ev = new HashMap<String, Integer>();
		this.iv = new HashMap<String, Integer>();
		this.current_status = 0;
		this.current_hp = 0;
		this.nature = 0;
	}

	public void setBaseStats(Map<String, Integer> baseStats) {
		this.baseStats = baseStats;
	}

	public void setEv(Map<String, Integer> ev) {
		this.ev = ev;
	}

	public void setIv(Map<String, Integer> iv) {
		this.iv = iv;
	}

	public Integer getType2() {
		return type2;
	}

	public int getType1() {
		return type1;
	}

	public Map<String, Integer> getStats() {
		return stats;
	}
	
	public Map<String, Integer> getBaseStats() {
		return baseStats;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<Attack> attacks) {
		this.attacks = attacks;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCurrent_hp() {
		return current_hp;
	}
	public void setCurrent_hp(float current_hp) {
		this.current_hp = current_hp;
	}

	public void compute_stats() {
		this.stats.put(constants.HP, this.compute_hp_stat());
		this.stats.put(constants.ATTACK, this.compute_standard_stat(constants.ATTACK,constants.ATTACKn));
		this.stats.put(constants.DEFENSE, this.compute_standard_stat(constants.DEFENSE,constants.DEFENSEn));
		this.stats.put(constants.SPATTACK, this.compute_standard_stat(constants.SPATTACK,constants.SPATTACKn));
		this.stats.put(constants.SPDEFENSE, this.compute_standard_stat(constants.SPDEFENSE,constants.SPDEFENSEn));
		this.stats.put(constants.SPEED, this.compute_standard_stat(constants.SPEED,constants.SPEEDn));
	}
	private Integer compute_standard_stat(String stat, int statn) {
		int value1 = (2*this.baseStats.get(stat)+this.iv.get(stat)+(this.ev.get(stat)/4))*this.level;
		return  (int)(((int)(value1/100) + 5 ) * constants.NATURE_MATRIX[this.nature][statn]);
	}
	private Integer compute_hp_stat() {
		int value1 = (2*this.baseStats.get(constants.HP)+this.iv.get(constants.HP)+(this.ev.get(constants.HP)/4))*this.level;
		return  (int)(((int)(value1/100)) +this.level+10);
	}
}
