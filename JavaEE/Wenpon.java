public class Wenpon extends Item{
	int damage;
	
	public static void main(String[] args){
		Wenpon infinityEdge = new Wenpon();
		infinityEdge.damage = 65;
		
		infinityEdge.name = "无尽之刃";
		infinityEdge.price = 3600;
	}
}