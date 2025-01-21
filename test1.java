import arc.*;

public class test1 {

    public static void main(String[] args) {
        // Create console for input/output
        Console con = new Console();

        // Variable declarations
        String strPlayerName;
        String strSecretWord;
        String[][] wordsArray;
        int intMaxTries = 6;
        int score;
        int intMenuChoice;

        // Main menu loop
        while (true) {
            con.println("Main Menu");
            con.println("1. Play Game");
            con.println("2. View High Scores");
            con.println("3. Help");
            con.println("4. Add Theme");
            con.println("5. Quit");
            con.print("Enter your choice: ");
            intMenuChoice = con.readInt();

            if (intMenuChoice == 1) {
                // Play Game
                con.print("Enter your name: ");
                strPlayerName = con.readLine();

                // Display themes
                con.println("Choose a theme:");
                con.println("1. Christmas");
                con.println("2. Games");
                con.println("3. Pokemon");
                con.println("4. Add Your Own Theme");
                con.print("Enter your choice: ");
                int themeChoice = con.readInt();

                // Open the correct theme file based on the user's choice
                TextInputFile themeFile = null;
                if (themeChoice == 1) {
                    themeFile = new TextInputFile("Christmas.txt");
                    con.println("Opening Christmas.txt...");
                } else if (themeChoice == 2) {
                    themeFile = new TextInputFile("Games.txt");
                    con.println("Opening Games.txt...");
                } else if (themeChoice == 3) {
                    themeFile = new TextInputFile("Pokemon.txt");
                    con.println("Opening Pokemon.txt...");
                } else if (themeChoice == 4) {
                    con.print("Enter the name of your custom theme file (e.g., 'CustomTheme.txt'): ");
                    String customThemeFileName = con.readLine();
                    themeFile = new TextInputFile(customThemeFileName);
                    con.println("Opening " + customThemeFileName + "...");
                } else {
                    con.println("Invalid theme choice. Returning to main menu...");
                    continue;
                }

                // Load words from the theme file
                wordsArray = loadWords(themeFile);
                themeFile.close();  // Close the file after reading
                strSecretWord = getRandomWord(wordsArray);

                // Play the game and calculate the score
                score = playGame(con, strSecretWord, intMaxTries);

                // Save high score if the player wins
                if (score > 0) {
                    saveHighScore(strPlayerName, score);
                }

            } else if (intMenuChoice == 2) {
                // View High Scores
                viewHighScores(con);

            } else if (intMenuChoice == 3) {
                // Help
                con.println("Help: Guess the word correctly within 6 tries.");
                con.println("Each correct word solved earns points.");
                con.println("Good luck!");

            } else if (intMenuChoice == 4) {
                // Add Theme (this option is for players to add their own theme files)
                addTheme(con);

            } else if (intMenuChoice == 5) {
                // Quit
                con.println("Goodbye!");
                break;

            } else {
                con.println("Invalid choice! Please try again.");
            }
        }
    }

    // Load words from a theme file into a 2D array
    private static String[][] loadWords(TextInputFile themeFile) {
        String[][] wordsArray = new String[10][2]; // 10 words, 2 columns (word, random number)
        int index = 0;

        while (!themeFile.eof() && index < 10) {
            wordsArray[index][0] = themeFile.readLine(); // Load word
            wordsArray[index][1] = String.valueOf((int) (Math.random() * 100) + 1); // Random number
            index++;
        }
        bubbleSort(wordsArray); // Randomize order of words
        return wordsArray;
    }

    // Bubble sort to randomize word order
    private static void bubbleSort(String[][] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (Integer.parseInt(array[i][1]) > Integer.parseInt(array[i + 1][1])) {
                    String[] temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    // Get the first word from the sorted array
    private static String getRandomWord(String[][] wordsArray) {
        return wordsArray[0][0];
    }

    // Play the game and return the player's score
    private static int playGame(Console con, String strSecretWord, int intMaxTries) {
        int intTries = 0;
        StringBuilder revealedLetters = new StringBuilder("_".repeat(strSecretWord.length()));
        String[] hangmanStages = {
            "  ----\n  |  |\n     |\n     |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n     |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n  |  |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|  |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n /   |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n / \\ |\n     |\n---------"
        };

        while (intTries < intMaxTries) {
            con.println(hangmanStages[intTries]);
            con.println("Word to guess: " + revealedLetters);
            con.print("Guess the word: ");
            String guess = con.readLine().toLowerCase();

            if (guess.equals(strSecretWord)) {
                con.println("Congratulations! You've guessed the word!");
                return (intMaxTries - intTries) * 20;
            } else {
                int randomIndex;
                do {
                    randomIndex = (int) (Math.random() * strSecretWord.length());
                } while (revealedLetters.charAt(randomIndex) != '_');
                revealedLetters.setCharAt(randomIndex, strSecretWord.charAt(randomIndex));
                intTries++;
            }

            if (intTries == intMaxTries) {
                con.println("Game Over! The correct word was: " + strSecretWord);
                return 0;
            }
        }
        return 0;
    }

    // Save high score to a file
    private static void saveHighScore(String playerName, int score) {
        TextOutputFile highScoreFile = new TextOutputFile("highscores.txt", true);
        highScoreFile.println(playerName + ": " + score);
        highScoreFile.close();
    }

    // View high scores from the file
    private static void viewHighScores(Console con) {
        TextInputFile highScoreFile = new TextInputFile("highscores.txt");
        con.println("High Scores:");
        while (!highScoreFile.eof()) {
            con.println(highScoreFile.readLine());
        }
        highScoreFile.close();
    }

    // Add a new theme (this is just for demonstration, no checks performed)
    private static void addTheme(Console con) {
        con.print("Enter the name of the new theme file (e.g., 'CustomTheme.txt'): ");
        String themeFileName = con.readLine();
        con.println("Theme file '" + themeFileName + "' has been added (no checks performed).");
    }
}


