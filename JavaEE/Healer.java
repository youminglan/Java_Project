pakeage charator;

public interface Healer {
    public void heal() {

    }
}

public class Support extend Hero implements Healter {
    @Override
    public void heal() {
        System.out.println("进行治疗");
    }
}