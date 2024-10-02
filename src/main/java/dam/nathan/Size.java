package dam.nathan;

import java.io.Serializable;

public class Size implements Serializable {
    private double Width;
    private double Heigh;

    public Size(){

    }

    public Size(double Width, double Height) {
        this.Width = Width;
        this.Heigh = Height;
    }

    public double getWidth() {
        return Width;
    }

    public double getHeigh() {
        return Heigh;
    }

    public void setWidth(double width) {
        Width = width;
    }

    public void setHeigh(double heigh) {
        Heigh = heigh;
    }

    @Override
    public String toString() {
        return "Size [Heigh = " + getHeigh() + ", Width = " + getWidth() + "]";
    }
}

