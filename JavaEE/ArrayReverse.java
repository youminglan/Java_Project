public class ArrayReverse{
	public static void main(String[] args){
		int [] a;
		a = new int[5];
		a[0] = (int)(Math.random()*100);
		a[1] = (int)(Math.random()*100);
		a[2] = (int)(Math.random()*100);
		a[3] = (int)(Math.random()*100);
		a[4] = (int)(Math.random()*100);
		for(int i = 0;i < a.length;i++){
			int temp = a[i];
			a[i] = a[a.length-i];
			a[a.length-i] = temp;
		}
	}
}