package weapon;

public class Main {
    public static void main(String[] args) {
        Weapon sword = new Sword("Sword");
        Weapon bow = new Bow("Bow");
        Weapon magicWand = new MagicWand("Wand");

        sword.use();
        bow.use();
        magicWand.use();
    }
}
