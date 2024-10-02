package dam.nathan;

import java.io.Serializable;

public class Point2D extends javafx.geometry.Point2D implements Serializable {
    private double v;
    private double v1;

    public Point2D(double v, double v1) {
        super(v, v1);
        this.v = v;
        this.v1 = v1;
    }

    public Point2D() {
        super(0, 0);



    }
}
