
public class Card implements Comparable<Card> {

	private enum Suits {
		HEARTS, DIAMONDS, CLUBS, SPADES
	}

	private Suits suit;
	private int rank;

	/**
	 * 
	 */
	public Card() {
		suit = Suits.SPADES;
		rank = 1;
	}

	/**
	 * @param suit
	 * @param rank
	 */
	public Card(int suit, int rank) {
		super();
		this.suit = Suits.values()[suit];
		this.rank = rank;
	}

	/**
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, String rank) {

		this.suit = Suits.valueOf(suit);
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
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, int rank) {
		this.suit = Suits.valueOf(suit);
		this.rank = rank;
	}

	/**
	 * @param suit
	 * @param rank
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
	 * @param c
	 */
	public Card(Card c) {
		suit = c.getSuit();
		rank = c.getRank();
	}
	
	/**
	 * @return the suit
	 */
	public Suits getSuit() {
		return suit;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
}
