package uno;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game {

	private int currentPlayer; // keep track of the player
	private String[] playerIds; // names of the player

	private UnoDeck deck;
	private ArrayList<ArrayList<UnoCard>> playerHand; // arrayList of arrayList, because every particular player hand is
														// an arrayList of unoCard
	private ArrayList<UnoCard> stockpile; // for when put down the card

	private UnoCard.Color validColor;
	private UnoCard.Value validValue;

	boolean gameDirection;

	public Game(String[] pids) {
		deck = new UnoDeck();
		deck.shuffle();
		stockpile = new ArrayList<UnoCard>();

		playerIds = pids;
		currentPlayer = 0;
		gameDirection = false;

		playerHand = new ArrayList<ArrayList<UnoCard>>();

		for (int i = 0; i < pids.length; i++) {
			ArrayList<UnoCard> hand = new ArrayList<UnoCard>(Arrays.asList(deck.drawCard(7))); // created a hand of uno
																								// card, which a new
																								// arraylist of uno
																								// card, drawing 7 cards
																								// as array
			playerHand.add(hand);
		}
	}

	public void start(Game game) {
		UnoCard card = deck.drawCard();
		validColor = card.getColor();
		validValue = card.getValue();

		if (card.getValue() == UnoCard.Value.Wild) {
			start(game);
		}

		if (card.getValue() == UnoCard.Value.WildFour || card.getValue() == UnoCard.Value.DrawTwo) {
			start(game);
		}

		if (card.getValue() == UnoCard.Value.Skip) {
			JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);

			if (gameDirection == false) {
				currentPlayer = (currentPlayer + 1) % playerIds.length;
			}

			else if (gameDirection == true) {
				currentPlayer = (currentPlayer - 1) % playerIds.length;
				if (currentPlayer == -1) {
					currentPlayer = playerIds.length - 1;
				}
			}
		}

		if (card.getValue() == UnoCard.Value.Reverse) {
			JLabel message = new JLabel(playerIds[currentPlayer] + " The game direction changed!");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);
			gameDirection ^= true;
			currentPlayer = playerIds.length - 1;
		}

		stockpile.add(card);
	}

}
