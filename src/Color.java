public class Color {

    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f);
    public static final Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f);
    public static final Color LIGHT_GRAY = new Color(0.75f, 0.75f, 0.75f);

    private float b;
    private float g;
    private float r;

    public Color(float r, float g, float b) {
        this.b = b;
        this.g = g;
        this.r = r;
    }

    public byte getBlue() {
        return (byte) (this.b * 255);
    }

    public byte getGreen() {
        return (byte) (this.g * 255);
    }

    public byte getRed() {
        return (byte) (this.r * 255);
    }

}
