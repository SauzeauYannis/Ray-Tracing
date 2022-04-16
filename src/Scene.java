import java.util.ArrayList;

public class Scene {

    private ArrayList<IntersectableObject> objects;
    private ArrayList<Light> lights;

    public Scene() {
        this.objects = new ArrayList<IntersectableObject>();
        this.lights = new ArrayList<Light>();

        this.objects.add(new Plane(new Vec3f(0.0f, 0.0f, -1.0f), 3.0f, Color.RED));
        this.objects.add(new Plane(new Vec3f(0.0f, 0.0f, 1.0f), 3.0f, Color.GREEN));
        this.objects.add(new Plane(new Vec3f(1.0f, 0.0f, 0.0f), 3.0f, Color.BLUE));
        this.objects.add(new Plane(new Vec3f(-1.0f, 0.0f, 0.0f), 3.0f, Color.YELLOW));
        this.objects.add(new Plane(new Vec3f(0.0f, 1.0f, 0.0f), 1.5f, Color.CYAN));
        this.objects.add(new Plane(new Vec3f(0.0f, -1.0f, 0.0f), 1.5f, Color.MAGENTA));

        this.objects.add(new Sphere(new Vec3f(0.0f, 0.0f, -1.0f), 0.25f, Color.WHITE));

        this.lights.add(new Light(
            new Vec3f(0.25f, 0.25f, 0.0f), 
            new Color(0.2f, 0.2f, 0.2f), 
            new Color(1.0f, 1.0f, 1.0f),
            new Color(0.8f, 0.8f, 0.8f))
        );
    }

    public Color findColor(Vec3f P, Vec3f v) {
        Color color = Color.BLACK;
        double lambdaMin = Double.MAX_VALUE;
        IntersectableObject closestObject = null;

        for (IntersectableObject object : this.objects) {
            double lambda = object.getIntersection(new Vec3f(P), v);

            if (lambda > 0 && lambda < lambdaMin) {
                lambdaMin = lambda;
                closestObject = object;
            }
        }

        if (closestObject != null) {
            Vec3f I = new Vec3f(P).add(new Vec3f(v).scale((float) lambdaMin));

            color = closestObject.getColor();

            for (Light light : this.lights) {
                boolean visible = true;

                for (IntersectableObject object : this.objects) {
                    double lambda = object.getIntersection(
                        new Vec3f(I), 
                        new Vec3f(light.getPosition()).sub(I)
                    );

                    if (0 < lambda - 0.0001f && lambda - 0.0001f < 1) visible = false;
                }

                if (!visible) color = Color.BLACK;
            }
        }

        return color;
    }
    
}
