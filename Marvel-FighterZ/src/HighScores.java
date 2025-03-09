import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author Rajveer Karotanian
 *
 * Date: 11/7/2023
 *
 * Description:	This program provides a series of static methods for the Universe class to calculate and 
 * 				store points. Points are calculated with a formula, requiring both stepsMoved, and team 
 * 				wins. This data can be saved into a file, which will be read from at the following round
 * 				with the readData method. The final method is the leaderboard method, where it takes in
 * 				a string array of names, an integer array of scores, and sorts it from greatest to least,
 * 				returning an array with leaderboard positions, names, and scores as a string.
 * 
 * 				Method List:
 * 				public static int calcPoints(int stepsMoved, int teamWins) - Returns points based off steps moved and wins
 * 				public static void saveData(int scores[]) - Saves scores into a file
 * 				public static int[] readData() - Returns an array of scores from file
 * 				public static String[] leaderBoard(String[] names, int[] scores) - arranges a leaderboard in descending order
 * 				public static void main(String[] args) - self-testing main method
 */
public class HighScores {

	// Method to calculate points
	public static int calcPoints(int stepsMoved, int placement) {
		// formula to calculate points
		return stepsMoved / placement;
	}

	// Method to save scores
	public static void saveData(int scores[]) throws IOException {
		// open new file to write to 
		FileWriter file = new FileWriter("Previous Scores.txt");
		PrintWriter output = new PrintWriter(file);

		// Create a list to write to file with
		String list = "";

		// for loop to write all phrases into file
		for (int i = 0; i < scores.length; i++) {
			list = list + scores[i] + "\n";
		}

		// indicate end of file
		list = list + "END";

		// write to and close file
		output.print(list);
		file.close(); 
	}

	// Method to read scores
	public static int[] readData() throws IOException {
		// open the file in fileName to read
		BufferedReader input = new BufferedReader(new FileReader("Previous Scores.txt"));

		// count how many elements need to be in array
		int counter = 0;
		while(!input.readLine().equals("END")) {
			counter++;
		}

		// declare and create an array for the data set
		int[] scores = new int[counter];

		// close buffered reader to reset
		input.close();

		// Reopen file *Doesn't work without opening a new file reader, attempted multiple methods*
		input = new BufferedReader(new FileReader("Previous Scores.txt"));

		// store data into array
		for (int i = 0; i < counter; i++) {
			scores[i] = Integer.parseInt(input.readLine());
		}

		// close input
		input.close();

		// return scores array
		return scores;
	}

	// Method to rearrange data in descending order and put it into leaderboard format
	public static String leaderBoard(String[] names, int[] scores) {
		// Rearrange data
		for (int i = 0; i < scores.length; i++) {
			// sort the array in descending order (least to greatest)
			for (int j = 0; j < scores.length - 1; j++) {
				// check if scores at index j is less than index j + 1
				if (scores[j] < scores[j+1]) {
					// variables to temporarily store incorrect element value
					int tempScores;
					String tempNames;

					tempScores = scores[j]; // step 1 of swapping scores
					scores[j] = scores[j+1]; // step 2 of swapping scores
					scores[j+1] = tempScores; // step 3 of swapping scores

					tempNames = names[j]; // step 1 of swapping names
					names[j] = names[j+1]; // step 2 of swapping names
					names[j+1] = tempNames; // step 3 of swapping names
					
				}
			}
		}

		// Recombine data into a string
		String list = "Pos   Name\tT.Score\n";
		// for loop to put info into array
		for (int i = 0; i < names.length; i++) {
			list = list + (i + 1) + ".     " + names[i] + ":\t" + scores[i] + "\n";
		}

		// return array
		return list;
	}

	// self-testing main method
	public static void main(String[] args) throws IOException {
		// test calcPoints method
		System.out.println("400 steps and second place gives: " + calcPoints(400, 2) + " points");

		// test saveData method
		int[] scores = {100, 200, 300 ,400};
		HighScores.saveData(scores);
		System.out.println("\nPrevious Scores.txt has been saved!");

		// test readData method
		int[] previousScores = HighScores.readData();
		String list = "";
		for (int i = 0; i < previousScores.length; i++) {
			list = list + previousScores[i] + "\n";
		}
		System.out.println("\nScores:\n" + list);
		
		// test leaderboard method
		String [] names = {"Spiderman", "Ironman", "Hulk", "Captain America"};
		int [] score = {500, 300, 700, 600};
		
		System.out.println(leaderBoard(names, score)); // tabbing is fixed in actual usage of method in universe class
	}

}
