/**
 * @author Marco Cecchi-Rivas
 *
 */
public class Card implements Comparable<Card> {

	/**
	 * used to keep track of the suit of a card
	 */
	private enum Suits {
		CLUBS, DIAMONDS, HEARTS, SPADES;
		@Override
		public String toString() {
			return this.name().substring(0,1)+this.name().substring(1).toLowerCase();
		}
	}

	private Suits suit;
	private int rank;

	/**
	 * Default no args constructor
	 * Creates Ace of Spades
	 */
	public Card() {
		suit = Suits.SPADES;
		rank = 1;
	}

	/**
	 * @param suit Card's suit as an int
	 * @param rank Card's rank as an int
	 */
	public Card(int suit, int rank) {
		super();
		this.suit = Suits.values()[suit];
		this.rank = rank;
	}

	/**
	 * @param suit Card's suit as a String
	 * @param rank Card's rank as a String
	 */
	public Card(String suit, String rank) {

		this.suit = Suits.valueOf(suit.toUpperCase());
		switch (rank.toUpperCase()) {
		case "ACE":
			this.rank = 1;
			break;
		case "JACK":
			this.rank = 11;
			break;
		case "QUEEN":
			this.rank = 12;
			break;
		case "KING":
			this.rank = 13;
			break;
		default:
			this.rank = Integer.parseInt(rank);
			break;
		}
	}

	/**
	 * @param suit Card's suit as a String
	 * @param rank Card's rank as an int
	 */
	public Card(String suit, int rank) {
		this.suit = Suits.valueOf(suit.toUpperCase());
		this.rank = rank;
	}

	/**
	 * @param suit Card's suit as an int
	 * @param rank Card's rank as a String
	 */
	public Card(int suit, String rank) {
		this.suit = Suits.values()[suit];
		switch (rank.toUpperCase()) {
		case "ACE":
			this.rank = 1;
			break;
		case "JACK":
			this.rank = 11;
			break;
		case "QUEEN":
			this.rank = 12;
			break;
		case "KING":
			this.rank = 13;
			break;
		default:
			this.rank = Integer.parseInt(rank);
			break;
		}
	}

	/**
	 * @param card Card being copied
	 */
	public Card(Card card) {
		suit = Suits.valueOf(card.getSuit());
		rank = card.getRank();
	}
	
	/**
	 * @return the suit as a String
	 */
	public String getSuit() {
		return suit.name();
	}
	
	/**
	 * @return the suit as an int
	 */
	public int getSuitInt() {
		int suitInt = 0;
		
		if (suit == Suits.DIAMONDS)
			suitInt = 1;
		if (suit == Suits.HEARTS)
			suitInt = 2;
		if (suit == Suits.SPADES)
			suitInt = 3;
		
		return suitInt;
	}

	/**
	 * @return the rank as an int
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * @return the rank as a String
	 */
	public String getRankStr() {
		return Integer.toString(rank);
	}
	
	/**
	 * @return *Rank* of *Suit*
	 */
	@Override
	public String toString() {
		String cardString = "";
		
		if (rank == 1)
			cardString = "Ace of "+ suit.toString();
		else if (rank == 2)
			cardString = "Two of "+ suit.toString();
		else if (rank == 3)
			cardString = "Three of "+ suit.toString();
		else if (rank == 4)
			cardString = "Four of "+ suit.toString();
		else if (rank == 5)
			cardString = "Five of "+ suit.toString();
		else if (rank == 6)
			cardString = "Six of "+ suit.toString();
		else if (rank == 7)
			cardString = "Seven of "+ suit.toString();
		else if (rank == 8)
			cardString = "Eight of "+ suit.toString();
		else if (rank == 9)
			cardString = "Nine of "+ suit.toString();
		else if (rank == 10)
			cardString = "Ten of "+ suit.toString();
		else if (rank == 11)
			cardString = "Jack of "+ suit.toString();
		else if (rank == 12)
			cardString = "Queen of "+ suit.toString();
		else if (rank == 13)
			cardString = "King of "+ suit.toString();
		
		return cardString;
	}

	public int compareTo(Card other) {
		if (this.getRank() == other.getRank())
			return this.getSuitInt() - other.getSuitInt();
		else
			return this.getRank() - other.getRank();
	}
	
	public boolean equals(Object other) {
		return (other instanceof Card) && this.compareTo((Card)other) == 0;
	}
}
