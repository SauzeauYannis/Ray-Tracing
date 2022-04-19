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

    public Color findColor(Vec3 P, Vec3 v, int depth) {
        if (depth == 0)
            return Color.BLACK;

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

        if (objectI == null) {
            return Color.BLACK;
        } else {
            Vec3 I = objectI.getIntersectionPoint(P, v, lambdaI); // I = P + lambda * v
            Vec3 nI = objectI.getNormal(I);

            color = objectI.getColor().multiply(Light.AMBIENT_LIGHT);

            for (Light light : this.lights) {
                Vec3 IS = light.getPosition().sub(I);

                boolean visible = true;

                for (IntersectableObject object : this.objects) {
                    double lambdaObj = object.getIntersection(I, IS);

                    if (0.0D < lambdaObj && lambdaObj < 1.0D)
                        visible = false;
                }

                if (visible) {
                    nI.normalize(); // nI / ||nI||
                    IS.normalize(); // IS / ||IS||
                    v.normalize();

                    double nIDotIS = Math.max(nI.dotProduct(IS), 0.0D); // niDotIS = max(nI . IS, 0)

                    Vec3 r = IS.sub(nI.mul(2.0D * nIDotIS)); // r = IS - 2 * nIDotIS * nI
                    double rDotV = Math.max(r.dotProduct(v), 0.0D); // rDotV = max(r . v, 0)

                    Color diffuse = light.getDiffuse().multiply(objectI.getColor()).multiply(nIDotIS); // light.diffuse
                                                                                                       // * object.color
                                                                                                       // * niDotIS
                    Color specular = light.getSpecular().multiply(objectI.getSpecularColor())
                            .multiply(Math.pow(rDotV, objectI.getShininess())); // light.specular *
                                                                                // object.specularColor * pow(rDotV,
                                                                                // object.shininess)

                    color = color.add(diffuse).add(specular);
                }
            }

            Vec3 r = v.sub(nI.mul(2.0D * nI.dotProduct(v))); // r = v - 2 * nIDotV * nI

            color = color.add(objectI.getReflectiveColor().multiply(findColor(I, r, depth - 1))); // object.reflectiveColor
                                                                                                  // *
                                                                                                  // findColor(I, r,
                                                                                                  // depth - 1)

            return color;
        }
    }

}
