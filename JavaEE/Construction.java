/*什么是构造方法
方法名和类名一样（包括大小写）
没有返回类型
实例化一个对象的时候，必然调用构造方法
*/
public class Hero {
 
    String name;
 
    float hp;
 
    float armor;
 
    int moveSpeed;
 
    
    public Hero(String heroName,float heroHp,float heroArmor,int herMoveSpeed) {
        name = heroName;
		hp = heroHp;
		armor = heroArmor;
		moveSpeed = herMoveSpeed;
    }
     
    public static void main(String[] args) {
        
        Hero garen = new Hero("盖伦",616.28f,27.536f,350);
		
		System.out.println("英雄名称: "+garen.name+"\nHp: "+garen.hp+"\n护甲 "+garen.armor+"\n移动速度："+garen.moveSpeed);
    }
 
}