public class Hero {
    String name;
    float hp;
    float armor;
    int moveSpeed;

    public Hero(String name, float hp) {
        this.name = name;
        this.hp = hp;
    }

    public Hero(String name, float hp, float armor, int moveSpeed) {
        this(name, hp);
        this.armor = armor;
        this.moveSpeed = moveSpeed;
    }

    public static void main(String[] args) {
        Hero hero = new Hero("提莫", 50l, 20l, 50);
    }
}