public class Boost {
    public static void main(String[] args) {
        int max = 0;
        int values[] = {18, 59, 62, 48, 32};
        for (int i : values) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
    }
}