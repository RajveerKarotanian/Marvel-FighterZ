import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Hilton Chukwu
 * 
 * Date: Nov 1. 2023
 * 
 * Description: A child class of ImagePicture that is used to
 *              determine the max number of steps that a minion
 *              image can move
 *              
 *              
 * Method List:
 * public int getStepsToMove() --> Returns the number of steps to move.
 * 
 * public void setStepsToMove(int steps) --> Sets the number of steps to move if the input is greater than 100, 
 *                                           and resets the steps taken to 0.
 * 
 * public int getStepsTaken() --> Returns the number of steps taken.
 * 
 * public void setStepsTaken(int steps) --> Sets the number of steps taken.
 *
 */
public class SmartMinion extends ImagePicture {

	/**
	 * Private variables for steps to move and steps taken
	 */
	private int stepsToMove;
	private int stepsTaken;
	
	//Constructor
	public SmartMinion(ImageIcon image) {
		super(image);
		
		//Default stepsToMove
		this.stepsToMove = 0;
	}
	
	/*
	 * Method that returns the number of steps to move.
	 */
	public int getStepsToMove() {
		return this.stepsToMove;
	}
	/*
	 * Method that sets the number of steps to move 
	 */
	public void setStepsToMove(int steps) {
		//if the steps provided is less than 100
		//the default value of 100 will persist
			this.stepsToMove = steps;
	}
	
	/*
	 * Method that returns the number of steps taken.
	 */
	public int getStepsTaken() {
		return this.stepsTaken;
	}
	/*
	 * Method that sets the steps taken
	 */
	public void setStepsTaken(int steps) {
		this.stepsTaken = steps;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// self testing main method;
		JFrame f = new JFrame("Testing");
		SmartMinion p = new SmartMinion(new ImageIcon("minion.png"));
		p.setStepsToMove(200);
		
		f.setSize(400,350); // size for graphics
		f.add(p);
		f.setVisible(true);
		
		
		JOptionPane.showMessageDialog(null,"Wait");
		//loop runs while the steps taken is less than the steps to move
		while(p.getStepsTaken() < p.getStepsToMove()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			p.setStepsTaken(p.getStepsTaken() + 2);//Each movement x and y counts as a step
			p.setxPos(p.getStepsTaken()); //Changing the x and y positions
			p.setyPos(p.getStepsTaken());
			//displaying the steps taken
			System.out.println(p.getStepsTaken());
			p.repaint(); //repainting the frame
		}
		f.remove(p);
		
		//minion to test when the stepsToMove is less than 100
		SmartMinion p1 = new SmartMinion(new ImageIcon("minion.png"));
		p1.setStepsToMove(40); // setting the steps to 40 which is not allowed
		f.add(p1);
		f.setVisible(true); // repaint picture
		JOptionPane.showMessageDialog(null,"Wait");
		//Animating the picture
		while(p1.getStepsTaken() < p1.getStepsToMove()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			p1.setStepsTaken(p1.getStepsTaken() + 2); //Each movement x and y counts as a step
			p1.setxPos(p1.getStepsTaken()); //Changing the x and Y positions
			p1.setyPos(p1.getStepsTaken());
			//displaying the steps taken 40 is too small, it should default to 100
			System.out.println(p1.getStepsTaken());
			p1.repaint(); //repainting the frame
		}
		
		
		
		

	}

}
