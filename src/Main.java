public class Main extends JavaTga {

    public static void main(String[] args) {
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
                        -D
                    )
                );

                buffer[index] = color.getBlue();
                buffer[index + 1] = color.getGreen();
                buffer[index + 2] = color.getRed();
            }
        }
        try {
            saveTGA("test.tga",buffer,w,h);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }

}
