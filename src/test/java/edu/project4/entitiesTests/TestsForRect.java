package edu.project4.entitiesTests;

import edu.project4.entities.Point;
import edu.project4.entities.Rect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForRect {
    @Test
    @DisplayName("rect contains test")
    void rectContainsTest() {
        Rect rect = new Rect(0, 0, 5, 5);
        Assertions.assertTrue(rect.contains(new Point(2, 2)));
        Assertions.assertFalse(rect.contains(new Point(10, 10)));
    }
}
