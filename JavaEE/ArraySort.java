import java.util.Arrays;

/*先把二维数组使用System.arraycopy进行数组复制到一个一维数组
然后使用sort进行排序
最后再复制回到二维数组
*/
public class ArraySort{
	public static void main(String[] args){
		int [][]a = new int[5][8];
		for(int i = 0;i < 5;i++){
			for(int j = 0;j < 5;j++){
				a[i][j] = (int)(Math.random()*100);
			}
		}
		
		int []b = new int [40];
		for(int i = 0;i < 5;i++){
			System.arraycopy(a[i],0,b,i*8,8);
		}
		Arrays.sort(b);
		for(int int i = 0;i < 5;i++){
			System.arraycopy(b,i*8,a[i],0,8);
		}
		for (int i = 0; i < 5; i++) {
		System.out.println(Arrays.toString(a[i]));
		}
	}
}