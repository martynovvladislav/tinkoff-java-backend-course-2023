package edu.project4.renderers;

import edu.project4.entities.FractalImage;
import edu.project4.entities.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int samples,
        int iterPerSample
    );
}
