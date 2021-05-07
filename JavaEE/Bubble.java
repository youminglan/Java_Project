/*第一步：从第一位开始，把相邻两位进行比较
如果发现前面的比后面的大，就把大的数据交换在后面，循环比较完毕后，最后一位就是最大的
第二步： 再来一次，只不过不用比较最后一位
*/
public class Bubble{
	public static void main(String[] args){
		int a [] = new int[]{18,62,68,82,65,9};
		for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println(" ");
		for(int j = 0;j < a.length;j++){
			for(int i = 0;i < a.length-j-1;i++){
				if(a[i] < a[i+1])
					int temp = a[i];
					a[i] = a[i+1];
					a[i+1] = temp;
			}
		}
		for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println(" ");
	}
}