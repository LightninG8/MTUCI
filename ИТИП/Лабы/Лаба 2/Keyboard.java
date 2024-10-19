public class Keyboard extends Device {

    private boolean isLighted;

    public float width;
    public float height;
    private String printedLine;

    public Keyboard(boolean isLighted, float width, float height,
                    int price, String nameOfDevice, String modelOfDevice) {
        super(price, nameOfDevice, modelOfDevice);
        this.isLighted = isLighted;
        this.width = width;
        this.height = height;
        printedLine = "";
    }

    public Keyboard() {
        super();
        this.isLighted = false;
        this.width = 0F;
        this.height = 0F;
        printedLine = "";
    }

    @Override
    public void buyDevice() {
        super.buyDevice();
        System.out.println("and it is a keyboard!");
    }

    public String getPrintedLine() {
        return this.printedLine;
    }

    public void editPrintedLine(String s) {
        if (!s.isEmpty()) {
            this.printedLine = this.printedLine.substring(0, printedLine.length()-1);
        }
        else {
            this.printedLine += s;
        }
    }

    public void editPrintedLine() {
        if (!this.printedLine.isEmpty()) {
            this.printedLine = this.printedLine.substring(0, printedLine.length()-1);
        }
    }
}