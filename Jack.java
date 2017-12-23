import java.util.Scanner;
import java.util.Random;

public class Jack {

	public static void main(String[] args) {

		//Variables
		int amount= 0, oppamount= 0, turn= 0, numrounds= 1, losses= 0, 
			wins= 0, ties= 0;
		double balance= 25.0, bet= 0.0;
		int owncard, oppcard;

		String round= "y", choice= "h", oppchoice= "h";
		String[] cards= {"Ace", "Two", "Three", "Four", "Five", "Six",
						 "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
						 "King"};
		String[] playercards= new String[0];
		String[] dealercards= new String[0];
			
		//Objects
		Scanner scan= new Scanner(System.in);
		Random rand= new Random();

		//Ask if player wants to play and if he wants instructions
		Functions.startGame();

		System.out.println("Your balance is $" + balance + ".");

		//While the balance is above 0 and the player wants to keep playing
		while (balance>0 && round.equalsIgnoreCase("y")) {

			turn += 1;

			//If it's the start of the round, ask the bet and give TWO cards
			if (turn == 1) {
				System.out.println("How  much would you like to bet?");
				bet= scan.nextDouble();

				//Valid bet
				while (bet>balance || bet<=0) {
					System.out.println("Enter a valid amount.");
					bet= scan.nextDouble();
				}

				//Two card giving
				owncard= rand.nextInt(13);
				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);

				owncard= Functions.valAssigner(owncard, amount);
				amount += owncard;

				owncard= rand.nextInt(13);
				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);

				owncard= Functions.valAssigner(owncard, amount);
				amount += owncard;

				System.out.println("Your total is " + amount + ".");
			}

			/*If the player stood skip asking them if he wants to hit or stand
			  , will go automatically to else statement*/
			if (!(choice.equalsIgnoreCase("s"))) {
				System.out.println();
				
				if (turn == 1) {
					choice= Functions.turnOne(bet, balance);
				}
				
				else {
					choice= Functions.notTurnOne();
				}
				
			}

			//If player hits
			if (choice.equalsIgnoreCase("h")) {
				/*Generate random card and see what range it is in, assign
				  the proper amount to add*/
				owncard= rand.nextInt(13);

				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);

				owncard= Functions.valAssigner(owncard, amount);

				//Adding to player's total
				amount += owncard;

				System.out.println("Your total is " + amount + ".");

				//If player busts
				if (amount>21) {
					System.out.println("You busted! \n");
					System.out.print("You had: ");
					Functions.printCards(playercards);

					balance= Functions.loseEvent(bet, balance);
					losses += 1;

					//If balance is 0 exit, don't display the text below
					if (balance == 0) {
						System.out.println("You lost all of your money!");
						break;
					}

					round= Functions.anotherRound();

					//Reset and add to variables if play again
					if (round.equalsIgnoreCase("y")) {
						turn= 0;
						amount= 0;
						choice= "h";
						oppamount= 0;
						oppchoice= "h";
						numrounds += 1;
						playercards= new String[0];
						dealercards= new String[0];
					}

					/*Return to the top, will check the round value and play
					  again accordingly*/
					continue;
				}
			}
			
			//Player doubles down
			else if (choice.equalsIgnoreCase("d")) {
				bet *= 2;
				
				System.out.println("You doubled down!");
				System.out.println("Your bet is now $" + bet + ".");
				
				owncard= rand.nextInt(13);

				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);

				owncard= Functions.valAssigner(owncard, amount);

				//Adding to player's total
				amount += owncard;

				System.out.println("Your total is " + amount + ".");
				
				if (amount>21) {
					System.out.println("You busted! \n");
					System.out.print("You had: ");
					Functions.printCards(playercards);

					balance= Functions.loseEvent(bet, balance);
					losses += 1;

					//If balance is 0 exit, don't display the text below
					if (balance == 0) {
						System.out.println("You lost all of your money!");
						break;
					}

					round= Functions.anotherRound();

					//Reset and add to variables if play again
					if (round.equalsIgnoreCase("y")) {
						turn= 0;
						amount= 0;
						choice= "h";
						oppamount= 0;
						oppchoice= "h";
						numrounds += 1;
						playercards= new String[0];
						dealercards= new String[0];
					}

					/*Return to the top, will check the round value and play
					  again accordingly*/
					continue;
				}
				
				else {
					System.out.println("You are now standing for the rest"
							+ " of the round.");
					choice= "s";
				}
			}
			
			//Player folds
			else if (choice.equalsIgnoreCase("f")) {
				balance -= (bet/2.0);
				losses += 1;
				
				System.out.println("You folded!");
				System.out.print("You had: ");
				Functions.printCards(playercards);
				System.out.println();
				
				System.out.println("Half of your bet, $" + (bet/2.0) + ", "
						+ "was subtracted from your balance, leaving you"
						+ " with $" + (balance) + ".");
				
				round= Functions.anotherRound();

				//Reset and add to variables if play again
				if (round.equalsIgnoreCase("y")) {
					turn= 0;
					amount= 0;
					choice= "h";
					oppamount= 0;
					oppchoice= "h";
					numrounds += 1;
					playercards= new String[0];
					dealercards= new String[0];
				}

				/*Return to the top, will check the round value and play
				  again accordingly*/
				continue;
				
			}
			
			//Sleep for one second
			Functions.Sleep(1);
			
			//Dealer's turn
			
			//If first turn
			if (turn == 1) {
				oppcard= rand.nextInt(13);
				dealercards= 
						Functions.appendCard(dealercards, cards[oppcard]);

				oppcard= Functions.valAssigner(oppcard, oppamount);
				oppamount += oppcard;

				oppcard= rand.nextInt(13);
				dealercards= 
						Functions.appendCard(dealercards, cards[oppcard]);

				oppcard= Functions.valAssigner(oppcard, oppamount);
				oppamount += oppcard;
			}

			//If the dealer is not standing, assign his choice
			if (!(oppchoice.equals("s"))) {
				oppchoice= Functions.dealerChoice(oppamount);
				
				if (oppchoice.equalsIgnoreCase("s")) {
					System.out.println("The dealer stood.");
				}
			}

			//If dealer hits
			if (oppchoice.equals("h")) {
				System.out.println("The dealer hit.\n");

				//Generate random card
				oppcard= rand.nextInt(13);
				dealercards= 
						Functions.appendCard(dealercards, cards[oppcard]);

				oppcard= Functions.valAssigner(oppcard, oppamount);

				//Add card to dealer's total
				oppamount += oppcard;

				//If dealer busts
				if(oppamount>21) {
					System.out.println("The dealer busted! \n");
					System.out.print("The dealer had: ");
					Functions.printCards(dealercards);
					System.out.println(", totaling to " + oppamount + ".");

					balance= Functions.winEvent(bet, balance);
					wins += 1;

					round= Functions.anotherRound();

					//Reset and add to variables if play again
					if (round.equalsIgnoreCase("y")) {
						turn= 0;
						amount= 0;
						choice= "h";
						oppamount= 0;
						oppchoice= "h";
						numrounds += 1;
						playercards= new String[0];
						dealercards= new String[0];
					}

					/*Return to the top, will check the round value and play
					  again accordingly*/
					continue;
				}
			}

			//If both stand, decide who wins the round
			if (oppchoice.equals("s") && choice.equalsIgnoreCase("s")) {
				System.out.println("The cards are being turned!");
				
				System.out.print("You had: ");
				Functions.printCards(playercards);
				System.out.print("totaling to " + amount + ". \n");
				
				Functions.Sleep(2);
				
				System.out.print("The dealer had: ");
				Functions.printCards(dealercards);
				System.out.print("totaling to " + oppamount + ". \n");

				if (amount>oppamount) {
					balance= Functions.winEvent(bet, balance);
					wins += 1;
				}

				else if (amount<oppamount) {
					balance= Functions.loseEvent(bet, balance);
					losses += 1;

					if (balance == 0) {
						System.out.println("You lost all of your money!");
						break;
					}
				}

				else {
					System.out.println("It was a tie!");
					System.out.println("Your balance remains at $" + balance
							+ ".");

					ties += 1;
				}

				round= Functions.anotherRound();

				//Reset and add to variables if play again
				if (round.equalsIgnoreCase("y")) {
					turn= 0;
					amount= 0;
					choice= "h";
					oppamount= 0;
					oppchoice= "h";
					numrounds += 1;
					playercards= new String[0];
					dealercards= new String[0];
				}
			}
		}

		System.out.println("You played a total of " + numrounds + " rounds,"
				+ " with " + wins + " wins, " + losses + " losses, and "
						+ ties + " ties.");

		System.out.println("You ended with $" + balance + ".");

		if (balance>=25) {
			System.out.println("You made $" + (balance-25) + "!");
		}

		else {
			System.out.println("You lost $" + (Math.abs(balance-25)) + "!");
		}

		scan.close();

	}
}
