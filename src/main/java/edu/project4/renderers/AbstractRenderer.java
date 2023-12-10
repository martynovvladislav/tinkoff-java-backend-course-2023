package edu.project4.renderers;

import edu.project4.entities.FractalImage;
import edu.project4.entities.Pixel;
import edu.project4.entities.Point;
import edu.project4.entities.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import static edu.project4.utils.RandomUtils.random;
import static edu.project4.utils.WorldUtils.mapRange;
import static edu.project4.utils.WorldUtils.rotate;

public abstract class AbstractRenderer  {
    private static final int NORMALIZATION_ITERATIONS = 20;

    protected void handlePixel(Pixel pixel, Color color) {
        if (pixel.getHitCount() == 0) {
            pixel.setHitCount(1);
            pixel.setColor(
                color.getRed(),
                color.getGreen(),
                color.getBlue()
            );
        } else {
            pixel.setHitCount(pixel.getHitCount() + 1);
            pixel.setCorrectedColor(
                color.getRed(),
                color.getGreen(),
                color.getBlue()
            );
        }
    }

    protected void renderFrame(
        int iterPerSample,
        int symmetry,
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        List<AffineTransformation> affineTransformations
    ) {
        Point point = random(world);
        for (int step = -NORMALIZATION_ITERATIONS; step < iterPerSample; step++) {
            AffineTransformation affineTransformation = random(affineTransformations);
            Transformation variation = random(variants);
            point = affineTransformation.apply(point);
            point = variation.apply(point);
            if (step > 0) {
                double theta = 0.0;
                for (int s = 0; s < symmetry; theta += Math.PI * 2 / symmetry, s++) {
                    var pwr = rotate(world, point, theta);
                    if (!world.contains(pwr)) {
                        continue;
                    }
                    Pixel pixel = mapRange(canvas, pwr, world);
                    if (pixel == null) {
                        continue;
                    }
                    synchronized (pixel) {
                        Color color = affineTransformation.color();
                        handlePixel(pixel, color);
                    }
                }

            }
        }
    }
}
