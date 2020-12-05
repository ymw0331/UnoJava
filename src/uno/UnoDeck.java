package uno;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

/*
 * The Deck class consists of 108 Uno Cards. There are four suite, Red, Green, Blue, and Yellow
 * Each consisting of one 0 card, two 1 cards, 2s, 3s, 4s, 5s, 6s, 7s, 8s, 9s; two Draw Two cards; two Skip cards; and two Reverse cards.
 * In addition, there are four Wild cards and four Wild Draw Four cards.
  */

public class UnoDeck {
	private UnoCard[] cards;
	private int cardsInDeck;

	public UnoDeck() {
		cards = new UnoCard[108];
	}

	// filling the array of values into the array of colors
	// cycle thru the array and assign the value
	public void reset() {
		UnoCard.Color[] colors = UnoCard.Color.values();
		cardsInDeck = 0;

		// For Red, Blue, Green and Yellow except Wild
		for (int i = 0; i < colors.length - 1; i++) {
			UnoCard.Color color = colors[i];

			// Assign values for card 0 because it need only one card
			cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(0));

			// Assign values for card 1 to 9 that need 2 cards
			for (int j = 1; j < 10; j++) {
				cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
				cards[cardsInDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
			}

			// How to use for-each Loop?
			// for(dataType item : array){}
			// Assign values for power card such as DrawTwo, Skip and Reverse
			UnoCard.Value[] values = new UnoCard.Value[] { UnoCard.Value.DrawTwo, UnoCard.Value.Skip,
					UnoCard.Value.Reverse };
			// enhanced for loop, to make a placeholder value cycle thru all the value
			for (UnoCard.Value value : values) {
				cards[cardsInDeck++] = new UnoCard(color, value);
				cards[cardsInDeck++] = new UnoCard(color, value);
			}

		}

		// Out of loop as Wild card does not have color
		// Assign value for wild card
		UnoCard.Value[] values = new UnoCard.Value[] { UnoCard.Value.Wild, UnoCard.Value.WildFour };
		for (UnoCard.Value value : values) {
			// loop for 4 woildcard and wild4card
			for (int i = 0; i < 4; i++) {
				cards[cardsInDeck++] = new UnoCard(UnoCard.Color.Wild, value);
			}

		}

	}

	/*
	 * @param cards (stockpile) replaces the deck with an arraylist of UnoCards (the
	 * stockpile)
	 */

	public void replaceDeckWith(ArrayList<UnoCard> cards) {
		// turning this array of uno cards into normal array of uno cards
		this.cards = cards.toArray(new UnoCard[cards.size()]);
		this.cardsInDeck = this.cards.length;
	}

	/*
	 * @return
	 */
	public boolean isEmpty() {
		return cardsInDeck == 0;
	}

	public void shuffle() {
		int n = cards.length;
		Random random = new Random();

		for (int i = 0; i < cards.length; i++) {
			// Get a random of the array past the current index
			// ....The argument is an exclusive bound
			// Swap the random element with the present element

			int randomValue = i + random.nextInt(n - i); // get random value
			UnoCard randomCard = cards[randomValue]; // get random card
			cards[randomValue] = cards[i]; // index of array of random value will put the current card
			cards[i] = randomCard;

		}

	}

	// return a singular Uno Card
	public UnoCard drawCard() throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("Cannot draw a card since there is no cards in the deck");
		}
		return cards[--cardsInDeck];

	}

	public ImageIcon drawCardImage() throws IllegalArgumentException {
		if (isEmpty()) {
			throw new IllegalArgumentException("Cannot draw a card since the deck is empty");
		}
		return new ImageIcon(cards[cardsInDeck].toString() + ".png");
	}

	// return array of uno card
	public UnoCard[] drawCard(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Must draw positive card but tried to draw " + n + "cards.");
		}
		
		if (n > cardsInDeck) {
			throw new IllegalArgumentException("Cannot draw " + n + " cards since there are only " + cardsInDeck + " cards. ");
		}
		
		UnoCard[] ret = new UnoCard[n];
		
		for (int i = 0; i < n; i++) {
			ret[i] = cards[--cardsInDeck];
			
		}
		return ret;
		
	}

}
