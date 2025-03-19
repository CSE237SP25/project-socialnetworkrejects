package bankapp;

import java.util.Scanner;

public class Menu {
	
	private Scanner scanner;
	
	public Menu() {
		this.scanner = new Scanner(System.in);
	}
	
	public void runStartingConfiguration() {
		//welcome message
		this.displayWelcomeMessage();
		
		this.displayMenuOptions();
		
		String menuChoice = this.handleUserMenuInput();
		
		this.handleUserMenuSelection(menuChoice);
		
	}

	public void handleUserMenuSelection(String menuChoice) {
		if (menuChoice.equals("register")) {
			this.registerUser();
		}
		else if (menuChoice.equals("login")) {
			this.userLogin();
		}
	}

	public void userLogin() {
		this.displayLoginOptions();
		
		String input = this.handleUserBooleanInput();
		
		if (input.equals("yes")) {
			//the user actually wants to login
			this.inputCredentials("login");
			
		}
		
		else if (input.equals("no")) {
			//go back to the start of the program
			this.runStartingConfiguration();
		}
		
	}

	public void registerUser() {
		this.displayRegisterOptions();
		
		String input = this.handleUserBooleanInput();
		
		if (input.equals("yes")) {
			//the user wants to register
			this.inputCredentials("register");
			
		}
		
		else if (input.equals("no")) {
			//go back to the start of the program
			this.runStartingConfiguration();
		}
		
		
	}

	private void inputCredentials(String menuType) {
		if (menuType.equals("login")) {
			
			System.out.println("Enter Username: ");
			String username = this.scanner.nextLine();
			System.out.println("Enter Password: ");
			String password = this.scanner.nextLine();
			
			//if it matches, log the user in to their account
			
			System.out.println("Logged in!");
			
		}
		
		else if (menuType.equals("register")) {
			//user will enter their username and password, and these will be used to create a new User -> which is logged into
			
			System.out.println("Enter Username: ");
			String username = this.scanner.nextLine();
			System.out.println("Enter Password: ");
			String password = this.scanner.nextLine();
			
			//User u = new User(username, password);
			
			System.out.println("Created and logged into new account!");
		}
		
	}

	public String handleUserMenuInput() {
		//convert user input to lower case for if statement handling
		String input = this.scanner.nextLine().toLowerCase();
		
		while ( !(input.equals("register")) && !(input.equals("login")) ) {
			System.out.println("Please enter a correct menu selection.");
			input = this.scanner.nextLine().toLowerCase();		}
		
		return input;
	}
	
	public String handleUserBooleanInput() {
		//convert user input to lower case for if statement handling
		String input = this.scanner.nextLine().toLowerCase();
		
		while ( !(input.equals("yes")) && !(input.equals("no")) ) {
			System.out.println("Please enter a correct menu selection.");
			input = this.scanner.nextLine().toLowerCase();		}
		
		return input;
	}

	public void displayWelcomeMessage() {
		System.out.println("Welcome to the bank.");
		
	}
	
	public void displayMenuOptions() {
		System.out.println("> REGISTER");
		System.out.println("> LOGIN");
		
	}
	
	public void displayLoginOptions() {
		System.out.println("Are you sure that you want to login? If not, you will be taken to the main menu.");
		System.out.println("> YES");
		System.out.println("> NO");
		
	}
	
	public void displayRegisterOptions() {
		System.out.println("Are you sure that you want to register? If not, you will be taken to the main menu.");
		System.out.println("> YES");
		System.out.println("> NO");
		
	}
	

}
