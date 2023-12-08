package edu.project4.renderers;

import edu.project4.entities.FractalImage;
import edu.project4.entities.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadRenderer extends AbstractRenderer implements Renderer {
    private static final int THREADS_AMOUNT = 10;
    private final ExecutorService executorService;

    public MultiThreadRenderer() {
        executorService = Executors.newFixedThreadPool(THREADS_AMOUNT);
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int samples,
        int iterPerSample
    ) {
        List<AffineTransformation> affineTransformations = AffineTransformation.generateAffineList(affineAmount);
        for (int num = 0; num < samples; num++) {
            executorService.execute(
                () -> renderFrame(iterPerSample, symmetry, canvas, world, variants, affineTransformations)
            );
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return canvas;
    }
}
