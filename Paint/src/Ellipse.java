import java.awt.*;

public class Ellipse extends Figure{
    public int SemyAxisX;
    public int SemyAxisY;

    public Ellipse(Point p, Color color) {
        super(color, p);
        SemyAxisX=0;
        SemyAxisY=0;
    }

    @Override
    public void setBoundingBox(int widthBB, int heightBB) {
        setSemyAxisX(widthBB);
        setSemyAxisY(heightBB);
    }

    @Override
    public void Draw(Graphics g) {
    	g.setColor(color);
    	if(fill) g.fillOval(origin.getX(), origin.getY(), SemyAxisX, SemyAxisY);
    	else g.drawOval(origin.getX(), origin.getY(), SemyAxisX, SemyAxisY);
    }

    public void setSemyAxisX(int semyAxisX) {
        SemyAxisX = semyAxisX;
    }

    public void setSemyAxisY(int semyAxisY) {
        SemyAxisY = semyAxisY;
    }

    @Override
    public int getPerimeter() {
        return (int) (2 * Math.PI * Math.sqrt((SemyAxisX^2 * SemyAxisY^2)/2));
    }

    @Override
    public int getSurface() {
        return (int) (Math.PI * SemyAxisX * SemyAxisY);
    }

    @Override
    public String toString() {
    	return "e"+getCharColor()+getFill()+Integer.toString(origin.getX()) +","+ Integer.toString(origin.getY()) +","+ Integer.toString(SemyAxisX) +","+ Integer.toString(SemyAxisY) + ", ";
    }
}
