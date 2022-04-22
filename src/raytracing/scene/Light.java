package raytracing.scene;

import raytracing.util.Color;
import raytracing.util.Vec3;

/**
 * Class representing a light.
 * 
 * @author Yannis Sauzeau
 */
public class Light {

    private final Vec3 position;
    private final Color diffuse;
    private final Color specular;

    public static Color AMBIENT_LIGHT = Color.BLACK;

    /**
     * Constructor.
     * 
     * @param position the position of the light
     * @param diffuse  the diffuse color of the light
     * @param specular the specular color of the light
     */
    public Light(Vec3 position, Color diffuse, Color specular) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    /**
     * 
     * @return the position of the light
     */
    public Vec3 getPosition() {
        return position;
    }

    /**
     * 
     * @return the diffuse color of the light
     */
    public Color getDiffuse() {
        return diffuse;
    }

    /**
     * 
     * @return the specular color of the light
     */
    public Color getSpecular() {
        return specular;
    }

}
