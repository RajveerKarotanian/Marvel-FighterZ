import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Rajveer Karotanian
 *
 * Date: 10/17/2023
 *
 * Description:	Class for objects that display icons
 * 				Inherits from Picture
 * 
 * 				Method List: 
 * 				public void setImage(ImageIcon image) - sets image to the ImageIcon given
 * 				public void paint(Graphics g) - overrides paint from picture
 * 				public static void main(String[] args) - self-testing main method
 */
public class ImagePicture extends Picture{

	/**
	 * Private Instance Variables
	 */
	private ImageIcon image; // for storing icons

	/**
	 * Constructor for showing an image
	 */
	public ImagePicture(ImageIcon img) {
		// call super class constructor
		super();
		this.image = img;
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
	}

	/**
	 * Overloaded constructor
	 */
	public ImagePicture(ImageIcon img, int x, int y) {
		// call the parent constructor
		super(x, y, img.getIconWidth(), img.getIconHeight());
		this.image = img;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	/**
	 * Override the paint from picture
	 */
	public void paint(Graphics g) {
		// paint the image
		this.image.paintIcon(this,  g ,  getxPos(), getyPos());
	}

	// self testing main method
	public static void main(String[] args) {
		// Create a JFrame for testing
		JFrame f = new JFrame("Testing only!");

		// set size of frame
		f.setSize(400, 350);

		// create an imagePicture object
		ImagePicture p = new ImagePicture(new ImageIcon("minion.png"));

		// add p to my frame
		f.add(p);

		// show my frame
		f.setVisible(true);

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// change position of p
		p.setxPos(200);

		p.repaint();

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// create an imagePicture object
		ImagePicture p1 = new ImagePicture(new ImageIcon("gru.png"), 50, 100);

		// add p1 to my frame
		f.add(p1);

		// show my frame
		f.setVisible(true);

		// Delay
		JOptionPane.showMessageDialog(null, "Wait");

		// test the setImage to change the image of p
		p.setImage(new ImageIcon("gru.png"));

		p.repaint();

	}
}