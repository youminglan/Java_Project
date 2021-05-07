/*GiantDragon 应该只有一只，通过私有化其构造方法，使得外部无法通过new 得到新的实例。
GiantDragon 提供了一个public static的getInstance方法.
单例模式又叫做 Singleton模式，指的是一个类，在一个JVM里，只有一个实例存在。
外部调用者通过该方法获取12行定义的对象，而且每一次都是获取同一个对象。 从而达到单例的目的。
这种单例模式又叫做饿汉式单例模式，无论如何都会创建一个实例
*/
pakeage charactor;

public class GaintDragon{
	private GaintDragon(){
		
	}
	private static GaintDragon instance = new GaintDragon();
	
	public static GaintDragon getInstance(){
		return instance;
	}
	 public static void main(String[] args) {
		 //通过new实例化会报错
//      GiantDragon g = new GiantDragon();
         
        //只能通过getInstance得到对象
		
		 GiantDragon g1 = GiantDragon.getInstance();
		 GiantDragon g2 = GiantDragon.getInstance();
         GiantDragon g3 = GiantDragon.getInstance();
		 //都是同一个对象
         System.out.println(g1==g2);
         System.out.println(g1==g3);
	 }
}