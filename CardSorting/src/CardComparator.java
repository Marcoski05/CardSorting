import java.util.Comparator;

/**
 * @author Marco Cecchi-Rivas
 *
 */
public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card one, Card two) {
		return one.compareTo(two);
	}

}
