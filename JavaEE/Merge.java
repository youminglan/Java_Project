public class Merge {
    public static void main(String[] args) {
        int a = (int) (Math.random() * 5 + 5);
        int b = (int) (Math.random() * 5 + 5);
        int c[] = new int[a.length + b.length];

        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(a, 0, c, a.length, b.length);

        System.out.println(Array.toString(c));
    }
}