package raytracing.scene;

import java.util.ArrayList;

import raytracing.object.Checkerboard;
import raytracing.object.IntersectableObject;
import raytracing.object.Plane;
import raytracing.object.Sphere;
import raytracing.utils.Color;
import raytracing.utils.Vec3;

public class Scene {

    private final ArrayList<IntersectableObject> objects;
    private final ArrayList<Light> lights;

    public Scene() {
        this.objects = new ArrayList<IntersectableObject>();
        this.lights = new ArrayList<Light>();
    }

    public void addObject(IntersectableObject object) {
        this.objects.add(object);
    }

    public void addLight(Light light) {
        this.lights.add(light);
    }

    public Color findColor(Vec3 P, Vec3 v, int depth) {
        if (depth == 0)
            return Light.AMBIENT_LIGHT;

        Color color = Light.AMBIENT_LIGHT;
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
            return Light.AMBIENT_LIGHT;

        Vec3 I = objectI.getIntersectionPoint(P, v, lambdaI); // I = P + lambda * v
        Vec3 nI = objectI.getNormal(I);

        boolean inside = nI.dotProduct(v) > 0.0D;
        if (inside)
            nI = nI.mul(-1.0D);

        color = objectI.getColor(I).multiply(Light.AMBIENT_LIGHT);

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

                Color diffuse = light.getDiffuse().multiply(objectI.getColor(I)).multiply(nIDotIS); // light.diffuse
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
            r.normalize(); // r / ||r||
            color = color.add(findColor(I, r, depth - 1).multiply(objectI.getReflectionCoeff()));
        }

        if (objectI.getTransmissionCoeff() > 0.0D) {
            double eta = inside ? objectI.getRefractionIndex() : 1.0D / objectI.getRefractionIndex();
            double c1 = -nI.dotProduct(v); // c1 = nI . v
            double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
            Vec3 t = v.mul(eta).add(nI.mul(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
            t.normalize(); // t / ||t||
            color = color.add(findColor(I, t, depth - 1).multiply(objectI.getTransmissionCoeff()));
        }

        return color;
    }

    public void createScene1() {
        this.addObject(new Plane(new Vec3(0.0D, 0.0D, -1.0D), 6.0D, Color.RED, Color.LIGHT_GRAY, 20.0D, 0.1D));
        this.addObject(new Plane(new Vec3(0.0D, 0.0D, 1.0D), 6.0D, Color.GREEN, Color.LIGHT_GRAY, 20.0D, 0.1D));
        this.addObject(new Plane(new Vec3(1.0D, 0.0D, 0.0D), 3.0D, Color.BLUE, Color.LIGHT_GRAY, 20.0D, 0.1D));
        this.addObject(new Plane(new Vec3(-1.0D, 0.0D, 0.0D), 3.0D, Color.YELLOW, Color.LIGHT_GRAY, 20.0D, 0.1D));
        this.addObject(new Plane(new Vec3(0.0D, 1.0D, 0.0D), 1.5D, Color.CYAN, Color.LIGHT_GRAY, 20.0D, 0.1D));
        this.addObject(new Plane(new Vec3(0.0D, -1.0D, 0.0D), 1.5D, Color.MAGENTA, Color.LIGHT_GRAY, 20.0D, 0.1D));

        this.addObject(
                new Sphere(new Vec3(0.0D, 0.0D, -4.0D), 1D, Color.DARK_GRAY, Color.WHITE, 10.0D, 0.0D, 0.75D, 1.1D));
        this.addObject(
                new Sphere(new Vec3(2.0D, 1.0D, -4.0D), 0.5D, Color.RED, Color.WHITE, 10.0D, 0.1D, 0.75D, 1.1D));
        this.addObject(
                new Sphere(new Vec3(-2.0D, -1.0D, -4.0D), 0.5D, Color.BLUE, Color.WHITE, 10.0D, 0.35D, 0.15D, 1.0D));

        this.addLight(new Light(new Vec3(1D, 1D, 0D), Color.WHITE, Color.LIGHT_GRAY));
    }

    // source: https://commons.wikimedia.org/wiki/File:Raytracing_reflection.png
    public void createScene2() {
        this.addObject(
                new Checkerboard(new Vec3(0.0D, 1.0D, 0.0D), 0D, Color.BLACK, Color.WHITE, Color.WHITE, 1000.0D, 0.3D));

        this.addObject(new Sphere(new Vec3(7, 3, -2), 3, Color.RED, Color.RED, 1000.0D, 0.3D));
        this.addObject(new Sphere(new Vec3(0, 3, -1), 3, Color.GREEN, Color.GREEN, 1000.0D, 0.6D));
        this.addObject(new Sphere(new Vec3(-6.5, 3, 0), 3, Color.BLUE, Color.BLUE, 1000.0D, 0.8D));

        this.addLight(new Light(new Vec3(0D, 20D, 20D), Color.WHITE, Color.LIGHT_GRAY));
        Light.AMBIENT_LIGHT = Color.BLACK;
    }

    // source:
    // https://forum.raytracerchallenge.com/thread/4/reflection-refraction-scene-description
    public void createScene3() {
        this.addObject(new Checkerboard(new Vec3(0.0D, 1.0D, 0.0D), 0D, new Color(0.35f, 0.35f, 0.35f),
                new Color(0.65f, 0.65f, 0.65f), Color.WHITE, 1000.0D, 0.3D));

        this.addObject(new Plane(new Vec3(0.0D, -1.0D, 0.0D), 5.0D, new Color(0.8f, 0.8f, 0.8f), Color.WHITE, 1000.0D));
        this.addObject(new Plane(new Vec3(1.0D, 0.0D, 0.0D), 5.0D, new Color(0.45f, 0.45f, 0.45f), Color.WHITE, 1000.0D,
                0.3D));
        this.addObject(
                new Plane(new Vec3(-1.0D, 0.0D, 0.0D), 5.0D, new Color(0.55f, 0.55f, 0.55f), Color.WHITE, 1000.0D,
                        0.3D));
        this.addObject(new Plane(new Vec3(0.0D, 0.0D, 1.0D), 5.0D, new Color(0.45f, 0.45f, 0.45f), Color.WHITE, 1000.0D,
                0.3D));
        this.addObject(
                new Plane(new Vec3(0.0D, 0.0D, -1.0D), 5.0D, new Color(0.55f, 0.55f, 0.55f), Color.WHITE, 1000.0D,
                        0.3D));

        this.addObject(new Sphere(new Vec3(4.6D, 0.4D, -1.0D), 0.4D, new Color(0.8f, 0.5f, 0.3f), Color.WHITE, 50.0D));
        this.addObject(new Sphere(new Vec3(4.7D, 0.3D, -0.4D), 0.3D, new Color(0.9f, 0.4f, 0.5f), Color.WHITE, 50.0D));
        this.addObject(new Sphere(new Vec3(-1.0D, 0.5D, -4.5D), 0.5D, new Color(0.4f, 0.9f, 0.6f), Color.WHITE, 50.0D));
        this.addObject(new Sphere(new Vec3(-1.7D, 0.3D, -4.7D), 0.3D, new Color(0.4f, 0.6f, 0.9f), Color.WHITE, 50.0D));

        this.addObject(new Sphere(new Vec3(-0.6D, 1.0D, -0.6D), 1.0D, new Color(1.0f, 0.3f, 0.2f), Color.WHITE, 5.0D));
        this.addObject(new Sphere(new Vec3(0.6D, 0.7D, 0.6D), 0.7D, new Color(0.0f, 0.0f, 0.2f), Color.WHITE, 300.0D,
                0.9D, 0.9D, 1.5D));
        this.addObject(new Sphere(new Vec3(-0.7D, 0.5D, 0.8D), 0.5D, new Color(0.0f, 0.2f, 0.0f), Color.WHITE, 300.0D,
                0.9D, 0.9D, 1.5D));
                
        this.addLight(new Light(new Vec3(-4.9D, 4.9D, 1.0D), Color.WHITE, Color.LIGHT_GRAY));
        Light.AMBIENT_LIGHT = Color.BLACK;
    }

}
