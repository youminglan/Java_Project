public class Copy{
	public static void main(String[] args){
		int a [] = new int[]{18,62,68,82,65,9};
		int b [] = new int[3];
		for(int i = 0;i < b.length;i++)
			b[j] = a [i];
		
		//方法二: System.arraycopy(src, srcPos, dest, destPos, length)
        //src: 源数组
        //srcPos: 从源数组复制数据的起始位置
        //dest: 目标数组
        //destPos: 复制到目标数组的启始位置
        //length: 复制的长度
		System.arraycopy(a,0,b,0,b,3);
		for(int i = 0;i < b.length;i++)
			System.out.println(b[i]+" ");
	}
}