import java.awt.*;

public class Rectangle extends Figure{
    int width;
    int lenght;


    public Rectangle(Point p, Color color) {
        super(color, p);
        width = 0;
        lenght = 0;
    }

    @Override
    public void setBoundingBox(int widthBB, int heightBB) {
        setLenght(heightBB);
        setWeidth(widthBB);
    }

    @Override
    public void Draw(Graphics g) {
    	g.setColor(color);
    	if(fill) g.fillRect(origin.getX(), origin.getY(), width, lenght);
    	else g.drawRect(origin.getX(), origin.getY(), width, lenght);
    }

    public int getWeidth() {
        return width;
    }

    public void setWeidth(int width) {
        this.width = width;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public int getPerimeter() {
        return 0;
    }

    @Override
    public int getSurface() {
        return 0;
    }

    @Override
    public String toString() {
		return "r"+getCharColor()+getFill()+Integer.toString(origin.getX()) +","+ Integer.toString(origin.getY()) +","+ Integer.toString(width) +","+ Integer.toString(lenght) + ", ";
    }
}

