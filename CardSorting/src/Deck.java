import java.util.Random;

/**
 * @author Marco Cecchi-Rivas
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
	 * @param sorted
	 */
	public Deck(boolean sorted) {
	    this();
	    if (!sorted) {
	        this.shuffle();
	    }
	}
	
	/**
	 * @param other
	 */
	public Deck(Deck other) {
        cards = new Card[getCards().length];
        topCard = getTopCard();

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
	 * @param tC
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
	        Card temp = cards[i];
	        cards[i] = cards[index];
	        cards[index] = temp;
	    }
	}
	
	/**
	 *
	 */
	public String toString() {
		String output = "";
		
		if (getTopCard() == 51) {	        
	        for (int i = 0; i < cards.length; i++) {
	            output += cards[i] + "\t";
	            
	            if ((i + 1) % 4 == 0)
	            	output += "\n";
	        }
		}
		else {
			for (int i = 0; i < getTopCard(); i++)
				output += (i+1) +". "+ cards[i] + "\n";
		}
		return output;
	}
	
	/**
	 *@param an object, preferably a Deck
	 *@return if the two decks are equal
	 */
	public boolean equals(Object other) {
	    if (other instanceof Deck) {
	        Deck otherDeck = (Deck) other;
	        if (getTopCard() != otherDeck.getTopCard()) {
	            return false;
	        }
	        return getCards().equals(otherDeck.getCards());
	    }
	    return false;
	}
	
	/**
	 * @param hands
	 * @param cardsPerHand
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
	 * @param card
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
	 * 
	 */
	public void selectionSort() {
	    for (int i = 0; i < cards.length; i++) {
	        int minIndex = i;
	        for (int j = i + 1; j < cards.length; j++) {
	            if (cards[j].compareTo(cards[minIndex]) < 0) {
	                minIndex = j;
	            }
	        }

	        Card temp = cards[i];
	        cards[i] = cards[minIndex];
	        cards[minIndex] = temp;
	    }
	}
	
	/**
	 * 
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

	/**
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
	    Card temp = cards[i];
	    cards[i] = cards[j];
	    cards[j] = temp;
	}


}
