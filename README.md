# Racing Game - Java Project

## Overview
Welcome to the **Marvel FighterZ**, a dynamic and interactive Java-based game where players can choose between two exciting game modes, customize their gameplay experience, and compete in a thrilling race to the finish line. This project showcases strong object-oriented programming principles and interactive design, providing an engaging experience for players.

---

## Features
1. **Game Modes**:
   - **Free For All (FFA)**: A solo race where four players compete individually.
   - **Teams**: A team-based race with two teams of two players each.

2. **Customization**:
   - **Hero Selection**: Choose from a variety of heroes. In Teams mode, ensure no duplicate heroes are selected.
   - **Background Selection**: Pick from three unique backgrounds to set the stage for the race.
   - **Path Selection**: Choose between two path configurations:
     - **Linear**: Heroes travel straight across the background.
     - **Around**: Heroes travel around the background.

3. **Score Management**:
   - In FFA mode, players can choose to reset previous scores or continue with existing scores from the last session.

4. **Interactive Gameplay**:
   - **Roll Button**: Manually roll to move heroes one by one.
   - **Automatic Mode**: Enable automatic rolling to speed up gameplay.
   - **Pause Button**: Pause the game at any time.
   - **Restart Button**: Restart the game from the beginning, available at any point during the race.

5. **Leaderboard**:
   - At the end of each round, a leaderboard displays the ranking of heroes based on their finishing positions.
   - The scoreboard at the bottom of the screen updates scores based on each hero's position.

---

## How to Use
1. **Run the Program**:
   - Launch the game by running the `Menu` class.
   - The title screen will appear. Press "Start" to proceed.

2. **Select Game Mode**:
   - Choose between **FFA** (Free For All) or **Teams** mode.
   - **FFA**: Enter names for four individual heroes.
   - **Teams**: Select two heroes for each team and enter team names.

3. **Customize Gameplay**:
   - **Background Selection**: Choose one of three backgrounds.
   - **Path Selection**: Select either Linear or Around path configuration.
   - **Score Reset (FFA Only)**: Choose to reset scores or continue with previous scores.

4. **Play the Game**:
   - Use the **Roll** button to move heroes manually or enable **Automatic Mode** for faster gameplay.
   - Pause the game at any time using the **Pause** button.
   - Restart the game using the **Restart** button.

5. **View Results**:
   - After each round, the leaderboard displays the finishing order of heroes.
   - The scoreboard updates with the latest scores based on hero positions.

---

## Classes and Functionality
### Main Class - `Menu`
- **Purpose**: The entry point of the program.
- **Functionality**:
  - Displays the title screen and "Start" button.
  - Guides the user through game mode selection, hero customization, and gameplay setup.

### `Universe` Class
- **Purpose**: Manages the core gameplay mechanics.
- **Functionality**:
  - Handles hero movement, rolling, and automatic mode.
  - Provides buttons for pausing and restarting the game.
  - Displays the leaderboard and updates the scoreboard.

---

## Gameplay Mechanics
- **Rolling**: Determines the movement of heroes during the race.
- **Automatic Mode**: Speeds up gameplay by automating the rolling process.
- **Pausing**: Allows players to pause the game at any time.
- **Restarting**: Resets the game to its initial state, allowing players to start over.

---

## Score Management
- **FFA Mode**:
  - Players can choose to reset scores to zero or continue with scores from the previous session.
- **Teams Mode**:
  - Scores are tracked for each team and displayed on the scoreboard.

---

## Leaderboard and Scoreboard
- **Leaderboard**:
  - Displays the ranking of heroes at the end of each round.
- **Scoreboard**:
  - Tracks and updates scores based on hero positions during the race.

---

## Requirements
- **Java Runtime Environment (JRE)**: Ensure Java is installed on your system.
- **IDE or Command Line**: Use an IDE like IntelliJ IDEA or Eclipse, or run the program via the command line.

---

## Notes
- The game is designed to be intuitive and user-friendly, with clear prompts and error handling.
- Object-oriented programming principles are used extensively to ensure modularity and scalability.
- The game supports replayability with customizable options and score tracking.
- This game was created with my friends, my main contributions are the main menu class, and handling file I/O

---
