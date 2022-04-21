package raytracing.utils;

public class Color {

    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static final Color LIGHT_RED = new Color(1.0f, 0.5f, 0.5f);
    public static final Color LIGHT_GREEN = new Color(0.5f, 1.0f, 0.5f);
    public static final Color LIGHT_BLUE = new Color(0.5f, 0.5f, 1.0f);
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f);
    public static final Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f);
    public static final Color LIGHT_GRAY = new Color(0.75f, 0.75f, 0.75f);

    private float r;
    private float g;
    private float b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public byte getRed() {
        return (byte) (this.r * 255.0f);
    }

    public byte getGreen() {
        return (byte) (this.g * 255.0f);
    }

    public byte getBlue() {
        return (byte) (this.b * 255.0f);
    }

    public Color add(Color color) {
        return new Color(Math.min(this.r + color.r, 1.0f), Math.min(this.g + color.g, 1.0f), Math.min(this.b + color.b, 1.0f));
    }

    public Color multiply(Color color) {
        return new Color(this.r * color.r, this.g * color.g, this.b * color.b);
    }

    public Color multiply(double d) {
        return new Color(this.r * (float) d, this.g * (float) d, this.b * (float) d);
    }

}
