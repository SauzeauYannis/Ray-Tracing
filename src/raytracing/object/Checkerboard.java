package raytracing.object;

import raytracing.utils.Color;
import raytracing.utils.Vec3;

public class Checkerboard extends Plane {

    private final Color secondaryColor;

    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor, Color specularColor,
            double shininess,
            double reflectionCoeff, double transmissionCoeff, double refractionIndex) {
        super(normal, distance, primaryColor, specularColor, shininess, reflectionCoeff, transmissionCoeff,
                refractionIndex);
        this.secondaryColor = secondaryColor;
    }

    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor, Color specularColor,
            double shininess,
            double reflectionCoeff) {
        super(normal, distance, primaryColor, specularColor, shininess, reflectionCoeff);
        this.secondaryColor = secondaryColor;
    }

    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor, Color specularColor,
            double shininess) {
        super(normal, distance, primaryColor, specularColor, shininess);
        this.secondaryColor = secondaryColor;
    }

    public Checkerboard(Vec3 normal, double distance, Color primaryColor, Color secondaryColor) {
        super(normal, distance, primaryColor);
        this.secondaryColor = secondaryColor;
    }

    @Override
    public Color getColor(Vec3 I) {
        Color primaryColor = super.getColor(I);

        return (Math.floor(I.getX()) + Math.floor(I.getZ())) % 2.0D == 0.0D ? primaryColor : secondaryColor;
    }

}
