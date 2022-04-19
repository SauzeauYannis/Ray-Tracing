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

    private static final Sphere sphere = new Sphere(new Vec3(0.0D, 0.0D, -1.0D), 0.5D, Color.DARK_GRAY);
    private static final Sphere sphere2 = new Sphere(new Vec3(1.0D, 0.25D, -1.0D), 0.25D, Color.RED, Color.WHITE, 10.0D, 0.1D, 0.75D, 1D);
    private static final Sphere sphere3 = new Sphere(new Vec3(-1.0D, -0.25D, -1.0D), 0.25D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1D);
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
        this.objects.add(sphere2);
        this.objects.add(sphere3);

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

        if (objectI == null)
            return Color.BLACK;

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
                v.normalize(); // v / ||v||

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

        if (objectI.getReflectionCoeff() > 0.0D) {
            Vec3 r = v.sub(nI.mul(2.0D * nI.dotProduct(v))); // r = v - 2 * nIDotV * nI
            color = color.add(findColor(I, r, depth - 1).multiply(objectI.getReflectionCoeff()));
        }

        if (objectI.getTransmissionCoeff() > 0.0D) {
            boolean inside = nI.dotProduct(v) > 0.0D;
            if (inside)
                nI = nI.mul(-1.0D);

            double eta = inside ? objectI.getRefractionIndex() : 1.0D / objectI.getRefractionIndex(); 
            double c1 = -nI.dotProduct(v); // c1 = nI . v
            double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
            Vec3 t = v.mul(eta).add(nI.mul(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
            color = color.add(findColor(I, t, depth - 1).multiply(objectI.getTransmissionCoeff()));
        }

        return color;

    }

}
