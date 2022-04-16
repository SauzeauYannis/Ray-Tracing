public class Sphere extends IntersectableObject {

    private Vec3f C;
    private float r;

    public Sphere(Vec3f center, float radius, Color color) {
        super(color);
        this.C = center;
        this.r = radius;
    }

    @Override
    public double getIntersection(Vec3f P, Vec3f v) {
        Vec3f CP = P.sub(C);

        float a = v.dotProduct(v);
        float b = 2 * v.dotProduct(CP);
        float c = CP.dotProduct(CP) - r * r;

        float delta = b * b - 4 * a * c;

        if (delta < 0) {
            return -1;
        } else if (delta == 0) {
            double lambda = -b / (2 * a);
            return (lambda > 0) ? lambda : -1;
        } else {
            double lambda1 = (-b - Math.sqrt(delta)) / (2 * a);
            double lambda2 = (-b + Math.sqrt(delta)) / (2 * a);

            if (lambda1 < 0 && 0 < lambda2) return lambda2;
            else if (0 < lambda1 && lambda1 < lambda2) return lambda1;
            else return -1;
        }
    }

}
