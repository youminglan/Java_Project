import java.util.Scanner;

public class Scanner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        System.out.println("第一个整数：" + a);
        int b = s.nextInt();
        System.out.println("第二个整数：" + b);

        float tall = new Scanner;
        System.out.println("请输入身高：" + tall);
        float kilogram = new Scanner;
        System.out.println("请输入体重：" + kilogram)；
        float bmi = new Scanner;
        bmi = kilogram / (tall * tall);
        System.out.println("BMI为：" + bmi)；
    }
}