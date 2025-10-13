package weapon;

public abstract class Weapon {
    private String name;

    public Weapon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void use();
}
