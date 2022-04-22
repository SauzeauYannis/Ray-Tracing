package raytracing.object;

import raytracing.util.Color;
import raytracing.util.Vec3;

/**
 * Class representing a checkerboard.
 * 
 * @author Yannis Sauzeau
 */
public abstract class IntersectableObject {

    private final Color color;
    private final Color specularColor;

    private final double shininess;
    private final double kr;
    private final double kt;
    private final double eta;

    /**
     * Constructor.
     * 
     * @param color             the color of the object
     * @param specularColor     the specular color of the object
     * @param shininess         the shininess of the object
     * @param reflectionCoeff   the reflection coefficient of the object
     * @param transmissionCoeff the transmission coefficient of the object
     * @param refractionIndex   the refraction index of the object
     */
    public IntersectableObject(Color color, Color specularColor, double shininess, double reflectionCoeff,
            double transmissionCoeff, double refractionIndex) {
        this.color = color;
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.kr = reflectionCoeff;
        this.kt = transmissionCoeff;
        this.eta = refractionIndex;
    }

    /**
     * Compute the intersection between the object and the ray.
     * 
     * @param P the origin of the ray
     * @param v the direction of the ray
     * @return the intersection
     */
    public abstract double getIntersection(Vec3 P, Vec3 v);

    /**
     * 
     * @param I the intersection
     * @return the normal of the object
     */
    public abstract Vec3 getNormal(Vec3 I);

    /**
     * 
     * @param P       the origin of the ray
     * @param v       the direction of the ray
     * @param lambdaI the distance from the origin of the ray to the intersection
     *                point
     * @return the intersection point
     */
    public Vec3 getIntersectionPoint(Vec3 P, Vec3 v, double lambdaI) {
        return P.add(v.mul(lambdaI));
    }

    /**
     * 
     * @param I the intersection point
     * @return the color of the intersection point
     */
    public Color getColor(Vec3 I) {
        return color;
    }

    /**
     * 
     * @return the specular color of the object
     */
    public Color getSpecularColor() {
        return specularColor;
    }

    /**
     * 
     * @return the shininess of the object
     */
    public double getShininess() {
        return shininess;
    }

    /**
     * 
     * @return the reflection coefficient of the object
     */
    public double getReflectionCoeff() {
        return kr;
    }

    /**
     * 
     * @return the transmission coefficient of the object
     */
    public double getTransmissionCoeff() {
        return kt;
    }

    /**
     * 
     * @return the refraction index of the object
     */
    public double getRefractionIndex() {
        return eta;
    }

}
