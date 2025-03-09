/**
 * 
 */

/**
 * @author Hilton Chukwu
 * 
 * Date: Oct. 2023
 * Description: Represents a die object.
 *
 */
public class Die {
	
	
	
	/*
	 * Attributes/properties/instance data
	 */
	
	private int faces;
	private int value;
	
	public Die() {
		this.faces = 6;
		rollDie();
	}
	
	/*
	 * Overloaded constructor
	 *  
	 */
	
	public Die(int faces) {
		this.faces = faces;
		rollDie();
	}
	
	// behaviours of Die
	
	public void rollDie() {
		this.value = (int) (Math.random() * this.faces + 1);
	}
	
	/*
	 * Method to get the value 
	 */
	
	public int getValue() {
		return this.value;
	}
	
	
	public boolean compareTo(Die d) {
		
		return this.value == d.getValue();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Die d1 = new Die();
		Die d = new Die();
		System.out.println(d.getValue());

		
		int counter = 0;
		
		//call the rollDie again 
		d.rollDie();
		System.out.println(d.getValue());
		
		for(int i = 0; i < 100; i++) {
			
			
			d1.rollDie();
			d.rollDie();
			
			
			if(d1.getValue() == d.getValue() ) {
				counter++;
				System.out.println(d1.getValue() + " " + d.getValue()); 
			}
		}
		System.out.println("There were " + counter + " like rolls out of 100");
		
		Die d2 = new Die(12);
		
		d2.rollDie();
		System.out.println(d2.getValue());
		
		
		//This should be tested several times
		System.out.println(d2.compareTo(d));
		
		
	}

}
