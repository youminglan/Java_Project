public class Support extends Hero {
    public void heal(Hero) {
        System.out.println("治疗了");
    }

    public void heal(Hero h) {
        System.out.println(h + "治疗了" + Hero);
    }

    public void heal(Hero h, int hp) {
        System.out.println(h + "治疗了" + Hero + "的血量" + hp);
    }

    public static void main(String[] args) {
        Support s = new Support();
        Hero h = new Hero();
        h.name = "寒冰";
        h.hp = 5;

        s.heal(h, 100);

    }
}