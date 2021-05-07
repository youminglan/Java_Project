package charactor;
 
public class Hero {
    public String name; //实例属性，对象属性，非静态属性
    protected float hp;
    static String copyright;//类属性,静态属性
	
	public static void main(String[] args) {
           Hero garen =  new Hero();
		   garen.name = "盖伦";
		   
		   Hero teemo = new Hero();
		   teemo.name = "提莫";
		   
		   Hero.copyright = "版权由Riot Games公司所有";
		   garen.copyright = "版权由Riot Games公司所有";
		   
		   System.out.println(garen.name);
		   System.out.println(garen.copyright);
		   
           System.out.println(teemo.name);    
           System.out.println(teemo.copyright);
         
    }
     
}