public class GoldenRatio {
    public static void main(String[] args) {
        int a = 1, b = 1;
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++)
                if (i % 2 == 0 && j % 2 == 0)
                    continue;
            if (Math.abs((double) i / j - 0.618) < Math.abs((double) a / b - 0.618)) {
                a = i;
                b = j;
            }
        }
    }
	 System.out.println(a+"/"+b+"="+(double)a/b);
}