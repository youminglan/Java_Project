public class HeroInstance{
	public String name;
	protected float hp;
	
	private Hero(){
		
	}
	
	private static Hero instance;
	
	public static Hero getInstance(){
		if(instance==null){
			instance = new Hero();
		}
		return instance;
	}
	
}