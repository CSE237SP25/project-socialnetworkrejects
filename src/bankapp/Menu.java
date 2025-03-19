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
		if (this.checkRegisterMenuSelection(menuChoice)) {
			this.registerUser();
		}
		else if (this.checkLoginMenuSelection(menuChoice)) {
			this.userLogin();
		}
	}
	
	public boolean checkRegisterMenuSelection(String input) {
		return input.equals("register");
	}
	
	public boolean checkLoginMenuSelection(String input) {
		return input.equals("login");
	}

	public void userLogin() {
		this.displayLoginOptions();
		
		String input = this.handleUserBooleanInput();
		
		if (this.checkYes(input)) {
			//the user actually wants to login
			this.inputCredentials("login");
			
		}
		
		else if (this.checkNo(input)) {
			//go back to the start of the program
			this.runStartingConfiguration();
		}
		
	}
	
	public boolean checkYes(String input) {
		return input.equals("yes");
		
	}
	
	public boolean checkNo(String input) {
		return input.equals("no");
	}

	public void registerUser() {
		this.displayRegisterOptions();
		
		String input = this.handleUserBooleanInput();
		
		if (this.checkYes(input)) {
			//the user wants to register
			this.inputCredentials("register");
			
		}
		
		else if (this.checkNo(input)) {
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
		String input = this.scanner.nextLine();
		
		while ( this.checkIncorrectUserMenuInput(input) ) {
			System.out.println("Please enter a correct menu selection.");
			input = this.scanner.nextLine();		}
		
		return input;
	}
	
	public boolean checkIncorrectUserMenuInput(String input) {
		input = input.toLowerCase();
		return !(input.equals("register")) && !(input.equals("login"));
		
	}
	
	public String handleUserBooleanInput() {
		//convert user input to lower case for if statement handling
		String input = this.scanner.nextLine();
		
		while ( this.checkIncorrectUserBooleanInput(input) ) {
			System.out.println("Please enter a correct menu selection.");
			input = this.scanner.nextLine();		}
		
		return input;
	}
	
	public boolean checkIncorrectUserBooleanInput(String input) {
		input = input.toLowerCase();
		return !(input.equals("yes")) && !(input.equals("no"));
		
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
