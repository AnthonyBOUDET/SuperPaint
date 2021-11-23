import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Drawing extends JPanel implements MouseListener, MouseMotionListener {
    public static Color c;
    public static String NameFigure;
    public boolean fill;
    public ArrayList<Figure> Undolist = new ArrayList<Figure>();
    public ArrayList<Figure> list = new ArrayList<Figure>();
    public static int undo;
    public static int x;
    public static int y;
    public Point MoovPoint;
    Preview thread;

    public static void setColor(Color color) {
    	c = color;
    }
    
    public static void setNameFigure(String nameFigure) {
        NameFigure = nameFigure;
    }
    
    public void reset() {
    	this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    @SuppressWarnings("resource")
	public void recalDrawing() {
    	try {
            JFileChooser dialogue = new JFileChooser(new File(".").getCanonicalFile());
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .des", "des");
            dialogue.setFileFilter(filter);
            dialogue.addChoosableFileFilter(filter);
            dialogue.setAcceptAllFileFilterUsed(false);
            
            dialogue.showOpenDialog(null);

            if(dialogue.getSelectedFile() == null)
            	return;
    		File file = dialogue.getSelectedFile();
    	    InputStream inputStream = new FileInputStream(file);
    	    ObjectInputStream isReader = new ObjectInputStream(inputStream);
    	    Point origine = new Point();
    	    Color color = Color.BLACK;
    	    int sizeX=0, sizeY=0, index=3;
    	    int size = isReader.readInt();
    	    String str="";
    	    list.clear();
    	    
    	    for(int i = 0; i < size; ++i) {
    	    	str = (String) isReader.readObject();
    	    	while(str.charAt(index) != ' ') {
    	    		int u=3;
    	    		for(int j = 0; j <= 3; ++j) {
    	    			String nb="";
        	    		while(str.charAt(u) != ',') {
        	    			nb += str.charAt(u);
        	    			u++;
        	    		}
        	    		switch(j) {
        	    			case 0 -> sizeX = Integer.parseInt(nb);
        	    			case 1 -> origine = new Point(sizeX, Integer.parseInt(nb));
        	    			case 2 -> sizeX = Integer.parseInt(nb);
        	    			case 3 -> sizeY = Integer.parseInt(nb);
        	    		}
        	    		u++;
    	    		}
    	    		index++;
    	    	}
    	    	switch(str.charAt(1)) {
    	    		case 'n' -> color = Color.BLACK;
    	    		case 'r' -> color = Color.RED;
    	    		case 'v' -> color = Color.GREEN;
    	    		case 'b' -> color = Color.BLUE;
    	    		case 'm' -> color = Color.MAGENTA;
    	    		case 'o' -> color = Color.ORANGE;
    	    		case 'p' -> color = Color.PINK;
    	    		case 'j' -> color = Color.YELLOW;
    	    	}
    	    	if(str.charAt(0) == 'r') {
    	    		Rectangle rec = new Rectangle(origine, color);
    	    		if(str.charAt(2) == 'n') rec.setfill(false);
    	    		else rec.setfill(true);
    	    		rec.setBoundingBox(sizeX, sizeY);
    	    		list.add(rec);
    	    	} else {
    	    		Ellipse elli = new Ellipse(origine, color);
    	    		if(str.charAt(2) == 'n') elli.setfill(false);
    	    		else elli.setfill(true);
    	    		elli.setBoundingBox(sizeX, sizeY);
    	    		list.add(elli);
    	    	}
    	    	index = 2;
    	    }
    	    reset();
    	    paintComponent(getGraphics());
    	} catch(Exception e) {
    		JOptionPane info = new JOptionPane();
    		JOptionPane.showMessageDialog(info, "Erreur sur la lecture du fichier", "Erreur", JOptionPane.OK_OPTION);
    	}
    }
    
    public void undo() {
    	if(list.size() == 0) return;

    	Undolist.add(list.get(list.size()-1));
    	list.remove(list.size()-1);
    	reset();
    	paintComponent(getGraphics());
    }
    
    public void redo() {
    	if(Undolist.size() == 0) return;
    	
    	list.add(Undolist.get(Undolist.size()-1));
    	Undolist.remove(Undolist.size()-1);
    	reset();
    	paintComponent(getGraphics());
    }

	public void saveDrawing() {
    	try {
    		JFileChooser chooser = new JFileChooser();
    		chooser.setCurrentDirectory(new File(".").getCanonicalFile());
    		int retrival = chooser.showSaveDialog(null);
    		File file = new File("");
    		boolean flag = false;
    		
    		if (retrival == JFileChooser.APPROVE_OPTION) {
    			if(chooser.getSelectedFile().getName().length() > 3) {
                	String c = chooser.getSelectedFile().getName();
                	int i = c.length();
                	if(c.substring(i-4, i).compareTo(".des") == 0) {
                		file = new File(c);
                		flag = true;
                    }
                }
    		}
    		if(!flag) {
    			file = new File(chooser.getSelectedFile()+".des");
    		}
        			
        	if(!file.exists()) {
        		file.createNewFile();
        	} else {
        		JOptionPane info = new JOptionPane();
        		if(JOptionPane.showInternalConfirmDialog(info, "Etes vous sur de vouloir supprimer l'ancien dessin ?", "Attention", JOptionPane.YES_NO_OPTION) == 1) {
                    return;
        		}
        	}

        	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        	oos.writeInt(list.size());
        	for(Figure f : list)
        		oos.writeObject(f.toString());
        	oos.close();
    	} catch (Exception e) {
    		JOptionPane info = new JOptionPane();
    		JOptionPane.showMessageDialog(info, "Erreur sur l'écriture du dessin !", "Erreur", JOptionPane.OK_OPTION);
    	}
    }
    
    public Drawing(){
    	addMouseMotionListener(this);
    	addMouseListener(this);
        this.setBackground(Color.white);
        NameFigure = "Rectangle";
        undo = 0;
        MoovPoint = new Point();
        fill = false;
        c = Color.BLACK;
    	x = 0; 
    	y = 0;
    }
    
    public void paintComponent(Graphics g) {
    	for(int i = 0; i < list.size(); ++i)
    		list.get(i).Draw(g);
    }
    
    @Override
    public void mouseClicked(MouseEvent mouse) {
    	
    }

	@Override
    public void mousePressed(MouseEvent mouse) {
    	x = mouse.getX();
    	y = mouse.getY();
    	thread = new Preview(x, y, NameFigure, c, this);
    	thread.start();
    }
    
    @Override
    public void mouseDragged(MouseEvent mouse) {
    	MoovPoint = new Point(mouse.getX(), mouse.getY());
    }

    @SuppressWarnings("deprecation")
	@Override
    public void mouseReleased(MouseEvent mouse) {
    	thread.stop();
    	int mouseX = mouse.getX();
    	int mouseY = mouse.getY();
    	Figure fig;
    	switch(NameFigure) {
			case "Rectangle" -> {
				fig = new Rectangle(thread.getOrigine(mouseX, mouseY, false), c);
				fig.setfill(fill);
				fig.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseY-y));
				list.add(fig);
			}
			case "Square" -> {
				fig = new Square(thread.getOrigine(mouseX, mouseY, true), c);
				fig.setfill(fill);
				Point point = new Point(Math.abs(x-mouseX), Math.abs(y-mouseY));
				if(point.getX() > point.getY())  fig.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseX-x));
				else fig.setBoundingBox(Math.abs(mouseY-y), Math.abs(mouseY-y));
				list.add(fig);
			}
			case "Ellipse" -> {
				fig = new Ellipse(thread.getOrigine(mouseX, mouseY, false), c);
				fig.setfill(fill);
				fig.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseY-y));
				list.add(fig);
			}
			case "Circle" -> {
				fig = new Circle(thread.getOrigine(mouseX, mouseY, true), c);
				fig.setfill(fill);
				Point point = new Point(Math.abs(x-mouseX), Math.abs(y-mouseY));	
				if(point.getX() > point.getY()) fig.setBoundingBox(Math.abs(mouseX-x), Math.abs(mouseX-x));
				else fig.setBoundingBox(Math.abs(mouseY-y), Math.abs(mouseY-y));	
				list.add(fig);
			}
    	}
    	reset();
    	paintComponent(getGraphics());
    }

    @Override
    public void mouseEntered(MouseEvent mouse) {
    	
    }

    @Override
    public void mouseExited(MouseEvent mouse) {
    	
    }

    @Override
    public void mouseMoved(MouseEvent mouse) {
    	MoovPoint = new Point(mouse.getX(), mouse.getY());
    }
}
