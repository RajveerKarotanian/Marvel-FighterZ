import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author      Hilton Chukwu
 * 
 * Date:        Oct. 16 2023
 * 
 * Description: A class that defines a component - Picture.
 * 
 * Method List:
 *
 *	public void setColor (Color col) --> This method sets the color of an object to the specified color.
 *	public void setColor (int r, int g, int b) --> This method sets the color of an object using the specified red, green, and blue values.
 *	public Color getColor() --> This method returns the current color of an object.
 *	public int getxPos() --> This method returns the current x-position of an object.
 *	protected void setxPos(int xPos) --> This method sets the x-position of an object to the specified value.
 *	public int getyPos() --> This method returns the current y-position of an object.
 *	public void setyPos(int yPos) --> This method sets the y-position of an object to the specified value.
 *	public void setMyWidth(int width) --> This method sets the width of an object to the specified value.
 *	public void setMyHeight(int height) --> This method sets the height of an object to the specified value.
 *
 */
public class Picture extends JComponent implements ActionListener {

	
	
	
	/**
	 * Instance data - Attributes 
	 */
	// private data for color, location and size
	private Color c;
	private int xPos, yPos, myWidth, myHeight;
	// default constructor
	public Picture() {
		this.c = Color.RED;
		this.xPos = 0;
		this.yPos = 0;
		this.myWidth = 50;
		this.myHeight = 25;
		repaint();
	}
	// constructor for specified location
	public Picture (int x, int y, int w, int h)
	{
		this.c = Color.RED;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = w;
		this.myHeight = h;
	}
	// setter and getter methods for color
	public void setColor (Color col)
	{
		this.c = col;
	}
	public void setColor (int r, int g, int b)
	{
		this.c = new Color (r,g,b);
	}
	public Color getColor() {
		return c;
	}
	// setter and getter methods for location
	public int getxPos() {
		return xPos;
	}
	protected void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	//Setter and getter method for width and height
	public void setMyWidth(int width) {
		this.myWidth = width;
	}
	
	
	public void setMyHeight(int height) {
		this.myHeight = height;
	}
	
	public int getMyHeight() {
		return this.myHeight;
	}
	
	public int getMyWidth() {
		return this.myWidth;
	}
	
	
	
	
	// paint method
	public void paint (Graphics g)
	{
		g.setColor(this.c); // set the new colour
		g.drawOval(xPos, yPos, myWidth, myHeight);
		//X position and width of inner circle vary with constants so that the eye remains
		//the way it is no matter what its dimensions are wherever it is located
		g.fillOval(xPos + myWidth /3, yPos, myWidth /3 , myHeight);
		g.drawRect(xPos, yPos, myWidth, myHeight);
	}

	/**
	 * Self - testing main
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creating new picture object using default constructor
		Picture p = new Picture();
		
		
		
		//Setting up the frame
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,650);
		frame.add(p);
		frame.setVisible(true);
		
		//Changing the color of the picture
		JOptionPane.showMessageDialog(null, "Wait");
		p.setColor(Color.black);
		frame.repaint();
		
		//Changing teh color of the picture using rgb 
		JOptionPane.showMessageDialog(null, "Wait");
		p.setColor(255,200,0);
		frame.repaint();
		
		
		//changing the width of the picture from default
		JOptionPane.showMessageDialog(null, "Wait");
		p.setMyWidth(400);
		frame.repaint();
		
		
		//changing the height of the picture from default
		JOptionPane.showMessageDialog(null, "Wait");
		p.setMyHeight(400);
		frame.repaint();
		
		
		//Changing the x position of the picture from default position
		JOptionPane.showMessageDialog(null, "Wait");
		p.setxPos(100);
		frame.repaint();
		
		//changing the y position of the picture from its default state
		JOptionPane.showMessageDialog(null, "Wait");
		p.setyPos(100);
		frame.repaint();
		
		
		//Creating a new picture object using other constructor
		Picture pic = new Picture(100,200,300,400);
		frame.add(pic);
		frame.remove(p);
		frame.setVisible(true);
		frame.repaint();
		JOptionPane.showMessageDialog(null, "Wait");
		
		//Testing the width and height setters and getters
		JOptionPane.showMessageDialog(null, "Wait");
		pic.setMyWidth(pic.getWidth() / 2);
		pic.setMyHeight(pic.getHeight() * 2);
		frame.repaint();
		
		
		
		
		//Animating the object to make from left to right 100 pixels
		for(int i = 0; i < 100; i++) {
			pic.setxPos(pic.getxPos() + 10);
			frame.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
