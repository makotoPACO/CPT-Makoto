import arc.*;

public class HangmanGame {
    public static void main(String[] args) {
        // Create console for input/output
        Console con = new Console();
        
	// Declare variables
    String strPlayerName;
    String strGuess;
    String strSecretWord;
    String strTheme;
    String StrGuessedWord;
    int intTries;
    int intMaxTries = 6;
    int intWordLength;
    int intIndex;
    int intThemeChoice;
    double dblWordGuessed;
        
    TextInputFile strTheme = new TextInputFile("themes.txt");
    strTheme = new String[3][11]; // 3 themes, 10 words per theme + one column for random number
    String[] StrGuessedWord;
        
        // Load themes and words from file
        //load the ThemesAndWords(strTheme, con);
        
        // Main menu loop
        while (true) {
            // Show the main menu options
            con.println("Main Menu");
            con.println("1. Play Game");
            con.println("2. View High Scores");
            con.println("3. Quit");
            con.println("4. Help");
            con.println("5. Add Theme");
            con.println("Enter your choice: ");
            int intMenuChoice = con.readInt();
            
            if (intMenuChoice == 1) {
                // Play the game
                con.println("Enter your name: ");
                strPlayerName = con.readline().trim();
                
                // Display available themes and let the user select
                con.println("Choose a theme:");
                for (int i = 0; i < 3; i++) {
                    con.println((i + 1) + ". " + strTheme[i][0]);
                }
                
                con.println("Enter the number corresponding to your choice: ");
                intThemeChoice = con.readInt() - 1; // Subtract 1 to get the index

                // Sort the words based on the random number in the second column (Bubble sort)

