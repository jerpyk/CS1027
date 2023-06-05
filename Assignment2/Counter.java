/**
 * Counter class for counting the number of points based on the hand of cards
 * and the starter card.
 * 
 * @author Eunsung Kim
 *
 */
public class Counter {
	PowerSet<Card> cardps; // Power Set of type Cards
	Card starter; // the starter Card

	/**
	 * Constructor for the Counter object, initializing the Power Set and the
	 * starter Card.
	 * 
	 * @param hand    array of Cards
	 * @param starter the starter Card
	 */
	public Counter(Card[] hand, Card starter) {
		this.starter = starter;
		this.cardps = new PowerSet<Card>(hand);
	}

	/**
	 * Returns the number of points from Pairs, Runs, Fifteen, Flush and His Knobs,
	 * based on the hand of cards and the starter.
	 * 
	 * @return integer value of the point
	 */
	public int countPoints() {
		// add all the ways to earn points
		int points = this.getPairs() + this.getRuns() + this.getFifteen() + this.getFlush() + this.getHisKnobs();
		return points;
	}

	/**
	 * Returns the number of points from Pairs, based on the hand of cards and the
	 * starter.
	 * 
	 * @return integer value of the point
	 */
	private int getPairs() {
		int points = 0;
		// repeat for every Card combination
		for (int i = 0; i < cardps.getLength(); i++) {
			// if the combination has two cards and the two cards have the same label
			if (cardps.getSet(i).getLength() == 2
					&& cardps.getSet(i).getElement(0).getLabel() == cardps.getSet(i).getElement(1).getLabel())
				points += 2; // add 2 points

		}
		return points;

	}

	/**
	 * Returns the number of points from Runs, based on the hand of cards and the
	 * starter.
	 * 
	 * @return integer value of the point
	 */
	private int getRuns() {
		int points = 0;
		int longestRun = 3; // initialize the longest run to 3
		// repeat for every card combination
		for (int i = 0; i < cardps.getLength(); i++) {
			// if the combination is a run and the length of the run is longer than 3
			if (cardps.getSet(i).getLength() > longestRun && this.isRun(cardps.getSet(i))) {
				// set the new longest run to the length of the combination
				longestRun = cardps.getSet(i).getLength();
			}
		}
		// repeat for every card combination
		for (int i = 0; i < cardps.getLength(); i++) {
			// if the combination is a run and the length of the run is same as the longest
			// run
			if (cardps.getSet(i).getLength() >= longestRun && this.isRun(cardps.getSet(i))) {
				points += longestRun; // add as many points as the length of the longest run
			}
		}
		return points;
	}

	/**
	 * Provided helper method to determine whether each set is a run.
	 * 
	 * @param set the set of Card objects
	 * @return boolean value
	 */
	private boolean isRun(Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes
		// a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to
		// represent the
		// range of card ranks from 1 to 13. We go through each card and increment the
		// cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index
		// 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done,
		// the array will
		// contain 5 or less cells with values of 1 or more to represent the number of
		// cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero
		// values to represent a run.

		int n = set.getLength();

		if (n <= 2)
			return false; // Run must be at least 3 in length.

		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++)
			rankArr[i] = 0; // Ensure the default values are all 0.

		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank() - 1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak)
					maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns the points from Fifteen, based on the hand of cards and the starter.
	 * 
	 * @return integer value of points
	 */
	private int getFifteen() {
		int points = 0;
		int setSum = 0; // sum for each set
		// repeat for every card combination
		for (int i = 0; i < cardps.getLength(); i++) {
			// repeat for every card in the card combination
			for (int j = 0; j < cardps.getSet(i).getLength(); j++) {
				// add the rank of the card
				setSum += cardps.getSet(i).getElement(j).getFifteenRank();
			}
			// if the sum of the card combination is exactly 15
			if (setSum == 15) {
				points += 2; // add 2 points
			}
			setSum = 0; // reset the sum
		}
		return points;
	}

	/**
	 * Returns the points from Flush, based on the hand of cards and the starter.
	 * 
	 * @return integer value of points
	 */
	private int getFlush() {
		int points = 0;
		Set<Card> fullSet = new Set<Card>(); // the full Card set
		String suit = "";
		for (int i = 0; i < cardps.getLength(); i++) {
			// the full Card set is the one with length of 5
			if (cardps.getSet(i).getLength() == 5) {
				fullSet = cardps.getSet(i);
			}
		}
		// repeat for every card of the full card set
		for (int i = 0; i < 5; i++) {
			// if the card is the starter
			if (fullSet.getElement(i).getSuit().equals(this.starter.getSuit())
					&& fullSet.getElement(i).getLabel().equals(this.starter.getLabel())) {
				continue; // skip and continue the loop
				// if the card is from the hand
			} else {
				// set the first suit if it's the first
				if (suit.equals(""))
					suit = fullSet.getElement(i).getSuit();
				// if the suit are equal, continue the loop
				else if (suit.equals(fullSet.getElement(i).getSuit()))
					continue;
				// if the suit from hand is different, return 0;
				else
					return points;
			}
		}
		points += 4; // add 4 points if all 4 suit are the same
		if (suit == this.starter.getSuit())
			points++; // add 1 point more if the suit of the starter is also the same
		return points;
	}

	/**
	 * Returns the points from His Knobs, based on the hand of cards and the
	 * starter.
	 * 
	 * @return integer value of points
	 */
	private int getHisKnobs() {
		int points = 0;
		Set<Card> fullSet = new Set<Card>();
		for (int i = 0; i < cardps.getLength(); i++) {
			if (cardps.getSet(i).getLength() == 5) {
				fullSet = cardps.getSet(i); // full set is the card combination with length of 5
			}
		}
		// repeat for every card of the full card set
		for (int i = 0; i < 5; i++) {
			// if the card is the starter
			if (fullSet.getElement(i).getSuit().equals(this.starter.getSuit())
					&& fullSet.getElement(i).getLabel().equals(this.starter.getLabel()))
				continue; // skip and continue the loop
			// if the card is from the hand, the card's label is Jack, and the Jack has the same suit as the starter
			else if (fullSet.getElement(i).getLabel().equals("J")
					&& fullSet.getElement(i).getSuit().equals(this.starter.getSuit()))
				return 1; // add 1 point
		}
		return points;

	}

}
