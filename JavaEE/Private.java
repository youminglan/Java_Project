/*使用private修饰属性
自身：是可以访问的
同包子类：不能继承
不同包子类：不能继承
同包类：不能访问
其他包类：不能访问

1. 属性通常使用private封装起来
2. 方法一般使用public用于被调用
3. 会被子类继承的方法，通常使用protected
4. package用的不多，一般新手会用package,因为还不知道有修饰符这个东西
*/
pakeage charactor;
import property.Weapon;

public class Private{
	private int id;
	String name;
	float hp;
	float armor;
    int moveSpeed;
	public void equip(Weapon w){
		
	}
}