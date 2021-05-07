/*当一个属性被static修饰的时候，就叫做类属性，又叫做静态属性
当一个属性被声明成类属性，那么所有的对象，都共享一个值
与对象属性对比：
不同对象的 对象属性 的值都可能不一样。
比如盖伦的hp 和 提莫的hp 是不一样的。
但是所有对象的类属性的值，都是一样的
*/

public class Hero {
    public String name; //实例属性，对象属性，非静态属性
    protected float hp;
    static String copyright;//类属性,静态属性
     
    public static void main(String[] args) {
           Hero garen =  new Hero();
		   garen.name = "盖伦";
		   
		   Hero.copyright = "版权由Riot Games公司所有";
		   
		   System.out.println(garen.name);
		   System.out.println(garen.copyright);
		   
		   Hero teemo = new Hero();
		   teemo.name = "提莫";
           System.out.println(teemo.name);    
           System.out.println(teemo.copyright);
         
    }
     
}