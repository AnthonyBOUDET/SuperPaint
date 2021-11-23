import java.awt.*;

public class Square extends Rectangle{
    public Square (Point p, Color color){
        super(p, color);
    }

    public void setWeidth(int width) {
        this.width = width;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public void setBoundingBox(int widthBB, int heightBB) {
        setWeidth(widthBB);
        setLenght(heightBB);
    }
}
