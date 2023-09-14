import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.png.PngImageParser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomImageGenerator {

    public static void main(String[] args) {
        String directoryPath = "path/to/png/files/directory";
        List<File> pngFiles = loadPngFiles(directoryPath);

        if (pngFiles.size() < 2) {
            System.out.println("Not enough .png files in the directory.");
            return;
        }

        Random random = new Random();
        BufferedImage generatedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = generatedImage.createGraphics();
        for (int i = 0; i < 2; i++) {
            int randomIndex = random.nextInt(pngFiles.size());
            File pngFile = pngFiles.get(randomIndex);

            try {
                BufferedImage overlayImage = Sanselan.getBufferedImage(pngFile);
                graphics.drawImage(overlayImage, 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        graphics.dispose();

        File outputFile = new File("output.png");
        try {
            Sanselan.writeImage(generatedImage, outputFile, Sanselan.PNG, null);
            System.out.println("Generated image saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> loadPngFiles(String directoryPath) {
        List<File> pngFiles = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    pngFiles.add(file);
                }
            }
        }

        return pngFiles;
    }
}
