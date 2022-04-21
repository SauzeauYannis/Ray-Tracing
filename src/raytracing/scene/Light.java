package raytracing.scene;

import raytracing.utils.Color;
import raytracing.utils.Vec3;

public class Light {

    private final Vec3 position;
    private final Color diffuse;
    private final Color specular;

    public static Color AMBIENT_LIGHT = Color.DARK_GRAY;

    public Light(Vec3 position, Color diffuse, Color specular) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public Color getSpecular() {
        return specular;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public Vec3 getPosition() {
        return position;
    }

}
