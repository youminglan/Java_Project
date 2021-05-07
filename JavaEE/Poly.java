package charactor;

public interface Mortal{
	public void die(){
		
	}
}

public class Hero{
	public String name;
	protected float hp;
	
	public void kill(Mortal m){
		System.out.println(name + " 放了一个大招");
		m.die();
	}
	
	public static void main(String[] args){
		
		Hero garen = new garen();
		garen.name = "盖伦";
		
		ADHero ad = new ADHero();
		ad.name = "艾希";
		
		APHero ap = new APHero();
		ap.name = "安妮";
		
		ADAPHero adap = new ADAPHero();
		adap.name = "库奇";
		
		garen.kill(ad);
		garen.kill(ap);
		garen.kill(adap);
		
	}
}