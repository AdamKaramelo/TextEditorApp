package spelling;

import java.util.List;

/**
 * Interface for predicting words based on prefix input.
 *
 */
public interface AutoComplete {
	public List<String> predictCompletions(String prefix, int numCompletions);
}
