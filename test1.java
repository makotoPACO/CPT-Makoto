import arc.*;

public class test1 {
    public static void main(String[] args) {
        // Create console for input/output
        Console con = new Console();
        
	// Declare variables
	String strWords [] [];
    String strPlayerName;
    String strGuess;
    String strSecretWord;
    String strTheme;
    String StrGuessedWord;
    double dblThemechoice;
    int intTries;
  
    //Array
    strWords = new String [2][11];   
        
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
        con.println("Enter your choice: ");
        
       double dblMenuChoice;
       dblMenuChoice = con.readDouble();;  
        //Choices   
            if (dblMenuChoice == 1) {
                // Play the game
                con.println("Enter your name: ");
				strPlayerName = con.readLine();
				con.println("1. Christmas ");
				con.println("2. games ");
				con.println("3. Pokemon ");
				con.println("4. Add Theme");
				con.println("Pick a theme: ");
				 dblThemechoice = con.readDouble();
				//Theme pick
				if (dblThemechoice == 1) {	
				TextInputFile Theme = new TextInputFile("Christmas.txt");	 
				} else if (dblThemechoice == 2) {
			    TextInputFile Theme = new TextInputFile("Games.txt");	 
				} else if (dblThemechoice == 3) {
				TextInputFile Theme = new TextInputFile("Pokemon.txt");	
				} else if (dblThemechoice == 4) {	
				
							
            } else if (dblMenuChoice == 2) {
                // View high scores
               
				// Make score board
            } else if (dblMenuChoice == 3) {
                // Quit the game
                con.println("Goodbye!");
               
            } else if (dblMenuChoice == 4) {
                // Help
                con.println("Help: Guess the word correctly before you run out of tries.");
                
            
				}				
			}			
		}
	}
}







