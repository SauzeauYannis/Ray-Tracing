public class Plane extends IntersectableObject {

    private Vec3f n;
    private float d;

    public Plane(Vec3f normal, float distance, Color color) {
        super(color);
        this.n = normal;
        this.d = distance;
    }

    @Override
    public double getIntersection(Vec3f P, Vec3f v) {
        if (n.dotProduct(v) == 0) return -1;

        float lambda = (-n.dotProduct(P) - d) / n.dotProduct(v);

        return (lambda > 0) ? lambda : -1;
    }

}
