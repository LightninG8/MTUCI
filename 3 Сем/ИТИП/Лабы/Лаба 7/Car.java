import java.util.ArrayList;

public class Car extends Storage {

    public int currentWeight;
    //private Storage storageIn, storageOut;
    private int status;

    public Car () {
        status = 0;
        currentWeight = 0;
    }

    public void changeStatus () {
        status = 1 - status;
    }
    public int getStatus(){
        return status;
    }

    public Product getFromCar () {
        Product a = super.getEasyProduct();
        return a;
    }
    public void removeFromCar(Product a) {
        currentWeight -= a.getWeight();
        super.removeFromStorage(a);
    }

    public ArrayList<Product> getProductsFromCar () {
        return super.getProductsFromStorage();
    }

    public void pushToCar (Product a) {
        currentWeight += a.getWeight();
        super.addToStorage(a);
    }
}
