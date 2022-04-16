package raytracing;

import raytracing.scene.Scene;
import raytracing.utils.Color;
import raytracing.utils.JavaTga;
import raytracing.utils.Vec3f;

public class Main extends JavaTga {

    public static void main(String[] args) {
        String filename = "test.tga";

        if (args.length > 0)
            filename = args[0];

        Scene scene = new Scene();

        int w = 1024;
        int h = 768;
        float D = 0.25f;
        byte buffer[] = new byte[3 * w * h];

        for (int row = 0; row < h; row++) { // for each row of the image
            for (int col = 0; col < w; col++) { // for each column of the image
                int index = 3 * (row * w + col);

                Color color = scene.findColor(
                        new Vec3f(),
                        new Vec3f(
                                (col - w / 2.0f) / (float) w,
                                (row - h / 2.0f) / (float) h,
                                -D));

                buffer[index] = color.getBlue();
                buffer[index + 1] = color.getGreen();
                buffer[index + 2] = color.getRed();
            }
        }
        try {
            saveTGA(filename, buffer, w, h);
            System.out.println("Saved image to " + filename);
        } catch (Exception e) {
            System.err.println("Image could not be saved.");
        }
    }

}
