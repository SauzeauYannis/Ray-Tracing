package raytracing.object;

import raytracing.utils.Color;
import raytracing.utils.Vec3;

public abstract class IntersectableObject {

    private Color color;
    private Color specularColor;
    private double shininess;
    private double kr;
    private double kt;
    private double eta;

    public IntersectableObject(Color color, Color specularColor, double shininess, double reflectionCoeff, double transmissionCoeff, double refractionIndex) {
        this.color = color;
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.kr = reflectionCoeff;
        this.kt = transmissionCoeff;
        this.eta = refractionIndex;
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

    public double getReflectionCoeff() {
        return kr;
    }

    public double getTransmissionCoeff() {
        return kt;
    }

    public double getRefractionIndex() {
        return eta;
    }

}
