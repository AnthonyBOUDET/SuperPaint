import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	JButton[] Buttons = new JButton[14];
	private Drawing draw;
    public Window(String Title,int x,int y) {
        super(Title);
        this.setSize(x,y);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPanel = this.getContentPane();
        
        JMenuBar MenuBar = new JMenuBar();

        JMenu MenuFile = new JMenu("File");
        JMenu MenuEdit = new JMenu("Edit");
        JMenu MenuApropos = new JMenu("A Propos");

        JMenuItem[] MenuItems = new JMenuItem[7];

        MenuItems[0] = new JMenuItem("New");
        MenuItems[1] = new JMenuItem("Open");
        MenuItems[2] = new JMenuItem("Save");
        MenuItems[3] = new JMenuItem("Quit");
        MenuItems[4] = new JMenuItem("Undo");
        MenuItems[5] = new JMenuItem("Redo");
        MenuItems[6] = new JMenuItem("About Supet Paint");
        MenuItems[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        MenuItems[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        MenuItems[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        for(int i = 0; i < 4; i++) {
            if(i == 3) MenuFile.addSeparator();
            MenuFile.add(MenuItems[i]);
        }
        for(int i = 4; i < 6; i++) {
        	MenuEdit.add(MenuItems[i]);
        }
        MenuApropos.add(MenuItems[6]);

        MenuBar.add(MenuFile);
        MenuBar.add(MenuEdit);
        MenuBar.add(MenuApropos);
        contentPanel.add(MenuBar, "North");

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        GridBagConstraints tableau = new GridBagConstraints();
        tableau.fill = GridBagConstraints.HORIZONTAL;
        tableau.weightx = 6;
        tableau.weighty = 2;
        tableau.gridx = 0;
        tableau.gridy = 0;

        contentPanel.add(southPanel,"South");

        Buttons[0] = new JButton("Noir");      	Buttons[0].setBackground(Color.BLACK);       Buttons[0].setForeground(Color.WHITE);
        Buttons[1] = new JButton("Rouge");     	Buttons[1].setBackground(Color.RED);
        Buttons[2] = new JButton("Vert");    	Buttons[2].setBackground(Color.GREEN);
        Buttons[3] = new JButton("Bleu");       Buttons[3].setBackground(Color.BLUE);        Buttons[3].setForeground(Color.WHITE);
        Buttons[4] = new JButton("Ellipse Ѻ");	Buttons[4].setBackground(Color.WHITE);       
        Buttons[5] = new JButton("Cercle o");	Buttons[5].setBackground(Color.WHITE);       
        Buttons[6] = new JButton("Fill");		Buttons[6].setBackground(Color.WHITE);     
        Buttons[7] = new JButton("Magenta");   	Buttons[7].setBackground(Color.MAGENTA);
        Buttons[8] = new JButton("Orange");     Buttons[8].setBackground(Color.ORANGE);
        Buttons[9] = new JButton("Rose");      	Buttons[9].setBackground(Color.PINK);
        Buttons[10] = new JButton("Jaune");     	Buttons[10].setBackground(Color.YELLOW);
        Buttons[11] = new JButton("Rectangle █");Buttons[11].setBackground(Color.BLACK);   Buttons[11].setForeground(Color.WHITE);   
        Buttons[12] = new JButton("Carre ■");	Buttons[12].setBackground(Color.WHITE);   
        Buttons[13] = new JButton("Unfill");	Buttons[13].setBackground(Color.BLACK);      Buttons[13].setForeground(Color.WHITE);

        for(int i = 0; i < 14; ++i) {
            southPanel.add(Buttons[i], tableau);
            tableau.gridx++;

            if(tableau.gridx == 7) {
                tableau.gridy++;
                tableau.gridx = 0;
            }
        }

        MenuItems[0].addActionListener(this); MenuItems[0].setActionCommand("New");
        MenuItems[1].addActionListener(this); MenuItems[1].setActionCommand("Open");
        MenuItems[2].addActionListener(this); MenuItems[2].setActionCommand("Save");
        MenuItems[3].addActionListener(this); MenuItems[3].setActionCommand("Quit");
        MenuItems[4].addActionListener(this); MenuItems[4].setActionCommand("Undo");
        MenuItems[5].addActionListener(this); MenuItems[5].setActionCommand("Redo");
        MenuItems[6].addActionListener(this); MenuItems[6].setActionCommand("APropos");
        
        Buttons[0].addActionListener(this); Buttons[0].setActionCommand("Noir");
        Buttons[1].addActionListener(this); Buttons[1].setActionCommand("Rouge");
        Buttons[2].addActionListener(this); Buttons[2].setActionCommand("Vert");
        Buttons[3].addActionListener(this); Buttons[3].setActionCommand("Bleu");
        Buttons[4].addActionListener(this); Buttons[4].setActionCommand("Ellipse");
        Buttons[5].addActionListener(this); Buttons[5].setActionCommand("Circle");
        Buttons[6].addActionListener(this); Buttons[6].setActionCommand("Fill");
        Buttons[7].addActionListener(this); Buttons[7].setActionCommand("Magenta");
        Buttons[8].addActionListener(this); Buttons[8].setActionCommand("Orange");
        Buttons[9].addActionListener(this); Buttons[9].setActionCommand("Rose");
        Buttons[10].addActionListener(this); Buttons[10].setActionCommand("Jaune");
        Buttons[11].addActionListener(this); Buttons[11].setActionCommand("Rectangle");
        Buttons[12].addActionListener(this); Buttons[12].setActionCommand("Square");
        Buttons[13].addActionListener(this); Buttons[13].setActionCommand("Unfill");

        this.setVisible(true);
        
        draw = new Drawing();
    	contentPanel.add(draw);
    }
    
    public void setSelected(int j, boolean type) {
    	int add;
    	if(type) {
    		add = 4;
    		for(int i = 0; i < 4; i++) {
    			if(i+add != j) {
    				Buttons[i+add].setBackground(Color.WHITE);
    				Buttons[i+add].setForeground(Color.BLACK);
    			} else {
    				Buttons[i+add].setBackground(Color.BLACK);
    				Buttons[i+add].setForeground(Color.WHITE);
    			}
    			if(i == 1) add += 5;
    		}
    	} else {
    		if(j == 6) add = 13;
    		else add = 6;
    		Buttons[j].setBackground(Color.BLACK);
			Buttons[j].setForeground(Color.WHITE);
			Buttons[add].setBackground(Color.WHITE);
			Buttons[add].setForeground(Color.BLACK);
    	}
    	
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "APropos" -> {
                JOptionPane info = new JOptionPane();
                JOptionPane.showInternalMessageDialog(info, "Authors : Un Codeur bancal",
                        "A Propos", JOptionPane.INFORMATION_MESSAGE);
            }
            case "Noir" -> Drawing.setColor(Color.BLACK);
            case "Rouge" -> Drawing.setColor(Color.RED);
            case "Vert" -> Drawing.setColor(Color.GREEN);
            case "Bleu" -> Drawing.setColor(Color.BLUE);
            case "Magenta" -> Drawing.setColor(Color.MAGENTA);
            case "Orange" -> Drawing.setColor(Color.ORANGE);
            case "Rose" -> Drawing.setColor(Color.PINK);
            case "Jaune" -> Drawing.setColor(Color.YELLOW);
            case "Ellipse" -> {
            	Drawing.setNameFigure("Ellipse");
            	setSelected(4, true);
            }
            case "Square" -> {
            	Drawing.setNameFigure("Square");
            	setSelected(12, true);
            }
            case "Rectangle" -> {
            	Drawing.setNameFigure("Rectangle");
            	setSelected(11, true);
            }
            case "Circle" -> {
            	Drawing.setNameFigure("Circle");
            	setSelected(5, true);
            }
            case "Fill" -> {
            	draw.fill = true;
            	setSelected(6, false);
            }
            case "Unfill" -> {
            	draw.fill = false;
            	setSelected(13, false);
            }
            case "Open" -> {
            	draw.recalDrawing();
            }
            case "New" ->  {
            	JOptionPane info = new JOptionPane();
            	if(JOptionPane.showInternalConfirmDialog(info, "Voulez vous sauvgarder votre dessin ?", "Attention", JOptionPane.YES_NO_OPTION) == 0) {
                	draw.saveDrawing();
    			}
            	this.setVisible(false);
            	new Window("Super Paint",800,600);
            }		
            case "Quit" -> System.exit(0);
            case "Save" -> {
            	draw.saveDrawing();
            }
            case "Undo" -> {
            	draw.undo();
            }
            case "Redo" -> {
            	draw.redo();
            }
        }
    }
}
