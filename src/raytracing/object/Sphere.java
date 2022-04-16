package raytracing.object;

import raytracing.utils.Color;
import raytracing.utils.Vec3;

public class Sphere extends IntersectableObject {

    private Vec3 C;
    private double r;

    public Sphere(Vec3 center, double radius, Color color, Color specularColor, double shininess) {
        super(color, specularColor, shininess);
        this.C = center;
        this.r = radius;
    }

    public Sphere(Vec3 center, double radius, Color color) {
        this(center, radius, color, Color.WHITE, 100.0D);
    }

    @Override
    public double getIntersection(Vec3 P, Vec3 v) {
        Vec3 CP = P.sub(C); // CP

        double a = v.dotProduct(v); // ||v||^2
        double b = 2.0D * v.dotProduct(CP); // 2 * v.CP
        double c = CP.dotProduct(CP) - r * r; // ||CP||^2 - r^2

        double delta = b * b - 4.0D * a * c; // b^2 - 4ac

        if (delta == 0.0D) {
            double lambda = -b / (2.0D * a); // -b / (2a)

            if (lambda > 0.0D)
                return lambda;
        } else {
            double lambda1 = (-b - Math.sqrt(delta)) / (2.0D * a); // (-b - sqrt(delta)) / (2a)
            double lambda2 = (-b + Math.sqrt(delta)) / (2.0D * a); // (-b + sqrt(delta)) / (2a)

            if (lambda1 < 0.0D && 0.0D < lambda2) {
                return lambda2;
            } else if (0.0D < lambda1 && lambda1 < lambda2) {
                return lambda1;
            }
        }

        return -1.0D;
    }

    @Override
    public Vec3 getNormal(Vec3 I) {
        Vec3 CI = I.sub(C); // CI

        return CI.div(CI.dotProduct(CI)); // CI / ||CI||^2
    }

}
