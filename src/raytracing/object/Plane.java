package raytracing.object;

import raytracing.util.Color;
import raytracing.util.Vec3;

/**
 * Class representing a plane.
 * 
 * @author Yannis Sauzeau
 */
public class Plane extends IntersectableObject {

    private final Vec3 n;
    private final double d;

    /**
     * Constructor.
     * 
     * @param normal            the normal of the plane
     * @param distance          the distance from the origin of the plane to the
     *                          plane
     * @param color             the color of the plane
     * @param specularColor     the specular color of the plane
     * @param shininess         the shininess of the plane
     * @param reflectionCoeff   the reflection coefficient of the plane
     * @param transmissionCoeff the transmission coefficient of the plane
     * @param refractionIndex   the refraction index of the plane
     */
    public Plane(Vec3 normal, double distance, Color color, Color specularColor, double shininess,
            double reflectionCoeff, double transmissionCoeff, double refractionIndex) {
        super(color, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.n = normal;
        this.d = distance;
    }

    /**
     * Constructor.
     * 
     * @param normal          the normal of the plane
     * @param distance        the distance from the origin of the plane to the plane
     * @param color           the color of the plane
     * @param specularColor   the specular color of the plane
     * @param shininess       the shininess of the plane
     * @param reflectionCoeff the reflection coefficient of the plane
     */
    public Plane(Vec3 normal, double distance, Color color, Color specularColor, double shininess,
            double reflectionCoeff) {
        this(normal, distance, color, specularColor, shininess, reflectionCoeff, 0.0D, 1.0D);
    }

    /**
     * Constructor.
     * 
     * @param normal        the normal of the plane
     * @param distance      the distance from the origin of the plane to the plane
     * @param color         the color of the plane
     * @param specularColor the specular color of the plane
     * @param shininess     the shininess of the plane
     */
    public Plane(Vec3 normal, double distance, Color color, Color specularColor, double shininess) {
        this(normal, distance, color, specularColor, shininess, 0.0D, 0.0D, 1.0D);
    }

    /**
     * Constructor.
     * 
     * @param normal        the normal of the plane
     * @param distance      the distance from the origin of the plane to the plane
     * @param color         the color of the plane
     * @param specularColor the specular color of the plane
     */
    public Plane(Vec3 normal, double distance, Color color) {
        this(normal, distance, color, Color.WHITE, 10.0D, 0.0D, 0.0D, 1.0D);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public double getIntersection(Vec3 P, Vec3 v) {
        double nDotv = n.dotProduct(v); // n.v

        if (nDotv != 0.0D) {
            double nDotP = n.dotProduct(P); // n.P
            double lambdaI = (-nDotP - d) / nDotv; // (-n.P - d) / n.v

            if (lambdaI > 0.0001D)
                return lambdaI;
        }

        return -1.0D;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Vec3 getNormal(Vec3 I) {
        return n;
    }

}
