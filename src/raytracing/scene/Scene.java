package raytracing.scene;

import java.util.ArrayList;

import raytracing.object.IntersectableObject;
import raytracing.object.Plane;
import raytracing.object.Sphere;
import raytracing.utils.Color;
import raytracing.utils.Vec3;

public class Scene {

    private static final Plane backWall = new Plane(new Vec3(0.0D, 0.0D, -1.0D), 3.0D, Color.RED);
    private static final Plane frontWall = new Plane(new Vec3(0.0D, 0.0D, 1.0D), 3.0D, Color.GREEN);
    private static final Plane leftWall = new Plane(new Vec3(1.0D, 0.0D, 0.0D), 3.0D, Color.BLUE);
    private static final Plane rightWall = new Plane(new Vec3(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW);
    private static final Plane floor = new Plane(new Vec3(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN);
    private static final Plane ceiling = new Plane(new Vec3(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA);

    private static final Sphere sphere = new Sphere(new Vec3(0.0D, 0.0D, -1.0D), 0.25D, Color.RED);

    private static final Light light = new Light(new Vec3(0.25D, 0.25D, 0.25D), Color.WHITE, Color.LIGHT_GRAY);

    private ArrayList<IntersectableObject> objects;
    private ArrayList<Light> lights;

    public Scene() {
        this.objects = new ArrayList<IntersectableObject>();
        this.lights = new ArrayList<Light>();

        this.objects.add(backWall);
        this.objects.add(frontWall);
        this.objects.add(leftWall);
        this.objects.add(rightWall);
        this.objects.add(ceiling);
        this.objects.add(floor);
        this.objects.add(sphere);

        this.lights.add(light);
    }

    public Color findColor(Vec3 P, Vec3 v) {
        Color color = Color.BLACK;
        double lambdaI = Double.MAX_VALUE;
        IntersectableObject objectI = null;

        for (IntersectableObject object : this.objects) {
            double lambdaObj = object.getIntersection(P, v);

            if (lambdaObj > 0.0D && lambdaObj < lambdaI) {
                lambdaI = lambdaObj;
                objectI = object;
            }
        }

        if (objectI != null) {
            Vec3 I = objectI.getIntersectionPoint(P, v, lambdaI);
            Vec3 nI = objectI.getNormal(I);

            color = objectI.getColor().multiply(Light.AMBIENT_LIGHT);

            for (Light light : this.lights) {
                boolean visible = true;

                for (IntersectableObject object : this.objects) {
                    Vec3 IS = light.getPosition().sub(I);
                    double lambdaObj = object.getIntersection(I, IS);

                    if (0.0D < lambdaObj && lambdaObj < 1.0D)
                        visible = false;
                }

                if (visible) {
                    nI.normalize();

                    Vec3 lightDirection = light.getPosition().sub(I);
                    lightDirection.normalize();

                    double weight = Math.max(nI.dotProduct(lightDirection), 0.0D);
                    Vec3 h = lightDirection.add(v);
                    h.normalize();
                    double shininess = Math.max(nI.dotProduct(h), 0.0D);

                    Color diffuse = light.getDiffuse().multiply(objectI.getColor()).multiply(weight);
                    Color specular = light.getSpecular().multiply(objectI.getSpecularColor()).multiply(Math.pow(shininess, objectI.getShininess()));
                    
                    color = color.add(diffuse).add(specular);
                } else {
                    color = Color.BLACK;
                }
            }
        }

        return color;
    }

}
