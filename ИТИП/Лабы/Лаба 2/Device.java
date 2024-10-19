public abstract class Device {

    private int price;
    public String nameOfDevice;
    public String modelOfDevice;
    public static int countOfInstances;

    public Device(int price, String nameOfDevice, String modelOfDevice) {
        this.price = price;
        this.nameOfDevice = nameOfDevice;
        this.modelOfDevice = modelOfDevice;
        Device.countOfInstances++;
    }

    public Device() {
        this.price = -1;
        this.nameOfDevice = "Not assigned";
        this.modelOfDevice = "Not assigned";
        Device.countOfInstances++;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void buyDevice() {
        OOP.ourSum -= this.price;
        System.out.print("You have bought this very useful device ");
    }

    public void showDetails() {
        System.out.println("As you buying this stuff you have been agreed with our rules," +
                " do not cross the line");
    }


}