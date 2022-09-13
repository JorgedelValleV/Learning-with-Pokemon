public class Attack{
	private String name;
	private int type;
	private String category;
	private int pp;
	private int maxpp;
	private int power;
	private int accuracy;
	public Attack(String name,int t, String category, int pp,int  power, int accuracy) {
		this.name = name;
		this.type = t;
		this.category = category;
		this.pp = pp;
		this.maxpp=pp;
		this.power = power;
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public int getPower() {
		return power;
	}
	public int getPP() {
		return pp;
	}
	public int getmaxPP() {
		return maxpp;
	}
	public String getCategory() {
		return category;
	}
	public void setPP(int n) {
		this.pp=n;
	}
	public int getPrecision() {
		return accuracy;
	}
}
