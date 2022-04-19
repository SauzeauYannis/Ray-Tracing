package raytracing.object;

import raytracing.utils.Color;
import raytracing.utils.Vec3;

public abstract class IntersectableObject {

    private Color color;
    private Color specularColor;
    private Color reflectiveColor;
    private double shininess;

    public IntersectableObject(Color color, Color specularColor, Color reflectiveColor, double shininess) {
        this.color = color;
        this.specularColor = specularColor;
        this.reflectiveColor = reflectiveColor;
        this.shininess = shininess;
    }

    public abstract double getIntersection(Vec3 P, Vec3 v);

    public abstract Vec3 getNormal(Vec3 I);

    public Vec3 getIntersectionPoint(Vec3 P, Vec3 v, double lambdaI) {
        return P.add(v.mul(lambdaI));
    }

    public Color getColor() {
        return color;
    }

    public Color getSpecularColor() {
        return specularColor;
    }

    public double getShininess() {
        return shininess;
    }

    public Color getReflectiveColor() {
        return reflectiveColor;
    }

}
