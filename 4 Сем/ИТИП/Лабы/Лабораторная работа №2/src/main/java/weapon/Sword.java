package weapon;

public class Sword extends Weapon {
    public Sword(String name) {
        super(name);
    }

    @Override
    public void use() {
        System.out.println(getName() + " delivers a slashing blow!");
    }
}
