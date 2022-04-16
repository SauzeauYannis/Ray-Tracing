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
        Color c = new Color(this.r + color.r, this.g + color.g, this.b + color.b);
        c.controlMinMax();

        return c;
    }

    public Color multiply(Color color) {
        Color c = new Color(this.r * color.r, this.g * color.g, this.b * color.b);
        c.controlMinMax();

        return c;
    }

    public Color multiply(double d) {
        Color c = new Color(this.r * (float) d, this.g * (float) d, this.b * (float) d);
        c.controlMinMax();

        return c;
    }

    private void controlMinMax() {
        this.b = Math.min(Math.max(this.b, 0.0f), 1.0f);
        this.g = Math.min(Math.max(this.g, 0.0f), 1.0f);
        this.r = Math.min(Math.max(this.r, 0.0f), 1.0f);
    }

}
