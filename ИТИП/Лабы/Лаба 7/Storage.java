import java.util.ArrayList;
import java.util.Iterator;

public class Storage {

    private ArrayList<Product> products;

    public Storage() {
        products = new ArrayList<>();
    }

    public Product getEasyProduct () {
        Product answer = new Product();
        Iterator<Product> iter = this.products.iterator();
        while (iter.hasNext()) {
            Product a1 = iter.next();
            if (a1.getWeight() < answer.getWeight()) {
                answer = a1;
            }
        }
        return answer;
    }
    public ArrayList<Product> getProductsFromStorage() {
        return this.products;
    }
    public void addToStorage(Product a) {
        products.add(a);
    }
    public void removeFromStorage (Product a) {
        products.remove(a);
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }
}
