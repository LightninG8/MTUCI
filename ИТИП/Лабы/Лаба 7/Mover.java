import java.util.ArrayList;

public class Mover extends Thread {

    private Storage storageIn, storageOut;
    private Car car;

    public Mover (Storage in, Storage out, Car car) {
        this.storageIn = in;
        this.storageOut = out;
        this.car = car;
    }

    public void run() {
        while (!storageIn.isEmpty()) {

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (car.getStatus() == 0) {
                this.addToCarFromStorage();
            }
            else {
                this.addToStorageFromCar();
            }


        }
    }

    private void addToStorageFromCar () {
        if (car.isEmpty()) {
            car.changeStatus();
        }
        else {
            Product a = car.getFromCar();
            storageOut.addToStorage(a);
            car.removeFromCar(a);
        }

    }

    private void addToCarFromStorage () {
        Product a = storageIn.getEasyProduct();
        if (a.getWeight() + car.currentWeight > 150) {
            System.out.print("--The car is full with this objects");
            System.out.print(" of total weight ");
            System.out.print(car.currentWeight);
            System.out.println(" -------");
            ArrayList<Product> products = car.getProductsFromCar();
            for (int i = 0; i < products.size(); i++) {
                Product x = products.get(i);
                System.out.print(x.getName());
                System.out.print(" ");
                System.out.println(x.getWeight());
            }
            car.changeStatus();
        }
        else {
            car.pushToCar(a);
            storageIn.removeFromStorage(a);
        }
    }

}
