import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * @author Rajveer Karotanian
 *
 * Date: 11/10/2023
 *
 * Description:	This program serves as the menu for our Marvel game. The menu itself is a
 * 				JFrame, and is composed of many components such as JButtons with several 
 * 				alterations, ImagePictures, and TextPictures. The user is first greeted
 * 				with a main menu screen, giving them the option to start the program.
 * 				After that, the user is asked to a select a gamemode, FFA (free for all)
 * 				or Teams (2 teams of 2). If FFA is selected, the user is sent to the 
 * 				background selection. If Teams is selected, the user is given a selection
 * 				menu, where they are able to click on the heros to cycle through them and
 * 				lock them in by confirming, letting them pick the heros 1 by 1. Once the
 * 				last hero is selected, the user is sent to the same background selection
 * 				they would be sent to if they clicked the FFA gamemode. this background 
 * 				selection gives the user an option to pick between 3 different backgrounds,
 * 				where once they are happy with their background choice, they are able to 
 * 				confirm it to start the program, where a universe object is then created.
 * 				This program also makes use of audio, playing a menu song, and a click
 * 				sound whenever the actionPerformed method is used. The audio alongside the
 * 				frame itself is removed/set invisible once the universe object is created.
 * 
 * 				Method list:
 * 				public void actionPerformed(ActionEvent e) - checks which button is pressed and performs corresponding procedure
 * 				public static void main(String[] args) throws IOException - creates menu object
 */
public class Menu extends JFrame implements ActionListener{

	/**
	 * Private Instance Variables
	 */
	private int width, height, counter, path;
	private String heroes[], customNames[], customTeamNames[];
	private JButton btnStart, btnFFA, btnTeams, yes, no, characterSelect[], btnConfirm[];
	private ImagePicture title, background, spiderManPeek, teamOverlay, select, backgroundSelection, heroPreview[];
	private JButton background1Preview, background2Preview, background3Preview, linear, around;
	private TextPicture text, team1Text, team2Text, linearText, aroundText;
	private JTextField names[], teamNames[];
	private AudioInputStream menuSong, buttonClick;
	private boolean isTeamsSelected, resetScores;
	private Clip clip1, clip2;

	// Constructor for menu GUI
	public Menu() throws IOException {
		// call super
		super("Menu");

		// Initialize width and height of frame
		width = 800;
		height = 600;

		// set layout of frame
		setLayout(null);

		// set cursor
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("Cursor.png").getImage(), 
				new Point(0,0),"Custom Cursor"));

		// initialize buttons
		btnStart = new JButton();
		btnFFA = new JButton();
		btnTeams = new JButton();
		characterSelect = new JButton[4];
		btnConfirm = new JButton[8];
		background1Preview = new JButton();
		background2Preview = new JButton();
		background3Preview = new JButton();
		linear = new JButton();
		around = new JButton();
		yes = new JButton();
		no = new JButton();

		btnStart.setIcon(new ImageIcon("StartButton.png"));
		btnStart.setRolloverIcon(new ImageIcon("StartHover.png"));
		btnStart.setOpaque(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorderPainted(false);

		btnFFA.setIcon(new ImageIcon("FFAButton.png"));
		btnFFA.setRolloverIcon(new ImageIcon("FFAHover.png"));
		btnFFA.setOpaque(false);
		btnFFA.setContentAreaFilled(false);
		btnFFA.setBorderPainted(false);

		btnTeams.setIcon(new ImageIcon("TeamsButton.png"));
		btnTeams.setRolloverIcon(new ImageIcon("TeamsHover.png"));
		btnTeams.setOpaque(false);
		btnTeams.setContentAreaFilled(false);
		btnTeams.setBorderPainted(false);

		background1Preview.setIcon(new ImageIcon("Background1Preview.jpeg"));
		background2Preview.setIcon(new ImageIcon("Background2Preview.jpg"));	
		background3Preview.setIcon(new ImageIcon("Background3Preview.jpg"));
		
		yes.setIcon(new ImageIcon("YesButton.png"));
		yes.setRolloverIcon(new ImageIcon("YesHover.png"));
		yes.setOpaque(false);
		yes.setContentAreaFilled(false);
		yes.setBorderPainted(false);
		
		no.setIcon(new ImageIcon("NoButton.png"));
		no.setRolloverIcon(new ImageIcon("NoHover.png"));
		no.setOpaque(false);
		no.setContentAreaFilled(false);
		no.setBorderPainted(false);


		for (int i = 0; i < characterSelect.length; i++) {
			characterSelect[i] = new JButton();
			characterSelect[i].setIcon(new ImageIcon("NotSelected.png"));
			characterSelect[i].setOpaque(false);
			characterSelect[i].setContentAreaFilled(false);
			characterSelect[i].setBorderPainted(false);
			characterSelect[i].setFocusPainted(false);
		}

		for (int i = 0; i < btnConfirm.length; i++) {
			btnConfirm[i] = new JButton();
			btnConfirm[i].setIcon(new ImageIcon("ConfirmButton.png"));
			btnConfirm[i].setRolloverIcon(new ImageIcon("ConfirmHover.png"));
			btnConfirm[i].setOpaque(false);
			btnConfirm[i].setContentAreaFilled(false);
			btnConfirm[i].setBorderPainted(false);
		}

		linear.setIcon(new ImageIcon("Linear.png"));
		linear.setOpaque(false);
		linear.setContentAreaFilled(false);
		linear.setBorderPainted(false);

		around.setIcon(new ImageIcon("Around.png"));
		around.setOpaque(false);
		around.setContentAreaFilled(false);
		around.setBorderPainted(false);


		// add ActionListener to buttons
		btnStart.addActionListener(this);
		btnFFA.addActionListener(this);
		btnTeams.addActionListener(this);
		for (int i = 0; i < characterSelect.length; i++) {
			characterSelect[i].addActionListener(this);
		}
		for (int i = 0; i < btnConfirm.length; i++) {
			btnConfirm[i].addActionListener(this);
		}
		background1Preview.addActionListener(this);
		background2Preview.addActionListener(this);
		background3Preview.addActionListener(this);
		linear.addActionListener(this);
		around.addActionListener(this);
		yes.addActionListener(this);
		no.addActionListener(this);

		// Initialize Images
		title = new ImagePicture(new ImageIcon("Marvel.png"));
		background = new ImagePicture(new ImageIcon("Background.gif"));
		spiderManPeek = new ImagePicture(new ImageIcon("SpiderManPeek.gif"));
		teamOverlay = new ImagePicture(new ImageIcon("TeamOverlay.png"));
		select = new ImagePicture(new ImageIcon("Select.gif"));
		heroPreview = new ImagePicture[4];
		heroPreview[0] = new ImagePicture(new ImageIcon("SpiderManIdle.gif"));
		heroPreview[1] = new ImagePicture(new ImageIcon("IronManIdle.gif"));
		heroPreview[2] = new ImagePicture(new ImageIcon("HulkIdle.gif"));
		heroPreview[3] = new ImagePicture(new ImageIcon("CaptainAmericaIdle.gif"));

		// Initialize text
		text = new TextPicture("Select Gamemode:", new Font("Comic Sans", Font.BOLD , 65), 110, 120);
		text.setColor(172, 79, 198); // Set text color to purple
		team1Text = new TextPicture("Team 1", new Font("Comic Sans", Font.BOLD , 70), 60, 80);
		team1Text.setColor(Color.MAGENTA);
		team2Text = new TextPicture("Team 2", new Font("Comic Sans", Font.BOLD , 70), 480, 80);
		team2Text.setColor(Color.BLUE);
		linearText = new TextPicture("Straight Across", new Font("Comic Sans", Font.BOLD , 30), 80, 420);
		linearText.setColor(Color.CYAN);
		aroundText = new TextPicture("Around Background", new Font("Comic Sans", Font.BOLD , 30), 450, 420);
		aroundText.setColor(Color.CYAN);

		// Initialize music/sounds and play menu music
		try {
			// menuSong = AudioSystem.getAudioInputStream(new File("MenuSong.wav"));
			// clip1 = AudioSystem.getClip();
			buttonClick = AudioSystem.getAudioInputStream(new File("ButtonClick.wav"));
			clip2 = AudioSystem.getClip();

			// clip1.open(menuSong);
			clip2.open(buttonClick);

			// clip1.loop(Clip.LOOP_CONTINUOUSLY);
			// clip1.start();
		}
		catch (Exception error) {
		}

		// initialize string array
		heroes = new String[4];

		// initialize click counter
		counter = 0;

		// Initialize boolean for isTeamsSelected
		isTeamsSelected = false;

		// set bounds of everything
		background.setBounds(0, 0, width, height);
		title.setBounds(0, 0, width, height);
		btnStart.setBounds(309, 400, 183, 86);
		btnFFA.setBounds(150, 250, 106, 80);
		btnTeams.setBounds(480, 250, 198, 87);
		spiderManPeek.setBounds(301, 0, 198, 396);
		text.setBounds(0, 0, width, height);
		team1Text.setBounds(0, 0, width, height);
		team2Text.setBounds(0, 0, width, height);
		teamOverlay.setBounds(0, 0, width, height);
		linearText.setBounds(0, 0, width, height);
		aroundText.setBounds(0, 0, width, height);
		characterSelect[0].setBounds(35, 225, 155, 136);
		characterSelect[1].setBounds(205, 225, 155, 136);
		characterSelect[2].setBounds(435, 225, 155, 136);
		characterSelect[3].setBounds(605, 225, 155, 136);
		btnConfirm[0].setBounds(35, 370, 155, 60);
		btnConfirm[1].setBounds(205, 370, 155, 60);
		btnConfirm[2].setBounds(435, 370, 155, 60);
		btnConfirm[3].setBounds(605, 370, 155, 60);
		btnConfirm[4].setBounds(323, 400, 155, 60);
		btnConfirm[5].setBounds(323, 400, 155, 60);
		btnConfirm[6].setBounds(323, 400, 155, 60);
		btnConfirm[7].setBounds(323, 400, 155, 200);
		select.setBounds(65, 150, width, height);
		background1Preview.setBounds(15, 230, 240, 144);
		background2Preview.setBounds(272, 230, 240, 144);
		background3Preview.setBounds(530, 230, 240, 144);
		linear.setBounds(60, 200, 300, 200);
		around.setBounds(440, 200, 300, 200);
		heroPreview[0].setBounds(45, 190, 155, 136);
		heroPreview[1].setBounds(240, 180, 155, 136);
		heroPreview[2].setBounds(410, 165, 155, 136);
		heroPreview[3].setBounds(605, 180, 155, 136);
		yes.setBounds(150, 250, 143, 109);
		no.setBounds(480, 250, 116, 119);

		// add everything to frame
		add(title);
		add(btnStart);
		add(spiderManPeek);
		add(background);

		// set size and location of frame
		setSize(width, height);  
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Method to check which button pressed and to perform assigned procedure
	public void actionPerformed(ActionEvent e) {
		// play sound effect for button pressed
		try {
			clip2.setFramePosition(0); // Start audio from beginning of audio file
			clip2.start();
		}
		catch (Exception error) {
		}

		// Check which button pressed
		if (e.getSource() == btnStart) { // if start button pressed

			// remove all components
			remove(title);
			remove(btnStart);
			remove(spiderManPeek);
			remove(background);

			// add new components with proper layering
			add(text);
			add(btnFFA);
			add(btnTeams);
			add(background);
		}

		else if(e.getSource() == btnTeams) { // If teams gamemode is selected
			// set isTeamsSelected to true
			isTeamsSelected = true;

			// remove all components
			remove(text);
			remove(btnFFA);
			remove(btnTeams);
			remove(background);

			// add new components with proper layering
			add(teamOverlay);
			for (int i = 0; i < characterSelect.length; i++) {
				add(characterSelect[i]);
			}
			for (int i = 0; i < btnConfirm.length - 4; i++) {
				add(btnConfirm[i]);
			}
			add(select);
			add(team1Text);
			add(team2Text);
			add(background);

			// start with buttons disabled
			for (int i = 0; i < btnConfirm.length; i++) {
				btnConfirm[i].setEnabled(false);
			}
			for (int i = 1; i < characterSelect.length; i++) {
				characterSelect[i].removeActionListener(this);
			}
		}

		else if (e.getSource() == characterSelect[0]) { // if character 1 is clicked
			// increment button click counter
			counter++;

			// swap characters based on last character clicked
			if (counter % 4 == 0) { // select spider man
				characterSelect[0].setIcon(new ImageIcon("SpiderManIdle.gif"));
				heroes[0] = "SpiderMan";
			}
			else if (counter % 4 == 1) { // select iron man
				characterSelect[0].setIcon(new ImageIcon("IronManIdle.gif"));
				heroes[0] = "IronMan";
				btnConfirm[0].setEnabled(true);
			}
			else if (counter % 4 == 2) { // select captain america
				characterSelect[0].setIcon(new ImageIcon("CaptainAmericaIdle.gif"));
				heroes[0] = "CaptainAmerica";
			}
			else if (counter % 4 == 3) { // select hulk
				characterSelect[0].setIcon(new ImageIcon("HulkIdle.gif"));
				heroes[0] = "Hulk";
			}

			// repaint button
			characterSelect[0].repaint();
		}

		else if (e.getSource() == btnConfirm[0]) { // if first confirm button is clicked
			// disable first buttons and add action listener to second button
			characterSelect[0].removeActionListener(this);
			btnConfirm[0].setEnabled(false);
			characterSelect[1].addActionListener(this);

			// reset button click counter
			counter = 0;

			// move select ImagePicture
			select.setxPos(180);
		}

		else if (e.getSource() == characterSelect[1]) { // if character 2 is clicked
			// increment button click counter
			counter++;

			// swap characters based on last character clicked
			if (counter % 4 == 0) { // select spider man
				characterSelect[1].setIcon(new ImageIcon("SpiderManIdle.gif"));
				heroes[1] = "SpiderMan";
				btnConfirm[1].setEnabled(true);
			}
			else if (counter % 4 == 1) { // select iron man
				characterSelect[1].setIcon(new ImageIcon("IronManIdle.gif"));
				heroes[1] = "IronMan";
				btnConfirm[1].setEnabled(true);
			}
			else if (counter % 4 == 2) { // select captain america
				characterSelect[1].setIcon(new ImageIcon("CaptainAmericaIdle.gif"));
				heroes[1] = "CaptainAmerica";
				btnConfirm[1].setEnabled(true);
			}
			else if (counter % 4 == 3) { // select hulk
				characterSelect[1].setIcon(new ImageIcon("HulkIdle.gif"));
				heroes[1] = "Hulk";
				btnConfirm[1].setEnabled(true);
			}

			// disable confirm button if character has already been selected
			if (heroes[0].equals(heroes[1])) {
				btnConfirm[1].setEnabled(false);
			}

			// repaint button
			characterSelect[1].repaint();
		}

		else if (e.getSource() == btnConfirm[1]) { // if second confirm button is clicked
			// disable second buttons and add action listener to third button
			characterSelect[1].removeActionListener(this);
			btnConfirm[1].setEnabled(false);
			characterSelect[2].addActionListener(this);

			// reset button click counter
			counter = 0;

			// move select ImagePicture
			select.setxPos(410);
		}

		else if (e.getSource() == characterSelect[2]) { // if character 3 is clicked
			// increment button click counter
			counter++;

			// swap characters based on last character clicked
			if (counter % 4 == 0) { // select spider man
				characterSelect[2].setIcon(new ImageIcon("SpiderManIdleR.gif"));
				heroes[2] = "SpiderMan";
				btnConfirm[2].setEnabled(true);
			}
			else if (counter % 4 == 1) { // select iron man
				characterSelect[2].setIcon(new ImageIcon("IronManIdleR.gif"));
				heroes[2] = "IronMan";
				btnConfirm[2].setEnabled(true);
			}
			else if (counter % 4 == 2) { // select captain america
				characterSelect[2].setIcon(new ImageIcon("CaptainAmericaIdleR.gif"));
				heroes[2] = "CaptainAmerica";
				btnConfirm[2].setEnabled(true);
			}
			else if (counter % 4 == 3) { // select hulk
				characterSelect[2].setIcon(new ImageIcon("HulkIdleR.gif"));
				heroes[2] = "Hulk";
				btnConfirm[2].setEnabled(true);
			}

			// disable confirm button if character has already been selected
			if (heroes[0].equals(heroes[2]) || heroes[1].equals(heroes[2])) {
				btnConfirm[2].setEnabled(false);
			}

			// repaint button
			characterSelect[2].repaint();
		}

		else if (e.getSource() == btnConfirm[2]) { // if third confirm button is clicked
			// disable third buttons and add action listener to fourth button
			characterSelect[2].removeActionListener(this);
			btnConfirm[2].setEnabled(false);
			characterSelect[3].addActionListener(this);

			// reset button click counter
			counter = 0;

			// move select ImagePicture
			select.setxPos(580);
		}

		else if (e.getSource() == characterSelect[3]) { // if character 4 is clicked
			// increment button click counter
			counter++;

			// swap characters based on last character clicked
			if (counter % 4 == 0) { // select spider man
				characterSelect[3].setIcon(new ImageIcon("SpiderManIdleR.gif"));
				heroes[3] = "SpiderMan";
				btnConfirm[3].setEnabled(true);
			}
			else if (counter % 4 == 1) { // select iron man
				characterSelect[3].setIcon(new ImageIcon("IronManIdleR.gif"));
				heroes[3] = "IronMan";
				btnConfirm[3].setEnabled(true);
			}
			else if (counter % 4 == 2) { // select captain america
				characterSelect[3].setIcon(new ImageIcon("CaptainAmericaIdleR.gif"));
				heroes[3] = "CaptainAmerica";
				btnConfirm[3].setEnabled(true);
			}
			else if (counter % 4 == 3) { // select hulk
				characterSelect[3].setIcon(new ImageIcon("HulkIdleR.gif"));
				heroes[3] = "Hulk";
				btnConfirm[3].setEnabled(true);
			}

			// disable confirm button if character has already been selected
			if (heroes[0].equals(heroes[3]) || heroes[1].equals(heroes[3]) || heroes[2].equals(heroes[3])) {
				btnConfirm[3].setEnabled(false);
			}

			// repaint button
			characterSelect[3].repaint();
		}

		else if (e.getSource() == btnConfirm[3]) { // if button 4 pressed
			remove(teamOverlay);
			for (int i = 0; i < characterSelect.length; i++) {
				remove(characterSelect[i]);
			}
			for (int i = 0; i < btnConfirm.length; i++) {
				remove(btnConfirm[i]);
			}
			remove(select);
			remove(background);
			remove(btnFFA);
			remove(btnTeams);

			teamNames = new JTextField[2];
			customTeamNames = new String[2];

			for (int i = 0; i < teamNames.length; i++) {
				teamNames[i] = new JTextField("Team " + (i+1));
			}
			teamNames[0].setBounds(80, 300, 200, 50);
			teamNames[1].setBounds(510, 300, 200, 50);

			add(text);
			text.setTitle("Enter Team Names:");
			text.setxPos(130);
			team1Text.setyPos(250);
			team2Text.setyPos(250);
			add(teamNames[0]);
			add(teamNames[1]);
			add(btnConfirm[4]);
			add(background);

			// enable button
			btnConfirm[4].setEnabled(true);

		}

		else if (e.getSource() == btnConfirm[4] || e.getSource() == btnFFA) { // if 5th confirm button is clicked or FFA button
			// remove previous components
			remove(text);
			text.setTitle("Enter Team Names:");
			text.setxPos(100);
			if (isTeamsSelected == true) {
				remove(team1Text);
				remove(team2Text);
				remove(teamNames[0]);
				remove(teamNames[1]);
			}
			remove(btnConfirm[4]);
			remove(background);
			remove(select);
			remove(background);
			remove(btnFFA);
			remove(btnTeams);

			names = new JTextField[4];
			customNames = new String[4];

			if (isTeamsSelected == true ) { // organize name order if teams selected
				for (int i = 0; i < names.length; i++) {
					if (heroes[i] == "SpiderMan") {
						heroPreview[i].setImage(new ImageIcon("SpiderManIdle.gif"));		
					}
					else if (heroes[i] == "IronMan") {
						heroPreview[i].setImage(new ImageIcon("IronManIdle.gif"));		
					}
					else if (heroes[i] == "CaptainAmerica") {
						heroPreview[i].setImage(new ImageIcon("CaptainAmericaIdle.gif"));		
					}
					else if (heroes[i] == "Hulk") {
						heroPreview[i].setImage(new ImageIcon("HulkIdle.gif"));		
					}
				}
			}


			for (int i = 0; i < names.length; i++) {
				names[i] = new JTextField("Hero " + (i+1));
			}
			names[0].setBounds(40, 300, 150, 50);
			names[1].setBounds(220, 300, 150, 50);
			names[2].setBounds(400, 300, 150, 50);
			names[3].setBounds(580, 300, 150, 50);

			for (int i = 0; i < names.length; i++) {
				add(names[i]);
				add(heroPreview[i]);
			}


			// add new Components
			add(text);
			text.setTitle("Enter Names:");
			text.setxPos(180);
			team1Text.setyPos(250);
			team2Text.setyPos(250);
			add(btnConfirm[5]);
			add(background);

			// Enable 6th button
			btnConfirm[5].setEnabled(true);


		}

		else if (e.getSource() == btnConfirm[5]) { // if 6th confirm button is clicked
			// remove previous components
			remove(teamOverlay);
			for (int i = 0; i < characterSelect.length; i++) {
				remove(characterSelect[i]);
			}
			for (int i = 0; i < btnConfirm.length; i++) {
				remove(btnConfirm[i]);
			}
			remove(select);
			remove(background);
			remove(btnFFA);
			remove(btnTeams);
			remove(team1Text);
			remove(team2Text);
			for (int i = 0; i < names.length; i++) {
				remove(names[i]);
				remove(heroPreview[i]);
			}

			// Add new components
			text.setTitle("Select Background:");
			text.setxPos(95);
			add(text);
			add(background1Preview);
			add(background2Preview);
			add(background3Preview);
			add(select);
			add(btnConfirm[6]);
			add(background);

			// Start with confirm button disabled
			btnConfirm[6].setEnabled(false);

			// start with select image not on screen
			select.setxPos(-200);
		}

		else if(e.getSource() == background1Preview) {// if first background is picked
			backgroundSelection = new ImagePicture(new ImageIcon("Background1.jpeg")); // set background 
			// enable confirm button
			btnConfirm[6].setEnabled(true);
			// show map selected
			select.setxPos(20);
		}

		else if(e.getSource() == background2Preview) {// if second background is picked
			backgroundSelection = new ImagePicture(new ImageIcon("Background2.jpg")); // set background 
			// enable confirm button
			btnConfirm[6].setEnabled(true);
			// show map selected
			select.setxPos(280);
		}

		else if(e.getSource() == background3Preview) {// if third background is picked
			backgroundSelection = new ImagePicture(new ImageIcon("Background3.jpg")); // set background 
			// enable confirm button
			btnConfirm[6].setEnabled(true);
			// show map selected
			select.setxPos(540);
		}

		else if(e.getSource() == btnConfirm[6]) {// if 7th confirm button is pressed
			// Remove previous components
			remove(text);
			remove(background1Preview);
			remove(background2Preview);
			remove(background3Preview);
			remove(select);
			remove(btnConfirm[6]);
			remove(background);


			// Add new components
			text.setTitle("Select Walk Path:");
			text.setxPos(130);
			add(select);
			add(text);
			add(linear);
			add(around);
			add(linearText);
			add(aroundText);
			add(btnConfirm[7]);
			add(background);

			// Start with confirm button disabled
			btnConfirm[7].setEnabled(false);

			// start with select image not on screen
			select.setxPos(-200);
			select.setyPos(0);
		}

		else if(e.getSource() == linear) {// if linear path pressed
			// set path to 1
			path = 1;
			// Enable confirm button
			btnConfirm[7].setEnabled(true);
			// show path selected
			select.setxPos(80);

		}

		else if(e.getSource() == around) {// if around path pressed
			// set path to 1
			path = 2;
			// Enable confirm button
			btnConfirm[7].setEnabled(true);
			// show path selected
			select.setxPos(480);

		}

		else if(e.getSource() == btnConfirm[7] && isTeamsSelected == false) {// if confirm 8th confirm button is pressed and teams isn't selected
			// remove previous components
			remove(select);
			remove(text);
			remove(linear);
			remove(around);
			remove(linearText);
			remove(aroundText);
			remove(btnConfirm[7]);
			remove(background);
			
			// add new components
			text.setTitle("Reset Previous Scores?");
			text.setxPos(20);
			add(text);
			add(yes);
			add(no);
			add(background);
		}
		
		else if(e.getSource() == no) {// if scores arent to be reset
			// Close audio sources and make frame invisible
			// clip1.close();
			clip2.close();

			setVisible(false);

			// Create heroes array
			if (isTeamsSelected == false) {
				heroes[0] = "SpiderMan";
				heroes[1] = "IronMan";
				heroes[2] = "Hulk";
				heroes[3] = "CaptainAmerica";
			}
			else {
				for (int i = 0; i < customTeamNames.length; i++) {
					customTeamNames[i] = teamNames[i].getText();
				}
			}
			for (int i = 0; i < heroes.length; i++) {
				customNames[i] = names[i].getText();
			}
			
			// don't reset scores
			resetScores = false;

			// create universe object
			try {
				new Universe(isTeamsSelected, resetScores, heroes, customNames, customTeamNames, backgroundSelection, path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource() == btnConfirm[7] && isTeamsSelected == true || e.getSource() == yes) {// if confirm 8th confirm button is pressed and teams is selected or if scores is to be reset
			// Close audio sources and make frame invisible
			// clip1.close();
			clip2.close();

			setVisible(false);

			// Create heroes array
			if (isTeamsSelected == false) {
				heroes[0] = "SpiderMan";
				heroes[1] = "IronMan";
				heroes[2] = "Hulk";
				heroes[3] = "CaptainAmerica";
			}
			else {
				for (int i = 0; i < customTeamNames.length; i++) {
					customTeamNames[i] = teamNames[i].getText();
				}
			}
			for (int i = 0; i < heroes.length; i++) {
				customNames[i] = names[i].getText();
			}
			
			// reset scores
			resetScores = true;

			// create universe object
			try {
				new Universe(isTeamsSelected, resetScores, heroes, customNames, customTeamNames, backgroundSelection, path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// Method to create menu object
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu();
	}
}