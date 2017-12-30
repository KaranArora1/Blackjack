import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Functions {
	
	//Static objects
	static Scanner scan= new Scanner(System.in);
	static Random rand= new Random();
	
	public static void startGame() {
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
		
		//Ask to tell instructions, if yes print them
		System.out.println("Would you like to know how to play the game?");
		choice= scan.next();
		
		while (!(choice.equalsIgnoreCase("y") || 
				choice.equalsIgnoreCase("n"))) {
			System.out.println("Please enter a valid response.");
			choice= scan.next();
			}
		
		if (choice.equalsIgnoreCase("y")) {
			System.out.println("The aim of Blackjack is to get the sum of "
					+ "your hand as close to 21 as possible, but not go "
					+ "over the number.");
			
			System.out.println("Each turn, you will have the choice "
					+ "of either hitting or standing. Afterwards, the dealer"
					+ " will hit or stand.");
			
			System.out.println("In the first turn, both you and the dealer"
					+ " will recieve two cards. Only one of the dealer's cards"
					+ " will be visible.");
			
			System.out.println("Hitting gives you another card, while "
					+ "standing opts you out of collecting any more cards "
					+ "until the end of the round.");
			
			System.out.println("Whoever ends up getting the closest to 21 "
					+ "is the winner of the round.");
			
			System.out.println("If you go over 21, or \"bust\", the round"
					+ " instantly ends and the dealer wins. But, if the"
					+ " dealer busts, you win.");
			
			System.out.println("If you lose, the amount you bet is deducted "
					+ "from your balance. If you win, the amount is added.");
			
			System.out.println("If the round ends with a hand that sums to "
					+ "21, also called a \"Blackjack\", 1.5 times that "
					+ "bet is added or subtracted.");
			
			System.out.println("For example, if you bet $10 and lose, and the "
					+ "dealer has a Blackjack, $15 would be subtracted instead"
					+ " of $10.");
			
			System.out.println("Cards are worth their printed value. Face cards"
					+ " have a value of 10.");
			
			System.out.println("However, Aces can have a value of 1 or 11,"
					+ "and this value can change throughout the round to the"
					+ " player's wish.");
			
			System.out.println("Additionally, instead of hitting or standing, "
					+ "the player also has the choices of doubling down or "
					+ "folding.");
			
			System.out.println("If you choose to double down, your bet is "
					+ "doubled and you recieve one more card.");
			
			System.out.println("Then, you stand for the rest of the round, "
					+ "and the dealer takes his turn.");
			
			System.out.println("It is a high-risk, high reward type of move.");
			
			System.out.println("If you fold, you automatically surrender and "
					+ "lose round, but only lose half of what you bet.");
			
			System.out.println("These two actions can only be performed on "
					+ "the first turn of a round.");
			
			System.out.println("Let's start! \n");
		}
	}
	
	//Assigns appropriate value to the card (eg. King= 10, Ace= 1 or 11)
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
	
	public static int aceAmount(String[] cardlist) {
		int amount= 0;
		
		
		for (String ele : cardlist) {
			if (ele.equals("Ace")) {
				amount++;
			}
		}
		
		return amount;
		
	}
	
	//On turn one offer more options
	public static String turnOne(double bet, double balance) {
		String choice;
		
		System.out.println("Hit, stand, double down, or fold? (h/s/d/f)");
		choice= scan.next();
		
		//Send player to while loop if bet is invalid
		if (choice.equalsIgnoreCase("d")) {
			if (bet*2 > balance) {
				System.out.println("Your bet would be greater than your"
						+ " balance if you doubled down!");
				choice= "";
			}
		}

		//Valid answer
		while (!(choice.equalsIgnoreCase("h") ||
				 choice.equalsIgnoreCase("s") ||
				 choice.equalsIgnoreCase("d") ||
				 choice.equalsIgnoreCase("f"))) {
			
			System.out.println("Enter a valid answer.");
			choice= scan.next();
			
			if (choice.equalsIgnoreCase("d")) {
				if (bet*2 > balance) {
					System.out.println("Your bet would be greater than your"
							+ " balance if you doubled down!");
					choice= "";
				}
			}
		}
		
		return choice;
		
	}
	
	//Not on turn one, offer hit or stand
	public static String notTurnOne() {
		String choice;
		
		System.out.println("Hit or stand? (h/s)");
		choice= scan.next();

		//Valid hit or stand
		while (!(choice.equalsIgnoreCase("h") ||
				choice.equalsIgnoreCase("s"))) {
			
			if (choice.equalsIgnoreCase("d") ||
				choice.equalsIgnoreCase("f")) {
				System.out.println("You can only do that on the first turn.");
			}
			
			else {
				System.out.println("Please enter a valid answer.");
			}
			
			choice= scan.next();
			
		}
		
		return choice;
		
	}
	
	//Decides if the dealer should hit or stand
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
		
		//Never hit if amount is 17 or above
		else {
			oppchoice= "s";
		}
		
		return oppchoice;
		
	}
	
	//Sets the card, making sure it is still available
	public static int cardSetter(int[] list) {
		int card;
		
		card= rand.nextInt(13);
		
		while (list[card] == 0) {
			card= rand.nextInt(13);
		}
		
		return card;
		
	}
	
	//Resets cardAmount array
	public static int[] cardAmountReset(int[] list) {
		
		for (int index= 0 ; index<list.length ; index++) {
			list[index]= 4;
		}
		
		return list;
		
	}
	
	//Add card to player's or dealer's list
	public static String[] appendCard(String[] list, String card) {
		String[] temp= new String[list.length+1];
		
		for (int index= 0 ; index < list.length ; index++) {
			temp[index]= list[index];
		}
		
		temp[list.length]= card;
		
		return temp;
		
	}
	
	//Print all the cards in the list
	public static void printCards(String[] list) {
		String sentence= "";
		
		for (String ele : list) {
			sentence += (ele + ", ");
		}
		
		System.out.print(sentence);
		
	}
	
	//If player wins
	public static double winEvent(double bet, double balance, int amount) {
		
		System.out.println("You won the round!");
		
		//Add bet to balance, check if player has Blackjack
		if (amount == 21) {
			bet *= 1.5;
			balance += bet;
			
			System.out.println("You had a Blackjack!");
			System.out.println("Because of this, $" + bet + " is added to"
					+ " your balance instead, totaling it to $" + balance
					+ ".");
		}
		
		else {
			balance += bet;
		
			System.out.println("$" + bet + " was added to your balance,"
				+ " totaling it to $" + balance + ".");
		}
		
		return balance;
		
	}
	
	//If player loses
	public static double loseEvent(double bet, double balance, int oppamount) {	
		
		System.out.println("You lost the round.");
		
		//Subtract bet from balance, check if dealer has Blackjack
		if (oppamount == 21) {
			
			if (bet * 1.5 > balance) {
				bet= balance;
			}
			
			else {
				bet *= 1.5;
			}
			
			bet -= balance;
			
			System.out.println("The dealer had a Blackjack.");
			System.out.println("Because of this, $" + bet + " is subtracted"
					+ " from your balance instead, totaling it to $" 
					+ balance + ".");
		}
		
		else {
			balance -= bet;
			
			System.out.println("$" + bet + " was subtracted from your "
					+ "balance, bringing it to $" + balance + ".");
		}
		
		return balance;
		
	}
	
	//Ask to play another round
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
	
	//Sleeps for a certain amount of time (seconds)
	public static void Sleep(int time) {
		
		//Sleep for a quick amount
		try {
			TimeUnit.SECONDS.sleep(time);
		}
		
		catch (InterruptedException ex) {
			System.out.println("Error.");
		}
		
	}
	
}
