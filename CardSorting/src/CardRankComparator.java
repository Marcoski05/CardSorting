import java.util.Comparator;

/**
 * @author Marco Cecchi-Rivas
 *
 */
public class CardRankComparator implements Comparator<Card> {

	@Override
	public int compare(Card one, Card two) {
		return one.getRank() - two.getRank();
	}

}
