public class Quote1{
	String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度
     
    public Hero(){
         
    }
	public Hero(String name,float hp){
		this.name = name;
		this.hp =hp;
	}
	public void revive(Hero h){
		h = new Hero("提莫",383);
	}
	
	public static void main(String[] args){
		Hero teemo = new Hero("提莫",383)；
		teemo.hp = teemo.hp-400;
		teemo.revive(teemo);
	}
}