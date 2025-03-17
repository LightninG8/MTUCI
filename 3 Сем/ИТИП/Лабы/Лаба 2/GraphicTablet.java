public class GraphicTablet extends Device {

    public long width;
    public long height;

    public float tabletResolution;


    public GraphicTablet(long width, long height, float tabletResolution,
                          int price, String nameOfDevice, String modelOfDevice) {
        super(price, nameOfDevice, modelOfDevice);
        this.width = width;
        this.height = height;
        this.tabletResolution = tabletResolution;
    }

    public GraphicTablet() {
        super();
        this.width = 0;
        this.height = 0;
        this.tabletResolution = 0F;
    }

    @Override
    public void buyDevice() {
        super.buyDevice();
        System.out.println("and it is a graphic tablet!");
    }

    public void showNicePict() {
        String s = "..... (¯`v´¯)♥\n" +
                ".......•.¸.•´\n" +
                "....¸.•´\n" +
                "... (\n" +
                "☻/\n" +
                "/▌♥♥\n" +
                "/ \\ ♥♥\n";
        System.out.println(s);
    }

    public void showNotNicePict() {
        String s = "⠄⠄⣿⣿⣿⣿⠘⡿⢛⣿⣿⣿⣿⣿⣧⢻⣿⣿⠃⠸⣿⣿⣿⠄⠄⠄⠄⠄\n" +
                "⠄⠄⣿⣿⣿⣿⢀⠼⣛⣛⣭⢭⣟⣛⣛⣛⠿⠿⢆⡠⢿⣿⣿⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠸⣿⣿⢣⢶⣟⣿⣖⣿⣷⣻⣮⡿⣽⣿⣻⣖⣶⣤⣭⡉⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⢹⠣⣛⣣⣭⣭⣭⣁⡛⠻⢽⣿⣿⣿⣿⢻⣿⣿⣿⣽⡧⡄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⣼⣿⣿⣿⣿⣿⣿⣿⣿⣶⣌⡛⢿⣽⢘⣿⣷⣿⡻⠏⣛⣀⠄⠄\n" +
                "⠄⠄⠄⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠙⡅⣿⠚⣡⣴⣿⣿⣿⡆⠄\n" +
                "⠄⠄⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠄⣱⣾⣿⣿⣿⣿⣿⣿⠄\n" +
                "⠄⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿⣿⠄\n" +
                "⠄⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠣⣿⣿⣿⣿⣿⣿⣿⣿⣿⠄\n" +
                "⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠑⣿⣮⣝⣛⠿⠿⣿⣿⣿⣿⠄\n" +
                "⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⠄⠄⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠄";
        System.out.println(s);
    }
}