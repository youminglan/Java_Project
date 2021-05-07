public class Hero{
	String name;
	float hp;
	float armor;
	int moveSpeed;
	
	public Hero(String name){
		System.out.println("一个参数的构造方法");
		this.name = name;
	}
	
	public Hero(String name,float hp){
		this(name);
		System.out.println("一个参数的构造方法");
		this.hp = hp;
	}
	
	public static void main(String[] args){
		Hero teemo =  new Hero("提莫",383);
		System.out.println(teemo.name);
	}
}