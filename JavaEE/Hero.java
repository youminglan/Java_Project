public class Hero {
    //定义属性
    String name;
    float hp;
    float armor;
    int moveSpeed;

    void keng() {
        System.out.println("坑");
    }

    void addSpeed(int speed) {
        moveSpeed = moveSpeed + speed;
    }

    void legendary() {
        System.out.println("超神");
    }

    float getHp() {
        return hp;
    }

    float recovery(float blood) {
        System.out.println("回血：" + blood);
    }

    public static void main(String[] args) {
        Hero garen = new Hero();
        garen.name = "盖伦";
        garen.hp = 616.28f;
        garen.armor = 27.536f;
        garen.moveSpeed = 350;
    }
}