// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        HashTable<BarCode, Product> ht = new HashTable<BarCode, Product>();

        BarCode b1 = new BarCode("1238462048274");
        BarCode b2 = new BarCode("7391837462917");
        BarCode b3 = new BarCode("8937462840183");

        ht.put(b1,
                new Product("Papaya", 120, 1000));

        ht.put(b2,
                new Product("Marakuya", 150, 500));

        ht.put(b3,
                new Product("Avacado", 80, 3000));

        ht.put(b1,
                new Product("NoPapaya", 120, 1000));

        Product guessWho1 = ht.get(b1);
        Product guessWho2 = ht.get(new BarCode("0192847593872"));


        System.out.println(guessWho1.productName);
        if (guessWho2 == null) System.out.println("null");

        ht.remove(b1);

        if (ht.get(b1) == null) {
            System.out.print("We are now without Papaya");
        }
    }
}