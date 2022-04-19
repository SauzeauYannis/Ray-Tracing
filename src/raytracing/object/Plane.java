package raytracing.object;

import raytracing.utils.Color;
import raytracing.utils.Vec3;

public class Plane extends IntersectableObject {

    private Vec3 n;
    private double d;

    public Plane(Vec3 normal, double distance, Color color, Color specularColor, double shininess,
            double reflectionCoeff, double transmissionCoeff, double refractionIndex) {
        super(color, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.n = normal;
        this.d = distance;
    }

    public Plane(Vec3 normal, double distance, Color color) {
        this(normal, distance, color, Color.WHITE, 10.0D, 0.20D, 0.0D, 1.0D);
    }

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

    @Override
    public Vec3 getNormal(Vec3 I) {
        return n;
    }

}
