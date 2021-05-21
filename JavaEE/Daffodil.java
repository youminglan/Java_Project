public class Daffodil {
    public static void main(String[] args) {
        for (int i = 100; i < 1000; i++) {
            int a = i / 100;  //取百位
            int b = i / 10;    //取十位
            int c = i % 10;    //取个位
            if (a * a * a + b * b * b + c * c * c == i) {
                System.out.println(i + "是水仙花数");
            }
        }
    }
}