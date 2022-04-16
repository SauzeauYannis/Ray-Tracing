public abstract class IntersectableObject {

    private Color color;
    
    public IntersectableObject(Color color) {
        this.color = color;
    }

    public abstract double getIntersection(Vec3f P, Vec3f v);

    public Color getColor() {
        return color;
    }

}
