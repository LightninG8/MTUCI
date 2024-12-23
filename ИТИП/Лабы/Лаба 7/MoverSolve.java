// У вас есть склад с товарами, которые нужно перенести на другой склад. У
// каждого товара есть свой вес. На складе работают 3 грузчика. Грузчики
// могут переносить товары одновременно, но суммарный вес товаров,
// которые они переносят, не может превышать 150 кг. Как только грузчики
// соберут 150 кг товаров, они отправятся на другой склад и начнут
// разгружать товары.
// Напишите программу на Java, используя многопоточность, которая
// реализует данную ситуацию
// Использование Thread: Создайте классы Товар, Склад, и
// Грузчик. Каждый грузчик должен быть представлен в виде
// отдельного потока.

public class MoverSolve {

    public static void main (String[] args) {

        Product chair = new Product(61, "Ikea nice chair");
        Product yellowChair = new Product(55, "New Ikea yellow chair");
        Product greenTable = new Product(20, "Table that comfortable in use");
        Product refregerator = new Product(100, "Cold");
        Product sofa = new Product(150, "Pretty heavy sofa");

        Storage in = new Storage();
        in.addToStorage(chair);
        in.addToStorage(yellowChair);
        in.addToStorage(yellowChair);
        in.addToStorage(yellowChair);
        in.addToStorage(greenTable);
        in.addToStorage(refregerator);
        in.addToStorage(sofa);

        Storage out = new Storage();

        Car mark = new Car();

        for (Product x : in.getProductsFromStorage()) {
            System.out.print(x.getName());
            System.out.print(" ");
            System.out.println(x.getWeight());
        }

        Mover firstMover = new Mover(in, out, mark);
        Mover secondMover = new Mover(in, out, mark);
        Mover thirdMover = new Mover(in, out, mark);

        firstMover.start();
        secondMover.start();
        thirdMover.start();

        try{
            firstMover.join();
            secondMover.join();
            thirdMover.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("------Our StorageOut------");
        for (Product x : out.getProductsFromStorage()) {
            System.out.print(x.getName());
            System.out.print(" ");
            System.out.println(x.getWeight());
        }
        System.out.println("----Our StorageIn----");
        for (Product x : in.getProductsFromStorage()) {
            System.out.print(x.getName());
            System.out.print(" ");
            System.out.println(x.getWeight());
        }
    }
}
