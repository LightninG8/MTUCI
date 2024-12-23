public class Product {

    private int weight;
    private String name;

    public Product(){
        this.weight = 1000000;
        this.name = "Izabel";
    }

    public Product(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return this.weight;
    }
    public String getName() {
        return this.name;
    }
}
