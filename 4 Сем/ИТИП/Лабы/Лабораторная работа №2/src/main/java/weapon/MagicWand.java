package weapon;

public class MagicWand extends Weapon {
    public MagicWand(String name) {
        super(name);
    }

    @Override
    public void use() {
        System.out.println(getName() + " creates a magical flash!");
    }
}
