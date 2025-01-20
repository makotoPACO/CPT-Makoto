import arc.*;

public class test1 {

    public static void main(String[] args) {
        // Create console for input/output
        Console con = new Console();

        // Variable declarations at the top
        String strPlayerName;
        String strSecretWord;
        String[][] wordsArray;
        double dblThemeChoice;
        int intMaxTries = 6;
        int intTries;
        int score;
        double dblMenuChoice;

        // Main menu loop
        while (true) {
            con.println("Main Menu");
            con.println("1. Play Game");
            con.println("2. View High Scores");
            con.println("3. Help");
            con.println("4. Add Theme");
            con.println("5. Quit");
            con.print("Enter your choice: ");
            dblMenuChoice = con.readDouble();

            // Menu logic
            if (dblMenuChoice == 1) {
                // Play game logic
                con.print("Enter your name: ");
                strPlayerName = con.readLine();
                
                // Load themes from Themes.TXT and display options
                con.println("Choose a theme:");
                displayThemes();
                dblThemeChoice = con.readDouble();
                String themeFile = getThemeFile(dblThemeChoice);

                // Read words from the selected theme file
                wordsArray = loadWords(themeFile);
                strSecretWord = getRandomWord(wordsArray);
                
                // Start the game and calculate score
                score = playGame(con, strSecretWord, intMaxTries);
                
                // Save high score if player wins
                if (score > 0) {
                    saveHighScore(strPlayerName, score);
                }

            } else if (dblMenuChoice == 2) {
                // View high scores
                viewHighScores(con);

            } else if (dblMenuChoice == 3) {
                // Help message
                con.println("Help: Guess the word correctly within 6 tries.");
                con.println("Each correct word solved earns 100 points.");
                con.println("You will be given a hanging post showing your progress.");
                con.println("Good luck!");

            } else if (dblMenuChoice == 4) {
                // Add theme logic
                addTheme(con);

            } else if (dblMenuChoice == 5) {
                // Quit the game
                con.println("Goodbye!");
                break;

            } else {
                con.println("Invalid choice! Please try again.");
            }
        }
    }

    // Display themes
    private static void displayThemes() {
        Console con = new Console();
        con.println("1. Christmas");
        con.println("2. Games");
        con.println("3. Pokemon");
    }

    // Get theme file name based on user input
    private static String getThemeFile(double dblThemeChoice) {
        if (dblThemeChoice == 1) {
            return "Christmas.txt";
        } else if (dblThemeChoice == 2) {
            return "Games.txt";
        } else if (dblThemeChoice == 3) {
            return "Pokemon.txt";
        } else {
            return "Invalid";
        }
    }

    // Load words from a theme file and store them in a 2D array
    private static String[][] loadWords(String fileName) {
        TextInputFile themeFile = new TextInputFile(fileName);
        String[][] wordsArray = new String[10][2];  // 10 words, 2 columns (word, random number)
        
        for (int i = 0; i < 10; i++) {
            wordsArray[i][0] = themeFile.readLine(); // Load word
            wordsArray[i][1] = String.valueOf((int)(Math.random() * 100) + 1); // Generate random number
        }
        themeFile.close();
        
        // Apply bubble sort to randomize word order
        bubbleSort(wordsArray);
        return wordsArray;
    }

    // Bubble sort to randomly order the words
    private static void bubbleSort(String[][] array) {
        int n = array.length;
        boolean swapped;
        
        // Bubble sort algorithm
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (Integer.parseInt(array[i][1]) > Integer.parseInt(array[i + 1][1])) {
                    // Swap the words and their corresponding random numbers
                    String[] temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);  // Repeat until no swaps occur
    }

    // Get a random word from the sorted array
    private static String getRandomWord(String[][] wordsArray) {
        return wordsArray[0][0];  // Get the word after sorting
    }

    // Function to play the game
    private static int playGame(Console con, String strSecretWord, int intMaxTries) {
        int intTries = 0;
        String strGuessedWord = "_".repeat(strSecretWord.length());
        boolean gameOver = false;

        // Game loop
        while (!gameOver) {
            con.println("Word to guess: " + strGuessedWord);
            con.println("Tries left: " + (intMaxTries - intTries));
            con.print("Guess the word: ");
            String guess = con.readLine().toLowerCase();

            // Check if guess is correct
            if (guess.equals(strSecretWord)) {
                con.println("Congratulations! You've guessed the word correctly!");
                gameOver = true;
                return 100; // Score for guessing the word
            } else {
                intTries++;
                con.println("Incorrect guess!");
            }

            // Check if player has run out of tries
            if (intTries >= intMaxTries) {
                con.println("Game Over! The correct word was: " + strSecretWord);
                gameOver = true;
                return 0; // No score if the player loses
            }
        }
        return 0; // In case of any error, return 0
    }

    // Function to save high score
    private static void saveHighScore(String playerName, int score) {
        TextOutputFile highScoreFile = new TextOutputFile("highscores.txt", true);
        highScoreFile.println(playerName + ": " + score);
        highScoreFile.close();
    }

    // Function to view high scores
    private static void viewHighScores(Console con) {
        TextInputFile highScoreFile = new TextInputFile("highscores.txt");
        con.println("High Scores:");
        while (!highScoreFile.eof()) {
            String scoreLine = highScoreFile.readLine();
            con.println(scoreLine);
        }
        highScoreFile.close();
    }

    // Function to add a new theme (For simplicity, this assumes the theme is added manually to the file)
    private static void addTheme(Console con) {
        con.println("To add a new theme, simply create a new .txt file with 10 words of 7 letters each.");
        con.println("Save the file with the theme name (e.g., 'NewTheme.txt') and add it to the program.");
    }
}

    // Function to draw the hangman based on the number of tries
    public static void drawHangman{
        String[] arrHangmanStages = {
            "  ----\n  |  |\n     |\n     |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n     |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n  |  |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|  |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n /   |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n / \\ |\n     |\n---------"
    }
