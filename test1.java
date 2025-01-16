import arc.*;

public class CPTtest {

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
