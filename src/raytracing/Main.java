package raytracing;

import raytracing.scene.Scene;
import raytracing.utils.Color;
import raytracing.utils.JavaTga;
import raytracing.utils.Vec3;

public class Main extends JavaTga {

    public static void main(String[] args) {
        String filename = "test.tga";
        int w = 1280;
        int h = 720;
        double D = 1D;
        int maxDepth = 5;
        int sceneNumber = 3;

        try {
            filename = args[0];
            w = Integer.parseInt(args[1]);
            h = Integer.parseInt(args[2]);
            D = Double.parseDouble(args[3]);
            maxDepth = Integer.parseInt(args[4]);
        } catch (Exception e) {
            System.out.println("Usage: java Main <filename> <width> <height> <distance> <maxDepth>");
            System.out.println("Default values: java Main " + filename + " " + w + " " + h + " " + D + " " + maxDepth);
            System.out.println("===============================================================");
        }

        Scene scene = new Scene();
        Vec3 P = new Vec3();

        switch (sceneNumber) {
            case 1:
                scene.createScene1();
                break;
            case 2:
                scene.createScene2();
                P = new Vec3(2, 4, 20);
                break;
            case 3:
                scene.createScene3();
                P = new Vec3(-2.6, 1.5, 3.9);
                D = 0.5D;
                break;
        }

        byte buffer[] = new byte[3 * w * h];

        for (int row = 0; row < h; row++) { // for each row of the image
            for (int col = 0; col < w; col++) { // for each column of the image
                int index = 3 * (row * w + col);

                Vec3 v = new Vec3(
                        ((double) col - (double) w / 2.0D) / (double) h,
                        ((double) row - (double) h / 2.0D) / (double) h,
                        -D);

                Color color = scene.findColor(P, v, maxDepth);

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
