public class Millionaire {
    public static void main(String[] args) {
        int p = 12000;
        int F = 0;
        float r = 0.2f;
        for (int i = 1; i < 100; i++) {
            F += p;
            F *= (1 + r);
            System.out.println("第" + i + "年" + F);
            if (F > 1000000)
                System.out.println("第" + i + "年成了百万富翁！");
            break;
        }
    }
}