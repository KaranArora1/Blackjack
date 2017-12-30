import java.util.Scanner;

public class Jack {

	public static void main(String[] args) {

		//Variables
		int amount= 0, oppamount= 0, turn= 0, numrounds= 1, losses= 0, 
			wins= 0, ties= 0, aceamount= 0, newaceamount= 0, oppaceamount= 0,
			oppnewaceamount= 0;
		
		int owncard, oppcard;
		
		int[] cardAmount= {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
		
		double balance= 25.0, bet= 0.0;

		String round= "y", choice= "h", oppchoice= "h";
		String[] cards= {"Ace", "Two", "Three", "Four", "Five", "Six",
						 "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
						 "King"};
		String[] playercards= new String[0];
		String[] dealercards= new String[0];
			
		//Scanner Object
		Scanner scan= new Scanner(System.in);

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
				owncard= Functions.cardSetter(cardAmount);
				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);
				cardAmount[owncard] -= 1;

				owncard= Functions.valAssigner(owncard, amount);
				amount += owncard;
		
				owncard= Functions.cardSetter(cardAmount);
				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);
				cardAmount[owncard] -= 1;

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
				owncard= Functions.cardSetter(cardAmount);

				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);
				cardAmount[owncard] -= 1;

				owncard= Functions.valAssigner(owncard, amount);

				//Adding to player's total
				amount += owncard;

				System.out.println("Your total is " + amount + ".");

				//If player busts
				if (amount>21) {
					
					newaceamount= Functions.aceAmount(playercards);
					
					if (aceamount != newaceamount) {
						aceamount = newaceamount;
						amount -= 10;
						System.out.println("Your Ace is now worth 1 to " +
						"prevent you from busting.");
						System.out.println("Your total is now " + amount + 
								".");
					}
					
					else {
						System.out.println("You busted! \n");
						System.out.print("You had: ");
						Functions.printCards(playercards);
	
						balance= Functions.loseEvent(bet, balance, oppamount);
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
							aceamount= 0;
							newaceamount= 0;
							oppaceamount= 0;
							oppnewaceamount = 0;
							playercards= new String[0];
							dealercards= new String[0];
							cardAmount= Functions.cardAmountReset(cardAmount);
						}
	
						/*Return to the top, will check the round value and
						  play again accordingly*/
						continue;
					}
				}
			}
			
			//Player doubles down
			else if (choice.equalsIgnoreCase("d")) {
				bet *= 2;
				
				System.out.println("You doubled down!");
				System.out.println("Your bet is now $" + bet + ".");
				
				owncard= Functions.cardSetter(cardAmount);

				System.out.println("You got a " + cards[owncard] + ".");
				playercards= 
						Functions.appendCard(playercards, cards[owncard]);
				cardAmount[owncard] -= 1;

				owncard= Functions.valAssigner(owncard, amount);

				//Adding to player's total
				amount += owncard;

				System.out.println("Your total is " + amount + ".");
				
				if (amount>21) {
					
					newaceamount= Functions.aceAmount(playercards);
					
					if (aceamount != newaceamount) {
						aceamount = newaceamount;
						amount -= 10;
						System.out.println("Your Ace is now worth 1 to " +
						"prevent you from busting.");
						System.out.println("Your total is now " + amount + 
								".");
					}
					
					else {
						System.out.println("You busted! \n");
						System.out.print("You had: ");
						Functions.printCards(playercards);
	
						balance= Functions.loseEvent(bet, balance, oppamount);
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
							aceamount= 0;
							newaceamount= 0;
							oppaceamount= 0;
							oppnewaceamount = 0;
							playercards= new String[0];
							dealercards= new String[0];
							cardAmount= Functions.cardAmountReset(cardAmount);
						}
	
						/*Return to the top, will check the round value and 
						  play again accordingly*/
						continue;
					}
				}
				
				System.out.println("You are now standing for the rest"
							+ " of the round.");
					choice= "s";
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
					aceamount= 0;
					newaceamount= 0;
					oppaceamount= 0;
					oppnewaceamount = 0;
					playercards= new String[0];
					dealercards= new String[0];
					cardAmount= Functions.cardAmountReset(cardAmount);
				}

				/*Return to the top, will check the round value and play
				  again accordingly*/
				continue;
				
			}
			
			//Sleep for one second
			Functions.Sleep(2);
			
			//Dealer's turn
			
			//If first turn
			if (turn == 1) {
				oppcard= Functions.cardSetter(cardAmount);
				dealercards= 
						Functions.appendCard(dealercards, cards[oppcard]);
				cardAmount[oppcard] -= 1;

				oppcard= Functions.valAssigner(oppcard, oppamount);
				oppamount += oppcard;

				oppcard= Functions.cardSetter(cardAmount);
				dealercards= 
						Functions.appendCard(dealercards, cards[oppcard]);
				cardAmount[oppcard] -= 1;

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
				oppcard= Functions.cardSetter(cardAmount);
				dealercards= 
						Functions.appendCard(dealercards, cards[oppcard]);
				cardAmount[oppcard] -= 1;

				oppcard= Functions.valAssigner(oppcard, oppamount);

				//Add card to dealer's total
				oppamount += oppcard;

				//If dealer busts
				if (oppamount>21) {
					
					oppnewaceamount= Functions.aceAmount(dealercards);
					
					if (oppaceamount != oppnewaceamount) {
						oppaceamount = oppnewaceamount;
						oppamount -= 10;
					}
					
					else {
						System.out.println("The dealer busted! \n");
						System.out.print("The dealer had: ");
						Functions.printCards(dealercards);
						System.out.println(", totaling to " + oppamount + ".");
	
						balance= Functions.winEvent(bet, balance, amount);
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
							aceamount= 0;
							newaceamount= 0;
							oppaceamount= 0;
							oppnewaceamount = 0;
							playercards= new String[0];
							dealercards= new String[0];
							cardAmount= Functions.cardAmountReset(cardAmount);
						}
	
						/*Return to the top, will check the round value and
						  play again accordingly*/
						continue;
					}
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
					balance= Functions.winEvent(bet, balance, amount);
					wins += 1;
				}

				else if (amount<oppamount) {
					balance= Functions.loseEvent(bet, balance, oppamount);
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
					aceamount= 0;
					newaceamount= 0;
					oppaceamount= 0;
					oppnewaceamount = 0;
					playercards= new String[0];
					dealercards= new String[0];
					cardAmount= Functions.cardAmountReset(cardAmount);
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