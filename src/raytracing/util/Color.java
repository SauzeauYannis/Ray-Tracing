package raytracing.util;

/**
 * Class representing a color.
 * 
 * @author Yannis Sauzeau
 */
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

    /**
     * Constructor.
     * 
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     */
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * 
     * @return the red component
     */
    public byte getRed() {
        return (byte) (this.r * 255.0f);
    }

    /**
     * 
     * @return the green component
     */
    public byte getGreen() {
        return (byte) (this.g * 255.0f);
    }

    /**
     * 
     * @return the blue component
     */
    public byte getBlue() {
        return (byte) (this.b * 255.0f);
    }

    /**
     * Add a color to this color.
     * 
     * @param c the color to add
     * @return the resulting color
     */
    public Color add(Color c) {
        return new Color(Math.min(this.r + c.r, 1.0f), Math.min(this.g + c.g, 1.0f), Math.min(this.b + c.b, 1.0f));
    }

    /**
     * Multiply this color by a color.
     * 
     * @param c the color to multiply by
     * @return the resulting color
     */
    public Color multiply(Color c) {
        return new Color(this.r * c.r, this.g * c.g, this.b * c.b);
    }

    /**
     * Multiply this color by a scalar.
     * 
     * @param s the scalar
     * @return the resulting color
     */
    public Color multiply(double s) {
        return new Color(this.r * (float) s, this.g * (float) s, this.b * (float) s);
    }

}
