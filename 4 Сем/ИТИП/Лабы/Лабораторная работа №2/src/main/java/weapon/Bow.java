package weapon;

public class Bow extends Weapon {
    public Bow(String name) {
        super(name);
    }

    @Override
    public void use() {
        System.out.println(getName() + " releases an arrow!");
    }
}
