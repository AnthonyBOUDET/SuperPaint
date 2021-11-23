import java.awt.*;

public abstract class Figure {
    protected Color color;
    protected Point origin;
    protected boolean fill;
    public abstract void setBoundingBox (int heightBB, int widthBB);
    public abstract void Draw (Graphics g);
    public abstract int getPerimeter();
    public abstract int getSurface();
    public Figure(Color color,Point point){
        this.color = color;
        origin = point;
        fill = false;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public void setfill(boolean e) {
    	fill = e;
    }
    
    public char getFill() {
    	if(fill) return 'y';
    	else return 'n';
    }
    
    public char getCharColor() {
    	char c = 'n';
    	String str = this.color.toString().substring(15, this.color.toString().length()-1);
    	switch(str) {
			case "r=0,g=0,b=0" -> c = 'n';
			case "r=255,g=0,b=0" -> c = 'r';
			case "r=0,g=255,b=0" -> c = 'v';
			case "r=0,g=0,b=255" -> c = 'b';
			case "r=255,g=0,b=255" -> c = 'm';
			case "r=255,g=175,b=175" -> c = 'p';
			case "r=255,g=200,b=0" -> c = 'o';
			case "r=255,g=255,b=0" -> c = 'j';
    	}
    	return c;
    }
}