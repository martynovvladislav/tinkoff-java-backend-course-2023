package edu.project4.imageMakerTests;

import edu.project4.entities.FractalImage;
import edu.project4.imageMakers.ImageFormat;
import edu.project4.imageMakers.ImageUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;

public class TestsForImageMaker {
    @Test
    @DisplayName("create and save image test")
    void saveImageTest() {
        FractalImage image = FractalImage.create(100, 100);
        File file = new File("src/main/java/edu/project4/images/test.png");
        if (file.exists()) {
            file.delete();
        }
        ImageUtils.save(image, Path.of("src/main/java/edu/project4/images/test.png"), ImageFormat.PNG);
        Assertions.assertTrue(new File("src/main/java/edu/project4/images/test.png").exists());
    }
}
