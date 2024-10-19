public class Headphones extends Device {

    private boolean isWireless;

    public float frequencyRange;
    public float maxPower;

    public Headphones(boolean isWireless, float frequencyRange, float maxPower,
                      int price, String nameOfDevice, String modelOfDevice) {
        super(price, nameOfDevice, modelOfDevice);
        this.isWireless = isWireless;
        this.frequencyRange = frequencyRange;
        this.maxPower = maxPower;
    }

    public Headphones() {
        super();
        this.isWireless = false;
        this.frequencyRange = 0F;
        this.maxPower = 0F;
    }

    @Override
    public void buyDevice() {
        super.buyDevice();
        System.out.println("and there are a headphones!");
    }

    public void listenMusic() {
        System.out.println("Sorry, bro, this headphones can only play songs of Metallica");
    }

}