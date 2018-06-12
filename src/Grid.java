import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Grid{
	private JPanel[][] grid;
	private JFrame theGUI;
	private Container pane;
	private int durp;

	public Grid(int x, int y, int size){
		grid = new JPanel[x][y];
		theGUI = new JFrame();
		theGUI.setSize(size,size);
		pane = theGUI.getContentPane();

		pane.setLayout(new GridLayout(x,y));
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				grid[j][i] = new JPanel();
				grid[j][i].setBackground(Color.white);
				grid[j][i].setBorder(BorderFactory.createLineBorder(Color.black));
				pane.add(grid[j][i]);
			}
		}
		theGUI.setVisible(true);
		theGUI.setFocusable(true);
		theGUI.requestFocusInWindow();

		theGUI.addKeyListener(new KeyListener(){
			@Override
		    public void keyTyped(KeyEvent e) {}

		    @Override
		    public void keyReleased(KeyEvent e) {}

		    @Override
		    public void keyPressed(KeyEvent e) {
		    	durp = e.getKeyCode();

		    }
            });

	}

	public void setColor(int x, int y, Color c){
		grid[y][x].setBackground(c);
	}


	public int getCode(){
		int code = durp;
		durp = 0;
		return code;
	}
}