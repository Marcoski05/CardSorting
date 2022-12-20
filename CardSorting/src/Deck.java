import java.util.Arrays;
import java.util.Random;

/**
 * @author Marco Cecchi-Rivas
 *
 */
/**
 * @author mcecc
 *
 */
public class Deck {
	
	private Card[] cards;
	private int topCard;
	
	/**
	 * 
	 */
	public Deck() {
		cards = new Card[52];
		int i = 0;
		for (int r = 1; r <= 13; r++) {
			for (int s = 0; s < 4; s++) {
				cards[i] = new Card (s, r);
				i++;
			}
		}
		topCard = 51;
	}
	
	/**
	 * @param sorted whether or not the made Deck should be sorted
	 */
	public Deck(boolean sorted) {
	    this();
	    if (!sorted) {
	        this.shuffle();
	    }
	}
	
	/**
	 * @param other the Deck being copied
	 */
	public Deck(Deck other) {
        cards = new Card[other.getCards().length];
        topCard = other.getTopCard();

        for (int i = 0; i < getCards().length; i++) {
            cards[i] = new Card(other.getCards()[i]);
        }
    }
	
	/**
	 * @return the Card[] array cards
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/**
	 * @return the index of the topCard
	 */
	public int getTopCard() {
		return topCard;
	}
	
	/**
	 * @param tC new topCard
	 */
	public void setTopCard(int tC) {
		topCard = tC;
	}
	
	/**
	 * 
	 */
	public void shuffle() {
	    Random rand = new Random();
	    for (int i = 0; i < cards.length; i++) {
	        int index = rand.nextInt(cards.length);
	        swap(i, index);
	    }
	}
	
	/**
	 * Prints the cards in four columns by suit, or one column if not a full deck
	 */
	public String toString() {
		String output = "";
		
		if (getTopCard() == 51) {	        
	        Card[] clubs = new Card[13];
	        int clubsIndex = 0;
	        Card[] diamonds = new Card[13];
	        int diamondsIndex = 0;
	        Card[] hearts = new Card[13];
	        int heartsIndex = 0;
	        Card[] spades = new Card[13];
	        int spadesIndex = 0;
	        
			for (Card c : cards) {
	        	if (c.getSuitInt() == 0)
	        		clubs[clubsIndex++] = c;
	        	else if (c.getSuitInt() == 1)
	        		diamonds[diamondsIndex++] = c;
	        	else if (c.getSuitInt() == 2)
	        		hearts[heartsIndex++] = c;
	        	else if (c.getSuitInt() == 3)
	        		spades[spadesIndex++] = c;
	        }
			
			for (int i = 0; i < clubs.length; i++) {
	            output += clubs[i];
	            if (clubs[i].toString().length() > 15)
	            	output += "\t";
	            else
	            	output += "\t\t";
	            
	            output += diamonds[i];
	            if (diamonds[i].toString().length() > 15)
	            	output += "\t";
	            else
	            	output += "\t\t";
	            
	            output += hearts[i];
	            if (hearts[i].toString().length() > 15)
	            	output += "\t";
	            else
	            	output += "\t\t";
	            
	            output += spades[i];
	            if (spades[i].toString().length() > 15)
	            	output += "\t";
	            else
	            	output += "\t\t";
	            
	            output += "\n";
	        }
		}
		else {
			for (int i = 0; i <= getTopCard(); i++)
				output += (i+1) +". "+ cards[i] + "\n";
		}
		return output;
	}
	
	/**
	 *@param other an object, preferably a Deck
	 *@return if the two decks are equal
	 */
	public boolean equals(Object other) {
	    if (other instanceof Deck) {
	        Deck otherDeck = (Deck) other;
	        if (getTopCard() != otherDeck.getTopCard()) {
	            return false;
	        }
	        return Arrays.equals(getCards(), otherDeck.getCards());
	    }
	    return false;
	}
	
	/**
	 * @param hands the number of hands to be created
	 * @param cardsPerHand the number of cards in each hand
	 * @return Deck[] array of the specified size and number of hands
	 */
	public Deck[] deal (int hands, int cardsPerHand) {
		if (topCard + 1 < hands*cardsPerHand)
			return null;
		
	    Deck[] decks = new Deck[hands];
	    for (int i = 0; i < hands; i++) {
	        decks[i] = new Deck();
	        decks[i].setTopCard(-1);
	    }
	    for (int i = 0; i < cardsPerHand; i++) {
	        for (int j = 0; j < hands; j++) {
	            decks[j].addCard(cards[topCard]);
	            setTopCard(getTopCard()-1);
	        }
	    }
	    return decks;
	}

	/**
	 * @param card the Card to be added to the deck
	 */
	public void addCard(Card card) {
		setTopCard(getTopCard()+1);
		cards[topCard] = card;
	}
	
	
	/**
	 * @return the random card removed from the deck
	 */
	public Card pick() {
	    Random rand = new Random();
	    int index = rand.nextInt(this.topCard + 1);
	    Card pickedCard = this.cards[index];

	    for (int i = index; i < this.topCard; i++) {
	        this.cards[i] = this.cards[i + 1];
	    }
	    this.cards[getTopCard()] = pickedCard;
	    setTopCard(getTopCard()-1);

	    return pickedCard;
	}
	
	/**
	 * Uses a selection sort algorithm to sort the Deck
	 */
	public void selectionSort() {
	    for (int i = 0; i < cards.length; i++) {
	        int minIndex = i;
	        for (int j = i + 1; j < cards.length; j++) {
	            if (cards[j].compareTo(cards[minIndex]) < 0) {
	                minIndex = j;
	            }
	        }

	        swap(i, minIndex);
	    }
	}
	
//	public long selectionBenchmarkSorted() {
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++)
//			selectionSort();
//		long endTime = System.currentTimeMillis();
//		System.out.println(startTime +" - "+ endTime);
//		long elapsedTime = endTime - startTime;
//		return elapsedTime;
//	}
//	
//	public long selectionBenchmarkShuffled() {
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++)
//			selectionSort();
//			shuffle();
//		long endTime = System.currentTimeMillis();
//		System.out.println(startTime +" - "+ endTime);
//		long elapsedTime = endTime - startTime;
//		return elapsedTime;
//	}
	
	/**
	 * Uses a merge sort algorithm to sort the Deck
	 */
	public void mergeSort() {
	    mergeSort(0, cards.length - 1);
	}

	/**
	 * @param start
	 * @param end
	 */
	private void mergeSort(int start, int end) {	
	    if (start < end) {
	        int mid = (start + end) / 2;

	        mergeSort(start, mid);

	        mergeSort(mid + 1, end);

	        merge(start, mid, end);
	    }
	}

	/**
	 * @param start
	 * @param mid
	 * @param end
	 */
	private void merge(int start, int mid, int end) {
	    int i = start, j = mid + 1;

	    while (i <= mid && j <= end) {
	        if (cards[i].compareTo(cards[j]) < 0) {
	            i++;
	        }
	        else {
	            for (int k = j; k > i; k--) {
	                swap(k, k - 1);
	            }
	            i++;
	            mid++;
	            j++;
	        }
	    }
	}
	
//	public long mergeBenchmarkSorted() {
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++)
//			mergeSort();
//		long endTime = System.currentTimeMillis();
//		System.out.println(startTime +" - "+ endTime);
//		long elapsedTime = endTime - startTime;
//		return elapsedTime;
//	}
//	
//	public long mergeBenchmarkShuffled() {
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++)
//			mergeSort();
//		long endTime = System.currentTimeMillis();
//		System.out.println(startTime +" - "+ endTime);
//		long elapsedTime = endTime - startTime;
//		return elapsedTime;
//	}

	/**
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
	    Card temp = cards[i];
	    cards[i] = cards[j];
	    cards[j] = temp;
	}
	
	
	/**
	 * @param args unused
	 * runs tests
	 */
	public static void main(String[] args) {
		
		Deck d1 = new Deck();
		Deck d2t = new Deck(true);
		Deck d3f = new Deck(false);
		Deck d4d1 = new Deck(d1);
		Deck d5d3f = new Deck(d3f);
		
		printAndCompare(d1, d2t, d3f, d4d1, d5d3f);
		
		d2t.shuffle();
		d4d1.shuffle();
		d3f.selectionSort();
		d5d3f.mergeSort();
		
		printAndCompare(d1, d2t, d3f, d4d1, d5d3f);
		
		System.out.println(d3f.pick());
		System.out.println(d3f.pick());
		System.out.println(d3f.pick());
		System.out.println();
		System.out.println(d3f);
		
		System.out.println(Arrays.toString(d1.deal(5, 7)));
		System.out.println(d1);
		
		System.out.println(d3f.deal(6, 10));
		
//		System.out.println(d2t.selectionBenchmark());
//		System.out.println(d5d3f.selectionBenchmark());
//		System.out.println(d2t.mergeBenchmark());
//		System.out.println(d5d3f.mergeBenchmark());
	}

	/**
	 * @param d1
	 * @param d2t
	 * @param d3f
	 * @param d4d1
	 * @param d5d3f
	 */
	private static void printAndCompare(Deck d1, Deck d2t, Deck d3f, Deck d4d1, Deck d5d3f) {
		System.out.println(d1);
		System.out.println(d2t);
		System.out.println(d3f);
		System.out.println(d4d1);
		System.out.println(d5d3f);
		
		System.out.println("d1 equals d2t: "+ d1.equals(d2t));
		System.out.println("d4d1 equals d5d3f: "+ d4d1.equals(d5d3f));
		System.out.println("d3f equals d5d3f: "+ d3f.equals(d5d3f));
		
		System.out.println("\n\n");
	}
}
