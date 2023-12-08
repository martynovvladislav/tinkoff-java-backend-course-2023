package edu.project4;

import edu.project4.entities.FractalImage;
import edu.project4.entities.Rect;
import edu.project4.imageMakers.ImageFormat;
import edu.project4.imageMakers.ImageUtils;
import edu.project4.imageProcessors.LogGammaCorrectionProcessor;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HyperbolicTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.SineTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.transformations.Transformation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final List<Transformation> TRANSFORMATIONS = new ArrayList<>(List.of(
        new HyperbolicTransformation(),
        new LinearTransformation(),
        new HeartTransformation(),
        new DiskTransformation(),
        new PolarTransformation(),
        new SineTransformation(),
        new SphericalTransformation()
    ));

    @SuppressWarnings("checkstyle:MagicNumber")
    public void runSingleThreadRender() {
        SingleThreadRenderer renderer = new SingleThreadRenderer();
        long start = System.currentTimeMillis();
        FractalImage image = renderer.render(
            FractalImage.create(3840, 2160),
            new Rect(-3, -2, 6, 6),
            TRANSFORMATIONS,
            5,
            1,
            10,
            10000000
        );
        LOGGER.info("Single thread: " + (System.currentTimeMillis() - start));
        LogGammaCorrectionProcessor processor = new LogGammaCorrectionProcessor();
        processor.process(image, 0.5);
        ImageUtils.save(
            image,
            Path.of("src/main/java/edu/project4/images/singlethreadimage.png"),
            ImageFormat.PNG
        );
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public void runMultiThreadRender() {
        MultiThreadRenderer renderer = new MultiThreadRenderer();
        long start = System.currentTimeMillis();
        FractalImage image = renderer.render(
            FractalImage.create(3840, 2160),
            new Rect(-3, -2, 6, 6),
            List.of(new HeartTransformation()),
            5,
            1,
            100,
            10000000
        );
        LOGGER.info("MultiThread: " + (System.currentTimeMillis() - start));
        LogGammaCorrectionProcessor processor = new LogGammaCorrectionProcessor();
        processor.process(image, 1.0);
        ImageUtils.save(
            image,
            Path.of("src/main/java/edu/project4/images/multithreadimage.png"),
            ImageFormat.PNG
        );
    }

    public void race() throws InterruptedException {
        Thread single = new Thread(this::runSingleThreadRender);
        single.start();
        runMultiThreadRender();
        single.join();
    }
}
