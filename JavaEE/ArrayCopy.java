import java.util.Arrays;

public class ArrayCopy {
    public static void main(String[] args) {
        int[] a = new int[]{18, 62, 68, 56, 123, 9};
        int[] b = Arrays.copyOfRange(a, 0, 3);

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
    }
}