public class DouleArray{
	public static void main(String[] args){
		int[][]a = new int[5][5];
		int x = 0;
		int y = 0;
		int max = 0;
		System.out.println("这个二维数组是 ");
		for(int i = 0;i < 5;i++){
			for(int j = 0;j < 5;j++){
				a[i][j] = (int)(Math.random()*100);
				System.out.println(a[i][j] + "\t");
				if(a[i][j] > max)
					max = a[i][j];
					x = i;
					y = j;
			}
			System.out.printl();
		}
		System.out.println("数组最大值是 ");
		System.out.println("最大值的坐标是: ["+(x+1)+"]["+(y+1)+"]");
	}
}