import java.awt.*;

public class Circle extends Ellipse{
    public Circle (Point p, Color color){
        super(p, color);
    }

    public void setSemyAxisX(int semyAxisX) {
        SemyAxisX = semyAxisX;
    }

    public void setSemyAxisY(int semyAxisY) {
        SemyAxisY = semyAxisY;
    }

    @Override
    public void setBoundingBox(int heightBB, int widthBB) {
        setSemyAxisX(widthBB);
        setSemyAxisY(widthBB);
    }
}
