import java.util.Scanner;
import java.util.Random;

public class Functions {

	static Scanner scan= new Scanner(System.in);
	static Random rand= new Random();
	
	public static void Instructions() {
		String choice;
		
		//Ask to tell instructions, if yes print them
		System.out.println("Would you like to know how to play the game?");
		choice= scan.next();
		
		while (!(choice.equalsIgnoreCase("y") || 
				choice.equalsIgnoreCase("n"))) {
			System.out.println("Please enter a valid response.");
			choice= scan.next();
			}
		
		if (choice.equalsIgnoreCase("y")) {
			System.out.println("Instructions to be printed. \n");
		}
	}
	
	public static void askToPlay() {
		String choice;
		
		//Ask to play Blackjack
		System.out.println("Would you like to play Blackjack? (y/n)");
		choice= scan.next();
		
		while (!(choice.equalsIgnoreCase("y") || 
				choice.equalsIgnoreCase("n"))) {
			System.out.println("Please enter a valid response.");
			choice= scan.next();
		}
		
		//End program if choice is no
		if (choice.equalsIgnoreCase("n")) {
			scan.close();
			java.lang.System.exit(0);
		}
	}
	
	public static int valAssigner(int owncard, int amount) {
		
		if (owncard>8) {
			owncard= 10;
		}
		
		else if (owncard == 0) {
			
			if (amount<11) {
				owncard= 11;
			}
			
			else {
				owncard= 1;
			}
		}
		
		else {
			owncard += 1;
		}
		
		return owncard;
		
	}
	
	public static String dealerChoice(int oppamount) {
		String oppchoice;
		int chance;
		
		//If amount less than 15, definitely hit 
		if (oppamount<=15) {
			oppchoice= "h";
		}
		
		//If amount is 16, hit with 40% chance
		else if (oppamount == 16) {
			chance= rand.nextInt(101);
			
			if (chance>=60) {
				oppchoice= "h";
			}
			
			else {
				oppchoice= "s";
			}
		}
		
		//If amount is 17, hit with 25% chance
		else if (oppamount == 17) {
			chance= rand.nextInt(101);
			
			if (chance>=75) {
				oppchoice= "h";
			}
			
			else {
				oppchoice= "s";
			}
		}
		
		//Never hit if amount is 18 or above
		else {
			oppchoice= "s";
		}
		
		return oppchoice;
		
	}
	
	public static String[] appendCard(String[] list, String card) {
		String[] temp= new String[list.length+1];
		
		for (int index= 0 ; index < list.length ; index++) {
			temp[index]= list[index];
		}
		
		temp[temp.length-1]= card;
		
		return temp;
		
	}
	
	public static String printCards(String[] list) {
		String sentence= "";
		
		for (String ele : list) {
			if (ele.equals("Ace(11)") || ele.equals("Ace(1)"))  {
				ele= "Ace";
			}
			sentence += ("a " + ele +", ");
		}
		
		return sentence;
		
	}
	
	public static int winEvent(int bet, int balance) {
		//Add bet to balance
		balance += bet;
		
		System.out.println("You won the round!");
		System.out.println("$" + bet + " was added to your balance, totaling "
				+ "it to $" + balance + ".");
		
		return balance;
		
	}
	
	public static int loseEvent(int bet, int balance) {	
		//Subtract bet from balance
		balance -= bet;
		
		System.out.println("You lost the round.");
		System.out.println("$" + bet + " was subtracted from your balance, "
				+ "bringing it to $" + balance + ".");
		
		return balance;
		
	}
	
	public static String anotherRound() {
		String round;
		
		System.out.println("Play another round? (y/n)");
		round= scan.next();
		
		//Valid yes or no
		while (!(round.equalsIgnoreCase("y") || round.equalsIgnoreCase("n"))) {
			System.out.println("Please enter a valid response.");
			round= scan.next();
		}
		
		return round;
		
	}
	
}
