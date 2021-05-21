import java.util.Arrays;

//比较两个数组的内容是否一样

public class IsSame {
    public static void main(String[] args) {
        int[] a = new int[]{18, 62, 68, 82, 65, 9};
        int b[] = new int[]{18, 62, 68, 82, 65, 8};

        System.out.println(Array.equals(a, b));
    }
} 