public class Hong{
	public static void main(String[] args){
		for(int i = 1,money = 1,int total = 0;i++;i <= 10){
			money *= 2;
			total += money;
			System.out.println("收入为："+total);
		}
	}
}