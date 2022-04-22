package raytracing;

import raytracing.scene.Scene;
import raytracing.util.Color;
import raytracing.util.JavaTga;
import raytracing.util.Vec3;

/**
 * Main class of the raytracing program.
 *
 * @author Yannis Sauzeau
 */
public class Main extends JavaTga {

    
    /** 
     * The main method.
     * 
     * @param args the command line arguments -> usage: java raytracing.Main <sceneNumber> <maxDepth> <width> <height>");
     */
    public static void main(String[] args) {
        int sceneNumber = 1;
        int maxDepth = 5;
        int w = 1280;
        int h = 720;

        try {
            sceneNumber = Integer.parseInt(args[0]);
            if (args.length > 1) {
                maxDepth = Integer.parseInt(args[1]);
                if (args.length > 2) {
                    w = Integer.parseInt(args[2]);
                    h = Integer.parseInt(args[3]);
                }
            }
        } catch (Exception e) {
            System.out.println("Usage: java raytracing.Main <sceneNumber> <maxDepth> <width> <height>");
            System.out.println("Default values: java raytracing.Main " + sceneNumber + " " + maxDepth + " " + w + " " + h);
            System.out.println("===============================================================");
        }

        String filename = "output/scene" + sceneNumber + "_" + maxDepth + "_" + w + "x" + h + ".tga";
        Scene scene = new Scene();
        Vec3 P = new Vec3();
        double D = 1D;

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
            default:
                System.err.println("Invalid scene number");
                return;
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
