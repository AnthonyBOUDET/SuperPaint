import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Preview extends Thread{
	
	private int x, y;
	private String NameFigure;
	private Color color;
	private Drawing d;
	
	public Preview(int x, int y, String figure, Color color, Drawing d) {
		this.x = x;
		this.y = y;
		this.NameFigure = figure;
		this.color = color;
		this.d = d;
	}
	
	public Point getOrigine(int mouseX, int mouseY, boolean type) { //type = false -> rectangle/ellipse	type = true -> square/circle
		if(type) {
			int newX = Math.abs(x-mouseX);
			int newY = Math.abs(y-mouseY);
			
			if(mouseX-x < 0 && mouseY-y < 0) {
				if(newX > newY) return new Point(mouseX, y-newX);
				else return new Point(x-newY, mouseY);
			}
			else if (mouseX-x < 0) {
				if(newX > newY) return new Point(mouseX, y);
				else return new Point(x-newY, y);
			}
			else if (mouseY-y < 0) {
				if(newX > newY) return new Point(x, y-newX);
				else return new Point(x, mouseY);
			}
			else return new Point(x, y);
		} else {
			if(mouseX-x < 0 && mouseY-y < 0) return new Point(mouseX, mouseY);
			else if (mouseX-x < 0) return new Point(mouseX, y);
			else if (mouseY-y < 0) return new Point(x, mouseY);
			else return new Point(x, y);
		}
	}

	@Override
    public void run() {
    	while(true) {
    		int mouseX = d.MoovPoint.getX();
    		int mouseY = d.MoovPoint.getY();

    		try { TimeUnit.MILLISECONDS.sleep(5); } catch (InterruptedException e) {};
    		
    		switch(NameFigure) {
    			case "Rectangle" -> {
    				Rectangle rectangle = new Rectangle(getOrigine(mouseX, mouseY, false), color);
    				rectangle.setfill(d.fill);
    				rectangle.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseY-y));
    				rectangle.Draw(d.getGraphics());
    				try { TimeUnit.MILLISECONDS.sleep(5); } catch (InterruptedException e) {};
    				rectangle.color = new Color(237,237,237);
    				rectangle.Draw(d.getGraphics());
    			}
    			case "Square" -> {
    				Square carre = new Square(getOrigine(mouseX, mouseY, true), color);
    				carre.setfill(d.fill);
    				Point point = new Point(Math.abs(x-mouseX), Math.abs(y-mouseY));
    				
    				if(point.getX() > point.getY())  carre.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseX-x));
    				else carre.setBoundingBox(Math.abs(mouseY-y), Math.abs(mouseY-y));
    				
    				carre.Draw(d.getGraphics());
    				try { TimeUnit.MILLISECONDS.sleep(5); } catch (InterruptedException e) {};
    				carre.color = new Color(237,237,237);
    				carre.Draw(d.getGraphics());
    			}
    			case "Ellipse" -> {
    				Ellipse ellipse = new Ellipse(getOrigine(mouseX, mouseY, false), color);
    				ellipse.setfill(d.fill);
    				ellipse.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseY-y));
    				ellipse.Draw(d.getGraphics());
    				try { TimeUnit.MILLISECONDS.sleep(5); } catch (InterruptedException e) {};
    				ellipse.color = new Color(237,237,237);
    				ellipse.Draw(d.getGraphics());
    			}
    			case "Circle" -> {
    				Circle circle = new Circle(getOrigine(mouseX, mouseY, true), color);
    				circle.setfill(d.fill);
    				Point point = new Point(Math.abs(x-mouseX), Math.abs(y-mouseY));
    				
    				if(point.getX() > point.getY()) circle.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseX-x));
    				else circle.setBoundingBox(Math.abs(mouseY-y), Math.abs(mouseY-y));

    				circle.Draw(d.getGraphics());
    				try { TimeUnit.MILLISECONDS.sleep(5); } catch (InterruptedException e) {};
    				circle.color = new Color(237,237,237);
    				circle.Draw(d.getGraphics());
    			}
    		}
    	}
    }
}