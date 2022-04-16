package raytracing.scene;

import raytracing.utils.Color;
import raytracing.utils.Vec3f;

public class Light {

    private Vec3f position;
    private Color ambient;
    private Color diffuse;
    private Color specular;

    public Light(Vec3f position, Color ambient, Color diffuse, Color specular) {
        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public Color getSpecular() {
        return specular;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getAmbient() {
        return ambient;
    }


    public Vec3f getPosition() {
        return position;
    }

}
