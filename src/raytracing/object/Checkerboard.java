package raytracing.object;

import raytracing.util.Color;
import raytracing.util.Vec3;

/**
 * Class representing a checkerboard.
 * 
 * @author Yannis Sauzeau
 */
public class Checkerboard extends Plane {

    private final Color secondaryColor;

    /**
     * Constructor.
     * 
     * @param normal            the normal of the plane
     * @param distance          the distance from the origin of the plane to the
     *                          plane
     * @param primaryColor      the primary color of the checkerboard
     * @param secondaryColor    the secondary color of the checkerboard
     * @param specularColor     the specular color of the checkerboard
     * @param shininess         the shininess of the checkerboard
     * @param reflectionCoeff   the reflection coefficient of the checkerboard
     * @param transmissionCoeff the transmission coefficient of the checkerboard
     * @param refractionIndex   the refraction index of the checkerboard
     */
    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor, Color specularColor,
            double shininess,
            double reflectionCoeff, double transmissionCoeff, double refractionIndex) {
        super(normal, distance, primaryColor, specularColor, shininess, reflectionCoeff, transmissionCoeff,
                refractionIndex);
        this.secondaryColor = secondaryColor;
    }

    /**
     * Constructor.
     * 
     * @param normal          the normal of the plane
     * @param distance        the distance from the origin of the plane to the plane
     * @param primaryColor    the primary color of the checkerboard
     * @param secondaryColor  the secondary color of the checkerboard
     * @param specularColor   the specular color of the checkerboard
     * @param shininess       the shininess of the checkerboard
     * @param reflectionCoeff the reflection coefficient of the checkerboard
     */
    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor, Color specularColor,
            double shininess,
            double reflectionCoeff) {
        super(normal, distance, primaryColor, specularColor, shininess, reflectionCoeff);
        this.secondaryColor = secondaryColor;
    }

    /**
     * Constructor.
     * 
     * @param normal         the normal of the plane
     * @param distance       the distance from the origin of the plane to the plane
     * @param primaryColor   the primary color of the checkerboard
     * @param secondaryColor the secondary color of the checkerboard
     * @param specularColor  the specular color of the checkerboard
     * @param shininess      the shininess of the checkerboard
     */
    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor, Color specularColor,
            double shininess) {
        super(normal, distance, primaryColor, specularColor, shininess);
        this.secondaryColor = secondaryColor;
    }

    /**
     * Constructor.
     * 
     * @param normal         the normal of the plane
     * @param distance       the distance from the origin of the plane to the plane
     * @param primaryColor   the primary color of the checkerboard
     * @param secondaryColor the secondary color of the checkerboard
     */
    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor) {
        super(normal, distance, primaryColor);
        this.secondaryColor = secondaryColor;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Color getColor(Vec3 I) {
        Color primaryColor = super.getColor(I);

        return (Math.floor(I.getX()) + Math.floor(I.getZ())) % 2.0D == 0.0D ? primaryColor : secondaryColor;
    }

}
