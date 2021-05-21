public class SelectSort {
    public static void main(String[] args) {
        int[] a = new int[]{18, 62, 68, 82, 65, 9};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println(" ");
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[0]) {
                int temp = a[0];
                a[0] = a[i];
                a[i] = temp;
            }
        }
        for (int i = 2; i < a.length; i++) {
            if (a[i] < a[0]) {
                int temp = a[1];
                a[1] = a[0];
                a[i] = temp;
            }
        }
        for (int j = 0; j < a.length; j++) {
            for (int i = j + 1; i < a.length; i++) {
                if (a[j] < a[i]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                for (int sum = 0; sum < a.length; sum++)
                    System.out.println(a[i] + " ");
            }

        }
    }
}