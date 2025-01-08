import arc.*;

public class test1 {
    public static void main(String[] args) {
        // Create console for input/output
        Console con = new Console();
        
        // Declare variables
        String strMapThemes, strSelectedTheme, strWordToGuess, strPlayerName, strGuess;
        int intThemeChoice, intTries, intMaxTries = 6, intWordLength, intIndex;
        double dblWordGuessed;
        String[] arrGuessedWord;

        // Load themes from the file (for simplicity, store them as a String)
        strMapThemes = loadThemesFromFile("Themes.txt");

        // Display available themes (simplified)
        con.println("Available Themes: ");
        int intThemeIndex = 1;
        for (String strTheme : strMapThemes.split(",")) {
            con.println(intThemeIndex + ". " + strTheme);
            intThemeIndex++;
        }

        // Get theme choice from the player
        con.println("Choose a theme by entering the corresponding number: ");
        intThemeChoice = con.readInt();
        
        // Select the theme (simplified)
        strSelectedTheme = strMapThemes.split(",")[intThemeChoice - 1];
        strWordToGuess = selectRandomWord(strSelectedTheme);  // Assume this function selects the word
        intWordLength = strWordToGuess.length();
        arrGuessedWord = new String[intWordLength];
        
        // Initialize guessed word with underscores
        for (intIndex = 0; intIndex < intWordLength; intIndex++) {
            arrGuessedWord[intIndex] = "_";
        }
        
        // Initialize tries and word guessed status (using dbl for boolean logic)
        intTries = 0;
        dblWordGuessed = 0.0; // 0.0 represents false
        
        // Game loop
        while (intTries < intMaxTries && dblWordGuessed == 0.0) {
            con.println("Current Word: " + String.join("", arrGuessedWord));
            con.println("Tries left: " + (intMaxTries - intTries));
            drawHangman(intTries, con);
            
            // Get player's guess
            con.println("Guess the word: ");
            strGuess = con.readLine().trim();
            
            if (strGuess.equalsIgnoreCase(strWordToGuess)) {
                dblWordGuessed = 1.0; // 1.0 represents true
                con.println("Correct! The word was: " + strWordToGuess);
            } else {
                intTries++;
                revealRandomLetter(strWordToGuess, arrGuessedWord);
            }
        }

        // If the player runs out of tries, reveal the word
        if (dblWordGuessed == 0.0) {
            con.println("Game Over! The word was: " + strWordToGuess);
        }
        
        // Save score
        String strHighScore
        strHighScore = (strPlayerName, intTries);
    }
    
    // Function to load themes and words from the file (simplified as String)
    public static String loadThemesFromFile(String strFileName) {
        // Simplified: Load a single string that contains the themes separated by commas
        return "Animals,Plants,Games,Music,Movies";
    }
    
    // Function to select a random word from a theme (simplified for this example)
    public static String selectRandomWord(String strTheme) {
        // For now, just return a static word
        return "elephant";
    }
    
    // Function to draw the hangman based on the number of tries
    public static void drawHangman(int intTries, Console con) {
        String[] arrHangmanStages = {
            "  ----\n  |  |\n     |\n     |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n     |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n  |  |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|  |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n     |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n /   |\n     |\n---------",
            "  ----\n  |  |\n  O  |\n /|\\ |\n / \\ |\n     |\n---------"
        };
        con.println(arrHangmanStages[intTries]);
    }
    
    // Function to reveal a random letter in the word
    public static void revealRandomLetter(String strWord, String[] arrGuessedWord) {
        int intIndex = (int) (Math.random() * strWord.length());
        while (!arrGuessedWord[intIndex].equals("_")) {
            intIndex = (int) (Math.random() * strWord.length());
        }
        arrGuessedWord[intIndex] = String.valueOf(strWord.charAt(intIndex));
    }
    
    // Function to save the high score (for simplicity, this just prints)
    public static void saveHighScore(String strName, int intTries, Console con) {
        con.println("Saving score: " + strName + " - " + intTries + " tries");
    }
}

