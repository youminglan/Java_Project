/*假设在使用switch的时候，不是使用枚举，而是使用int，
而int的取值范围就不只是1-4，
有可能取一个超出1-4之间的值，这样判断结果就似是而非了。（因为只有4个季节）
但是使用枚举，就能把范围死死的限定在这四个当中
*/

public class Enum {
    public enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }

    public static void main(String[] args) {
        Season season = Season.SPRING;
        switch (season) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }
    }
}