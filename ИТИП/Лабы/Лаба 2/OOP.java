public class OOP {

    public static int ourSum = 9000;
    public static void main(String[] args) {
        Keyboard k = new Keyboard();
        Keyboard K1 = new Keyboard(false, 100F, 50F, 1000, "Ryzen", "Norm");

        GraphicTablet g = new GraphicTablet();
        g.showNicePict();
        K1.buyDevice();
        System.out.println(OOP.ourSum);
        System.out.println(Device.countOfInstances);
    }
}

