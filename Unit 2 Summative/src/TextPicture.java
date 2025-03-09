import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Rajveer Karotanian
 *
 * Date: 10/18/2023
 *
 * Description:	This program takes a string and font, drawing the text in a JFrame. This class
 * 				inherits from the picture class.
 * 
 * 				Method List:
 * 				public void setTitle(String text) - sets the title to whatever string user wants
 * 				public String getTitle() - gets title contents
 * 				public void setFont(Font fnt) - sets font of text
 *				public Font getFont() - gets current font
 *				public void paint(Graphics g) - method to paint the title onto the JFrame
 *				public static void main(String[] args) - Self Testing main method 
 */
public class TextPicture extends Picture{

	/**
	 * Private instance variables
	 */	
	private String title;
	private Font font;

	// constructor for showing title
	public TextPicture(String text, Font fnt) {
		// call superclass constructor
		super();
		this.title = text;
		this.font = fnt;
	}

	// Overloaded constructor
	public TextPicture(String text, Font fnt, int x, int y) {
		// call parent constructor
		super(x, y, 0, 0);
		this.title = text;
		this.font = fnt;

	}

	// set title
	public void setTitle(String text) {
		this.title = text;
	}

	// get title
	public String getTitle() {
		return title;
	}

	// set font
	public void setFont(Font fnt) {
		this.font = fnt;
	}

	// get font
	public Font getFont() {
		return font;
	}

	// paint method
	public void paint(Graphics g) {
		g.setFont(this.getFont());
		g.setColor(this.getColor());
		g.drawString(title, this.getxPos(), this.getyPos());
	}


	/**
	 * self-testing main method
	 */
	public static void main(String[] args) {
		// Create a JFrame for testing
		JFrame f = new JFrame("Testing only!");

		// set size of frame
		f.setSize(400, 350);

		// create title
		TextPicture p = new TextPicture("Hello World!", new Font("Serif", Font.BOLD, 18), 100, 200);

		// add p to my frame
		f.add(p);

		// show my frame
		f.setVisible(true);

		// Delay
		JOptionPane.showMessageDialog(null, "wait");


		// change text, font, and color
		p.setTitle("Change text and font");
		p.setFont(new Font("Comic Sans", Font.ITALIC, 30));
		p.setxPos(50);
		p.setColor(Color.BLUE);


		f.repaint();
	}
}