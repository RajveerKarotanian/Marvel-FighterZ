import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;
/**
 * @author Hilton Chukwu, Gurnoor Gill, Rajveer Karotanian
 * 
 * Date: Nov. 6, 2023
 * 
 * Description: Marvel hero game.
 * 
 * Method List: public void route1() --> The first route the heros can possibly follow
 *              public void route2() --> The second route the hero can possibly follow
 *              public void simplifyCode() --> Determines which restart code should be used
 *                                             depending on the map option the user selects
 * 
 *
 */
public class Universe extends JFrame implements ActionListener {



	//Instance data for program
	private JPanel drawingPanel, buttonPanel, gameOverPanel; // Data for drawing panels button panels
	private JButton roll, pause, restart;                   // and the panel that is displayed when the game is over
	private Die die1; //Dies that will be rolled to determine the steps that each hero should
	private Die die2; // be moving
	private int [] highScores, highScoresFix;
	private int [] heroSteps;
	private int [] lastScore;
	private int [] diceRolls; // Array to store last value each hero rolled
	private JLayeredPane gamePane, drawingPane; //JLayeredPane to allow the overlap of components
	private Timer timer; //Timer to update frame
	private MarvelHero[] heros; //MarvelHero object to manipulate each hero
	private JRadioButton on, off;
	private int winnerPointer, mapOption,playerTurnInd; 
	private ImagePicture panelBackground, frameBackground, leftImage, rightImage, gameOverImage,automatic;
	private ButtonGroup toggleGroup;
	private boolean changeSteps, preventError,rollIsPressed;
	private String[] heroNames, winnerOrder, winnerNames;
	private final String[] heroNames_;
	private String gameOverOutput, scoreBoardOutput;
	private String[] customNames, customNameFix, customTeamNames;
	private JTextArea gameOverArea, scoreBoardArea;
	private boolean gameMode;
	private JPanel scoreBoard;
	private boolean[][] checkPoints; //Checkpoints for each MarvelHero


	/**
	 * @throws IOException 
	 * 
	 */
	public Universe(boolean isTeamsSelected, boolean resetScores, String[] heroes, String[] names, String[] teamNames, ImagePicture backgroundSelection, int path) throws IOException {

		// call super
		super("Marvel FighterZ");

		//Panel to stored buttons in organized fashion
		buttonPanel = new JPanel();
		buttonPanel.setBounds(200,320,500,200); //setting up various aspects of the panel
		buttonPanel.setBackground(new Color(213, 134, 145, 123));
		//buttonPanel.se
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));


		//button for rolling
		roll = new JButton("");
		roll.setIcon(new ImageIcon("rollButton.png"));
		roll.setVerticalAlignment(JButton.CENTER);


		//Backing unnecesary components of the button transparent
		//https://stackoverflow.com/questions/4585867/transparent-jbutton
		roll.setOpaque(false);
		roll.setContentAreaFilled(false);
		roll.setBorderPainted(false);

		//setting the size of the roll button
		roll.setSize(100,100);

		//Setting the basic hero names along with their
		heroNames = new String[4];
		heroNames[0] = heroes[0];
		heroNames[1] = heroes[1];
		heroNames[2] = heroes[2];
		heroNames[3] = heroes[3];

		heroNames_ = new String[4];
		heroNames_[0] = heroes[0];   
		heroNames_[1] = heroes[1];   
		heroNames_[2] = heroes[2];   
		heroNames_[3] = heroes[3];

		// Set custom names
		if (isTeamsSelected == true) {
			customTeamNames = new String[2];
			for (int i = 0; i < customTeamNames.length; i++) {
				customTeamNames[i] = teamNames[i];
			}
		}
		customNames = new String[4];
		for (int i = 0; i < customNames.length; i++) {
			customNames[i] = names[i];
		}
		//Setting up the array that contains the order in which heros were
		//when the game concluded
		winnerOrder = new String[4];

		// set gamemode
		gameMode = isTeamsSelected;

		//Button that allows the user to pause the game to possible switch
		//between automatic and or the manual version of the program
		pause = new JButton();
		pause.setSize(100,100);
		pause.setIcon(new ImageIcon("pauseButton.png"));
		pause.setVerticalAlignment(JButton.CENTER);
		pause.setOpaque(false);
		pause.setContentAreaFilled(false);
		pause.setBorderPainted(false);


		//Button to allow the user to restart the game
		restart = new JButton();
		restart.setSize(100,100);
		restart.setIcon(new ImageIcon("restartButton.png"));
		restart.setVerticalAlignment(JButton.CENTER);
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);


		//Die to determine the amount of steps each minion moves
		die1 = new Die(12);
		die2 = new Die(12);

		// initialize hero steps
		heroSteps = new int[4];

		// initialize last score
		lastScore = new int[4];
		for (int i = 0; i < lastScore.length; i++) {
			lastScore[i] = 0;
		}

		highScores = new int[4];
		highScoresFix = new int[4];

		// Reset previous scores from last game if teams chosen as scores will not be accurate
		if (resetScores == true) {
			HighScores.saveData(lastScore);
		}

		//Radio buttons to allow the user to switch mode from manual or
		//to automatic
		on = new JRadioButton("On");
		off = new JRadioButton("Off");
		on.addActionListener(this);
		off.addActionListener(this);
		on.setFont(new Font("MV Boli", Font.PLAIN, 12));
		off.setFont(new Font("MV Boli", Font.PLAIN, 12));
		on.setOpaque(true);
		off.setOpaque(true);
		on.setBackground(new Color(248));
		off.setBackground(new Color(248,131,121));
		on.setForeground(Color.white);
		off.setForeground(Color.white);


		//ButtonGroup so that only one of them can be pressed at a time
		toggleGroup = new ButtonGroup();
		toggleGroup.add(on);
		toggleGroup.add(off);


		on.setBounds(440, 390, 50, 50);
		off.setBounds(490, 390, 50, 50);

		//Defaulting manual mode
		off.setSelected(true);


		//Title beside radio buttons 
		automatic = new ImagePicture(new ImageIcon("automatic.png"));
		automatic.setBounds(180,360,900,500);

		//Getting action events from buttons
		roll.addActionListener(this);
		pause.addActionListener(this);
		restart.addActionListener(this); // There may be some issues with the existence of this
		//buttn


		//Attempted to create a classic fighter asthetic
		//so these images were placed at the left and right side of the screen
		leftImage = new ImagePicture(new ImageIcon("left.png"));
		leftImage.setBounds(10,5, 200,500);
		rightImage = new ImagePicture(new ImageIcon("right.png"));
		rightImage.setBounds(690,-20, 200,500);

		// Initialize customNameFix
		customNameFix = new String[4];


		//Adding the buttons to their own panel so that I donnt need
		//to worry about their bounds
		buttonPanel.add(roll);
		buttonPanel.add(pause);
		buttonPanel.add(restart);

		//Pane in which all actions will be done
		drawingPane = new JLayeredPane();
		drawingPane.setBounds(0,0,500,300);

		drawingPanel = new JPanel();
		drawingPanel.setSize(400, 300);
		drawingPanel.setLayout(null);
		//drawingPanel.setBackground(Color.red);
		drawingPanel.setBounds(200,20, 500, 300);

		drawingPanel.add(drawingPane);

		//https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
		drawingPanel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red));
		//Setting the insets with this
		//bit of code

		// initialize scoreboard
		scoreBoardArea = new JTextArea("*Scores Will Appear\nWhen Round Ends*");
		scoreBoardArea.setBounds(540, 320, 160, 140);
		scoreBoardArea.setFont(new Font("NV Boli", Font.ITALIC + Font.PLAIN, 10));
		scoreBoardArea.setTabSize(6);
		scoreBoardArea.setBackground(Color.CYAN);
		scoreBoardArea.setEditable(false);

		// Scoreboard
		scoreBoard = new JPanel();
		scoreBoard.setBounds(540, 320, 160, 140);
		scoreBoard.setBackground(Color.CYAN);
		scoreBoard.add(scoreBoardArea);

		//Panel that appears onscreen when the game end
		gameOverPanel = new JPanel();
		gameOverPanel.setBounds(200, 20, 500,300);
		gameOverPanel.setBackground(new Color(28,32,36,255));
		gameOverPanel.setLayout(null);


		//Text area where important information is displayed when the game ends
		gameOverArea = new JTextArea(gameOverOutput);
		gameOverArea.setFont(new Font("MV Boli", Font.PLAIN, 15));
		gameOverArea.setTabSize(10);
		gameOverArea.setForeground(Color.white);
		gameOverArea.setBounds(100,120,500,300);
		gameOverArea.setBackground(new Color(28,32,36,255));
		gameOverArea.setEditable(false);


		//ImagePicture for gif at the center of the gameOverPanel
		gameOverImage = new ImagePicture(new ImageIcon("gameOver1.gif"),0,0);
		gameOverImage.setBounds((gameOverPanel.getWidth()/2)- 
				gameOverImage.getWidth()- 100,0, 500,300 );
		gameOverPanel.add(gameOverImage);
		gameOverPanel.add(gameOverArea);

		//Making sure the panel cannot be seen unless the game actually ends
		gameOverPanel.setVisible(false);

		//Setting up the background of the drawing Pane
		panelBackground = backgroundSelection;
		panelBackground.setBounds(5,1,500,300);


		drawingPane.add(panelBackground, JLayeredPane.DEFAULT_LAYER);


		//MapOption Stuff
		mapOption = path;

		//Initalizing important arrays that will be used in the program
		heros = new MarvelHero[4];
		diceRolls = new int[4];
		checkPoints = new boolean[4][4];



		//In the instance the first map Option is selected we can set up the marvelHeros below
		//eachother
		if (mapOption == 1) {
			for (int i = 0; i < heros.length; i++) {
				//need to initialize each of the objects in a very specific position
				//so the image placed in them for now is of little relevance
				if (i == 0) {
					heros[i] = new MarvelHero(new ImageIcon("minion.png"));
					heros[i].setBounds(0,0, drawingPanel.getWidth(), drawingPanel.getHeight());
					drawingPane.add(heros[i], JLayeredPane.PALETTE_LAYER);
				} else if (i ==1) {
					heros[i] = new MarvelHero(new ImageIcon("minion.png"));
					heros[i].setBounds(0,50, drawingPanel.getWidth(), drawingPanel.getHeight());
					drawingPane.add(heros[i], JLayeredPane.PALETTE_LAYER);
				} else if (i ==2) {
					heros[i] = new MarvelHero(new ImageIcon("minion.png"));
					heros[i].setBounds(0,100, drawingPanel.getWidth(), drawingPanel.getHeight());
					drawingPane.add(heros[i], JLayeredPane.PALETTE_LAYER);
				}
				else {
					heros[i] = new MarvelHero(new ImageIcon("minion.png"));
					heros[i].setBounds(0,150, drawingPanel.getWidth(), drawingPanel.getHeight());
					drawingPane.add(heros[i], JLayeredPane.PALETTE_LAYER);
				}

				//Setting the true image for the hero here
				heros[i].setImageIdle(heroNames_[i]);
				diceRolls[i] =0;

			}
			//Otherwise if the second mapOption has been selected heros can overlap in terms of their images
			//and will have the same y and x coordinates at the begining of the game
		} else if (mapOption == 2) {
			for (int i = 0; i < heros.length; i++) {
				//Will probably need to do this multiple times when we want to change the image
				heros[i] = new MarvelHero(new ImageIcon("minion.png"));
				heros[i].setBounds(0,0, drawingPanel.getWidth(), drawingPanel.getHeight());
				drawingPane.add(heros[i], JLayeredPane.PALETTE_LAYER);
				for (int j = 0; j < heros.length;j++) {
					checkPoints[i][j] = true;
				}
				heros[i].setImageIdle(heroNames_[i]);

			}
		}

		//repainting the frame
		repaint();


		//Setting up the background of the frame
		frameBackground = new ImagePicture(new ImageIcon("marvelHeros.gif"),0,0);
		frameBackground.setBounds(0,0,900,500);

		//setting up the JLayeredPane in which all content is stored
		gamePane = new JLayeredPane();
		gamePane.setLayout(null);
		//the same size as the frame
		gamePane.setSize(700, 400);
		//Adding the background to this layered pane in the base layer
		gamePane.add(frameBackground, JLayeredPane.DEFAULT_LAYER);

		//Initializing a sort of pointer for the playerTurn
		//as turns cannot be easily analyzed in a loop with a
		//loop variable
		playerTurnInd = 0;

		rollIsPressed = false;

		preventError = true; //Variable that will be used later
		//along wiht 


		//Setting the pointer for the winnerOrder array to the first position
		winnerPointer = 0;

		winnerNames = new String[4];

		//Initializing the string that will soon store the order of the winners such that
		//it can be used at a later instance
		gameOverOutput = "";





		//Setting up the timer
		timer = new Timer(1, this);
		timer.start();


		//Adding all major components toe the frame on the layeredPane
		//to ensure that they are all visble
		gamePane.add(drawingPanel, JLayeredPane.PALETTE_LAYER);
		gamePane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);
		gamePane.add(automatic, JLayeredPane.DRAG_LAYER);
		gamePane.add(on, JLayeredPane.DRAG_LAYER);
		gamePane.add(off, JLayeredPane.DRAG_LAYER);
		gamePane.add(gameOverPanel, JLayeredPane.DRAG_LAYER); //Some components are brought to the front
		gamePane.add(leftImage, JLayeredPane.PALETTE_LAYER);  //most layer
		gamePane.add(rightImage, JLayeredPane.PALETTE_LAYER);
		gamePane.add(scoreBoard, JLayeredPane.DRAG_LAYER);


		//Setting up the frame  at the center of the screen
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width /3, 
				Toolkit.getDefaultToolkit().getScreenSize().height /3);
		this.setLayeredPane(gamePane);
		this.setLayout(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900,500);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * @param args
	 */


	// Testing is done through menu, therefore main method not used
	public static void main(String args[]) {
	}

	/*
	 * Method for the first route the heros can possibly follow
	 */
	public void route1() {
		//The roll button be pressed and the names of the heros must be within the heroNames array
		if (rollIsPressed == true && !(heroNames[playerTurnInd].equals("NULL"))) {

			//Rolling both of the die
			die1.rollDie();
			die2.rollDie();
			diceRolls[playerTurnInd] = die1.getValue() + die2.getValue();

			//only allowing the hero to change their dieRoll one time
			//Also not allowing the user to roll if the first die plus the second
			// is a value that cannot be in the program
			if (changeSteps && diceRolls[playerTurnInd] != 4 &&diceRolls[playerTurnInd] != 6 && diceRolls[playerTurnInd] != 24) {
				heros[playerTurnInd].setStepsToMove(heros[playerTurnInd].getStepsToMove() + diceRolls[playerTurnInd]);
				//setting change steps = false which prevents them from essentially rolling again
				//until they have reached one of their checkpoints
				changeSteps = false;
			} 


			//Checking if the hero has not reached the end of the frame
			if (heros[playerTurnInd].getxPos() < (drawingPanel.getWidth() - heros[playerTurnInd].getMyWidth())) {
				heros[playerTurnInd].setImageWalking(heroNames_[playerTurnInd]);
				//if so the position of the hero can be updated
				heros[playerTurnInd].setxPos(heros[playerTurnInd].getxPos() + 1);
				//and the number of steps they have taken can increase
				heros[playerTurnInd].setStepsTaken(heros[playerTurnInd].getStepsTaken() + 1);
			} else {
				heros[playerTurnInd].setImageIdle(heroNames_[playerTurnInd]);
				//if this is not true we can simply cancel the current request for rolling
				if (!(on.isSelected())) {
					rollIsPressed = false;
				}
				//Reseting change steps variable for other heros
				changeSteps = true;
				//The current hero is the winner if they have reached the final checkpoint
				winnerOrder[winnerPointer] = heroNames[playerTurnInd];
				winnerNames[winnerPointer] = customNames[playerTurnInd];
				heroSteps[playerTurnInd] = heros[playerTurnInd].getStepsTaken();
				// calculate points
				if (winnerPointer == 0) {
					try {
						lastScore = HighScores.readData();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				highScores[playerTurnInd] = HighScores.calcPoints(heros[playerTurnInd].getStepsTaken(), winnerPointer + 1) + lastScore[playerTurnInd];
				customNameFix[winnerPointer] = customNames[playerTurnInd];
				highScoresFix[winnerPointer] = highScores[playerTurnInd];
				winnerPointer++; //Change the pointer for the winner
				heroNames[playerTurnInd] = "NULL"; // Setting the name of the hero in the hero nameArray as null
				//When the hero name is null we attempt to find a hero with a name that isnt null
				while(heroNames[playerTurnInd].equals("NULL")) {
					//Checking basic things relating to the array so no out of bounds 
					//error is thrown
					if (playerTurnInd == 3) {
						playerTurnInd = 0;
					} else {
						//Switching the hero until a hero with a non null name is found
						playerTurnInd++;
					}

					//temporary boolean to check if the game has concluded
					boolean gameOver = true;
					for (int i = 0; i < heroNames.length; i++) {
						//if at any instance all of the names in the heroName array
						//are null the game must have ended
						if (!(heroNames[i].equals("NULL"))) {
							//if this is not the case though, the game is not over and gameOver is fasle
							gameOver = false;
						}
					}

					//When the game ends though
					if (gameOver) {
						//Setting every button that is not the restart button
						//as false so they can no longer be pressed
						roll.setEnabled(false);
						pause.setEnabled(false);
						on.setEnabled(false);
						off.setEnabled(false);
						//Stopping the timer so the frame is no longer updated
						//Setting up the gameOver text that is displayed giving information like
						//positions score (relative to the race that just concluded) &&
						timer.stop(); 
						try {
							lastScore = HighScores.readData();
						} catch (IOException e) {
							e.printStackTrace();
						}
						for (int i =0; i < heroNames.length; i++) {
							if (i == 0 && gameMode && (heroNames_[0] == winnerOrder[0] || heroNames_[1] == winnerOrder[0])) {
								gameOverOutput+= customTeamNames[0] + " has won!\n";
							}
							else if (i == 0 && gameMode && (heroNames_[2] == winnerOrder[0] || heroNames_[3] == winnerOrder[0])){
								gameOverOutput+= customTeamNames[1] + " has won!\n";
							}
							if (i == 0) {
								gameOverOutput+= "Position\tName\tSteps Taken\n";
							}
							gameOverOutput+= (i+1) + "\t"+ winnerNames[i] + "\t" + heroSteps[i] + "\n";
						}
						// save scores
						try {
							HighScores.saveData(highScores);
						} catch (IOException e) {
							e.printStackTrace();
						}

						// update leaderboard
						scoreBoardOutput = HighScores.leaderBoard(customNameFix, highScoresFix);
						scoreBoardArea.setText(scoreBoardOutput);

						//Changing the current message with the string that was just constructed
						gameOverArea.setText(gameOverOutput);
						gameOverPanel.setVisible(true); //Allowing the game overPanel to be seen by
						//the user
						//can break out of loop
						break;

					}

				}
				preventError = false; //Variable to prevent and issue that I was having when programming



			}

			//Checking if the hero has reached the amount of steps they should have taken
			if (heros[playerTurnInd].getStepsToMove() < heros[playerTurnInd].getStepsTaken() && preventError) {
				//if this is the case and on is not selected the heros turn must end so rollIsPressed
				//is swiftly made false
				heros[playerTurnInd].setImageIdle(heroNames_[playerTurnInd]);
				if (!(on.isSelected())) {
					//otherwise if we are in automatic mode things will simply continue
					rollIsPressed = false;
				}
				changeSteps = true; //Reseting this variable for the next hero as I am now aware the
				//program for the current hero wont do anything as roll is pressed is now
				//false 


				//Changing things related to the current hero being viewed
				if (playerTurnInd == 3) {
					playerTurnInd = 0;
					//moving to a new hero if current heroName is null
					while (heroNames[playerTurnInd].equals("NULL")) {
						playerTurnInd++;
					}

				} else {

					//Doing the same here for the most part
					playerTurnInd++;

					if (playerTurnInd > 3) {
						playerTurnInd = 0;
					}
					while (heroNames[playerTurnInd].equals("NULL")) {
						
						if (playerTurnInd == 3) {
							playerTurnInd = 0;
						} else {
							playerTurnInd++; 
						}
						
					}
				}



			}
			preventError = true; //Once again require this variable in order to prevent
			//a certain logical error that should not happen but does happen

		} 
		else { 
			//if the roll is pressed by conditions like the hero name being valid
			//are not true the program just sets roll is pressed back to false and prevents any further
			//analysis
			if (!(on.isSelected())) {
				rollIsPressed = false;
			}
		}
	}
	/*
	 * Method for the second route a hero can possibly take
	 */
	public void route2 () {
		//Runs on the same general conditions as that of the first route
		if (rollIsPressed == true && !(heroNames[playerTurnInd].equals("NULL"))) {

			//Rolling both die
			die1.rollDie();
			die2.rollDie();
			diceRolls[playerTurnInd] = die1.getValue() + die2.getValue();
			//only allowing the hero to change their dieRoll one time
			//Also not allowing the user to roll if the first die plus the second
			// is a value that cannot be in the program
			if (changeSteps && diceRolls[playerTurnInd] != 4 &&diceRolls[playerTurnInd] != 6 && diceRolls[playerTurnInd] != 24) {
				heros[playerTurnInd].setStepsToMove(heros[playerTurnInd].getStepsToMove() + diceRolls[playerTurnInd]);
				//setting change steps = false which prevents them from essentially rolling again
				//until they have reached one of their checkpoints
				changeSteps = false;
			} 




			//Checking if the hero has not reached the end of the frame
			if (heros[playerTurnInd].getxPos() < (drawingPanel.getWidth() - heros[playerTurnInd].getMyWidth()) && checkPoints[playerTurnInd][0]) {


				heros[playerTurnInd].setImageWalking(heroNames_[playerTurnInd]);
				//if so the position of the hero can be updated
				heros[playerTurnInd].setxPos(heros[playerTurnInd].getxPos() + 1);
				//And the number of steps taken can increase
				heros[playerTurnInd].setStepsTaken(heros[playerTurnInd].getStepsTaken() +1);
				//checking if the hero has reached the bottom of the frame
			} else if (heros[playerTurnInd].getyPos() < (drawingPanel.getHeight() - heros[playerTurnInd].getMyHeight()) && checkPoints[playerTurnInd][1])  {
				heros[playerTurnInd].invertImageWalking(heroNames_[playerTurnInd]);
				checkPoints[playerTurnInd][0] = false;
				heros[playerTurnInd].setyPos(heros[playerTurnInd].getyPos() + 1);
				heros[playerTurnInd].setStepsTaken(heros[playerTurnInd].getStepsTaken() +1);
				//Checking if the hero has reached the bottom left of the frame
			} else if (heros[playerTurnInd].getxPos() > 0 && checkPoints[playerTurnInd][2]) {
				heros[playerTurnInd].invertImageWalking(heroNames_[playerTurnInd]);
				checkPoints[playerTurnInd][1] = false;
				heros[playerTurnInd].setxPos(heros[playerTurnInd].getxPos() - 1);
				heros[playerTurnInd].setStepsTaken(heros[playerTurnInd].getStepsTaken() +1);

				//checking if the hero has gone back to the beginning of the frame
			} else if (heros[playerTurnInd].getyPos() > 0 && checkPoints[playerTurnInd][3]) {
				heros[playerTurnInd].setImageWalking(heroNames_[playerTurnInd]);
				checkPoints[playerTurnInd][2] = false;
				heros[playerTurnInd].setyPos(heros[playerTurnInd].getyPos() - 1);
				heros[playerTurnInd].setStepsTaken(heros[playerTurnInd].getStepsTaken() +1);
			}
			else {
				heros[playerTurnInd].setImageIdle(heroNames_[playerTurnInd]);
				//if this is not true we can simply cancel the current request for rolling
				if (!(on.isSelected())) {
					rollIsPressed = false;
				}
				//reseting the cange steps variable again 
				changeSteps = true;
				//The current hero is the winner if they have reached the final checkpoint
				winnerOrder[winnerPointer] = heroNames[playerTurnInd];
				winnerNames[winnerPointer] = customNames[playerTurnInd];
				heroSteps[playerTurnInd] = heros[playerTurnInd].getStepsTaken();
				// calculate points
				if (winnerPointer == 0) {
					try {
						lastScore = HighScores.readData();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				highScores[playerTurnInd] = HighScores.calcPoints(heros[playerTurnInd].getStepsTaken(), winnerPointer + 1) + lastScore[playerTurnInd];
				customNameFix[winnerPointer] = customNames[playerTurnInd];
				highScoresFix[winnerPointer] = highScores[playerTurnInd];
				winnerPointer++;//changing the location of the pointer
				heroNames[playerTurnInd] = "NULL"; //Making it so that they hero can no longer
				// do anything

				//When the hero name is null we attempt to find a hero with a name that isnt null
				while(heroNames[playerTurnInd].equals("NULL")) {


					//Checking basic things relating to the array so no out of bounds 
					//error is thrown
					if (playerTurnInd == 3) {
						playerTurnInd = 0;
					} else {
						//Switching the hero until a hero with a non null name is found
						playerTurnInd++;
					}

					//temporary boolean to check if the game has concluded
					boolean gameOver = true;
					//if at any instance all of the names in the heroName array
					//are null the game must have ended
					for (int i = 0; i < heroNames.length; i++) {
						if (!(heroNames[i].equals("NULL"))) {
							gameOver = false;
						}
					}

					if (gameOver) {
						roll.setEnabled(false);
						pause.setEnabled(false);
						on.setEnabled(false);
						off.setEnabled(false);
						//Stopping the timer so the frame is no longer updated
						//Setting up the gameOver text that is displayed giving information like
						//positions score (relative to the race that just concluded) &&
						timer.stop();
						for (int i =0; i < heroNames.length; i++) {
							if (i == 0 && gameMode && (heroNames_[0] == winnerOrder[0] || heroNames_[1] == winnerOrder[0])) {
								gameOverOutput+= customTeamNames[0] + " has won!\n";
							}
							else if (i == 0 && gameMode && (heroNames_[2] == winnerOrder[0] || heroNames_[3] == winnerOrder[0])){
								gameOverOutput+= customTeamNames[1] + " has won!\n";
							}
							if (i == 0) {
								gameOverOutput+= "Position\tName\tSteps Taken\n";
							}
							gameOverOutput+= (i+1) + "\t"+ winnerNames[i] + "\t" + heroSteps[i] + "\n";
						}
						// save scores
						try {
							HighScores.saveData(highScores);
						} catch (IOException e) {
							e.printStackTrace();
						}
						// update leaderboard
						scoreBoardOutput = HighScores.leaderBoard(customNameFix, highScoresFix);
						scoreBoardArea.setText(scoreBoardOutput);
						//Changing the current message with the string that was just constructed
						gameOverArea.setText(gameOverOutput);
						gameOverPanel.setVisible(true);
						break;

					}

				}
				//Variable to prevent and issue that I was having when programming
				preventError = false;



			}

			if (heros[playerTurnInd].getStepsToMove() < heros[playerTurnInd].getStepsTaken() && preventError) {
				heros[playerTurnInd].setImageIdle(heroNames_[playerTurnInd]);
				if (!(on.isSelected())) {
					rollIsPressed = false;
				}
				changeSteps = true;




				if (playerTurnInd == 3) {
					playerTurnInd = 0;
					while (heroNames[playerTurnInd].equals("NULL")) {
						playerTurnInd++;
					}

				} else {

					playerTurnInd++;
					if (playerTurnInd > 3) {
						playerTurnInd = 0;
					}
					while (heroNames[playerTurnInd].equals("NULL")) {

						if (playerTurnInd == 3) {
							playerTurnInd = 0;
						}else {
							playerTurnInd++; 
						}
						
					}
				}



			}
			preventError = true;

		} 
		else {
			if (!(on.isSelected())) {
				rollIsPressed = false;
			}
		}
	}


	/*
	 * Method to reset the locations of the heros depending on the map option
	 * that has been selected
	 */
	public void simplifyCode() {


		if (mapOption == 1) {
			//Putting heros back in the line in which they should be present
			for (int i = 0; i < heros.length; i++) {
				if (i == 0) {
					heros[i].setxPos(0);
					heros[i].setyPos(0);
					heros[i].setStepsTaken(0);
					heros[i].setStepsToMove(0);
				} else if (i ==1) {
					heros[i].setxPos(0);
					heros[i].setyPos(0);
					heros[i].setStepsTaken(0);
					heros[i].setStepsToMove(0);
				} else if (i ==2) {
					heros[i].setxPos(0);
					heros[i].setyPos(0);
					heros[i].setStepsTaken(0);
					heros[i].setStepsToMove(0);
				}
				else {
					heros[i].setxPos(0);
					heros[i].setyPos(0);
					heros[i].setStepsTaken(0);
					heros[i].setStepsToMove(0);
				}
				highScores[i] = 0;

				diceRolls[i] =0;
				heros[i].setImageIdle(heroNames_[i]);

			}
		} else {
			//Putting heros back in the orientation where they overlap
			for (int i = 0; i < heros.length; i++) {
				heros[i].setxPos(0);
				heros[i].setyPos(0);
				heros[i].setStepsTaken(0);
				heros[i].setStepsToMove(0);
				diceRolls[i] =0;
				//also resetting all of their checkpoints as the map is a bit
				//more complicated
				for (int j = 0; j < heros.length;j++) {
					checkPoints[i][j] = true;
				}
				//also reseting the idleImages
				heros[i].setImageIdle(heroNames_[i]);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == timer) {

			if (on.isSelected()) {
				//rollIsPressed should always be true on automatic program so that
				//the program continues to run even without the user pressing a button
				rollIsPressed = true;
				//Changing the path the heros move based on the map option selected
				if (mapOption ==1) {
					route1();
				} else {
					route2();
				}

			} else { 
				//For when the user has not selected the on button on the manual mode
				if (mapOption ==1) {
					route1();
				} else {
					route2();
				}
			}


		}

		//When on is pressed we do not want to give the user
		//access to important buttons like roll
		//but otherwise roll can be set true when the off button is selected
		if (e.getSource() == on) {
			roll.setEnabled(false);
		} else if (e.getSource() == off) {
			roll.setEnabled(true);
		}


		if (e.getSource() == pause) {
			//Checking if the timer is running 
			if (timer.isRunning()) {
				//if so we stop the timer
				timer.stop();
			} else {
				//if not we can start the timer
				timer.start();
			}
			//resetting rollIsPressed variable when there is a transition between modes
			if (on.isSelected() && rollIsPressed == true) {
				rollIsPressed = false;
			}
		}

		if (e.getSource() == restart) {


			//Reseting all important variables to program functionality when the restart button is pressed
			rollIsPressed = false;
			changeSteps = true;
			preventError = true;
			off.setSelected(true);

			//Calling the method to fix the position of the heros back to the way they were at the begining

			simplifyCode();

			gameOverPanel.setVisible(false);
			heroNames[0] = heroNames_[0];   
			heroNames[1] = heroNames_[1];   
			heroNames[2] = heroNames_[2];   
			heroNames[3] = heroNames_[3];   
			winnerPointer=0;
			playerTurnInd = 0;
			gameOverOutput="";
			roll.setEnabled(true);
			pause.setEnabled(true);
			on.setEnabled(true);
			off.setEnabled(true);
			timer.start();



		}

		//roll button is probably most simple feature
		if (e.getSource() == roll) {

			rollIsPressed = true; //I could have tried using queue but kiss


		}

	}

}