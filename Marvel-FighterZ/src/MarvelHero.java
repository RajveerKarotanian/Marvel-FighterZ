import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 
@author Gurnoor Gill, Hilton Chukwu
Date: November 7th, 2023
*
Description: MarvelHero inherits from Smart Minion. Class sets images to idle and walking as well as turning around the gif.

	Method List:  
		public void setImageIdle(String hero) -> Universe class asks to set an image to idle and image is set to idle animation
	 	public void setImageWalking(String hero) -> Universe class asks to set an image to it's walking animation and it sets the image to walking.
	 	public void invertImageWalking (String hero) -> Universe class asks to turn around the walking gif and method sets hero to a turned around gif.
*/
public class MarvelHero extends SmartMinion {


    // Constructor
    public MarvelHero(ImageIcon img) {
        super(img);
    }

    // set a hero in idle animation
    public void setImageIdle(String hero) {
        this.setImage(new ImageIcon(hero + "Still.gif"));
    }

    // set a hero in walking animation
    public void setImageWalking(String hero) {
        this.setImage(new ImageIcon(hero + "Walking.gif"));
    }

    // set a hero in walking animation and turn around
    public void invertImageWalking (String hero) {
        this.setImage(new ImageIcon(hero + "WalkingL.gif"));
    }
    
    /*
     * Self-Testing Main Method
     */

    public static void main(String[] args) {
        // create JFrame to test class
        JFrame f = new JFrame("Testing");

        // create MarvelHero object
        MarvelHero p = new MarvelHero(new ImageIcon("SpiderManStill.gif"));

        // set size
        f.setSize(700, 500);

        f.add(p); // add my own object to the frame

        f.setVisible(true); // paint the JFrame

        // wait to see changes
        JOptionPane.showMessageDialog(null, "Wait");


        // set steps to move
        p.setStepsToMove(300);

        // Shows user how many steps image will be moving.
        JOptionPane.showMessageDialog(null, " Image will move " + p.getStepsToMove() + " steps");

        // set Iron-Man to walking animation
        p.setImageWalking("IronMan");

        // wait to see changes
        JOptionPane.showMessageDialog(null, "Wait");

        // Turn around Captain America in walking animation
        p.invertImageWalking("CaptainAmerica");

        // Test moving the picture until steps moved is equal to my steps
        for (p.setStepsTaken(0); p.getStepsTaken() < p.getStepsToMove(); p.setStepsTaken(p.getStepsTaken() + 1)) {

            p.setxPos(p.getxPos() + 1);
            p.setyPos(p.getyPos() + 1);

            // delay
            try {

                Thread.sleep(10);

            }
            catch (Exception e) {

            }

        }

        // set final Idle to Hulk.
        p.setImageIdle("Hulk");

        // display steps moved to make sure 300 steps were moved
        JOptionPane.showMessageDialog(null, p.getStepsTaken() + " steps moved");
    }
}