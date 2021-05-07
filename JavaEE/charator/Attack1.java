package JavaEE.charator;

public class ADHero extends Hero {
	public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }
	public void attack(Hero... heros){
		for(int i = 0;i < heros.length;i++)
			System.out.println(name+"攻击了"+heros[i].name);
	}
	
	public static void main(String[] args){
		JavaEE.charator.ADHero bh = new JavaEE.charator.ADHero();
		bh.name = "赏金猎人";
		
		Hero h1 = new Hero();
		h1.name = "盖伦";
		
		Hero h2 = new Hero();
		h2.name = "提莫";
		
		bh.attack(h1);
		bh.attack(h1,h2);
	}
	
}