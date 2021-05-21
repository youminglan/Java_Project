/*如果一个变量是基本类型
比如 int hp = 50;
我们就直接管hp叫变量
=表示赋值的意思。
如果一个变量是类类型
比如 Hero h = new Hero();
我们就管h叫做引用。
=不再是赋值的意思
=表示指向的意思
比如 Hero h = new Hero();
这句话的意思是
引用h，指向一个Hero对象
*/
public class Quote {
    String name; // 姓名

    float hp; // 血量

    float armor; // 护甲

    int moveSpeed; // 移动速度

    public Hero(String name, float hp) {
        this.name = name;
        this.hp = hp;

        public void attack (Hero hero,int damage){
            hero.hp = hero.hp - damage;
        }

        public void static main(String[]args){
            Hero teemo = new Hero("提莫", 383);
            Hero garen = new Hero("盖伦", 616)；
            garen.attack(teemo, 100);
            System.out.println(teemo.hp);
        }
    }