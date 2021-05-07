public class Hero{
	String name;
	float hp;
	float armor;
	int moveSpeed;
	
	public void setName1(String name){
		name = name;
	}
	public void setName2(String heroName){
		name = heroName;
	}
	public void setName3(String name){
		this.name = name;
	}
	public static void main(String[] args){
		Hero h =new Hero();
		
		h.setName1("teemo");
		System.out.println(h.name);
		
		h.setName2("garen");
		System.out.println(h.name);
		
		h.setName3("死歌");
		System.out.println(h.name);
	}
}