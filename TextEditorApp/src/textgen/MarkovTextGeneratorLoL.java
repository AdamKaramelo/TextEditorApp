package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText
	 * Algorithm used:<p>
			set "starter" to be the first word in the text  <br>
			set "prevWord" to be starter<br>
			for each word "w" in the source text starting at the second word<p>
			   check to see if "prevWord" is already a node in the list<p>
			      if "prevWord" is a node in the list<br>
			         add "w" as a nextWord to the "prevWord" node<br>
			      else<br>
			         add a node to the list with "prevWord" as the node's word<br>
			         add "w" as a nextWord to the "prevWord" node<br>
			    set "prevWord" = "w"<br>
	
			add starter to be a next word for the last word in the source text. 
	 */
	@Override
	public void train(String sourceText)
	{
		String[] words = sourceText.split("[\\s]+");
		/*for (int i = 0; i < words.length; i++) {
		    words[i] = words[i].replaceAll("[^\\w]", "");
		}*/
		starter = words[0];
		ListNode prevWord = new ListNode(starter);
		for(int i = 1; i < words.length; i++) {
			int check = containWordNode(wordList, prevWord);
			if(check < wordList.size()) {
				wordList.get(check).addNextWord(words[i]);
			}else {
				ListNode newNode = new ListNode(prevWord.getWord());
				wordList.add(newNode);
				newNode.addNextWord(words[i]);
			}
			prevWord.setWord(words[i]);
		}
		ListNode lastWord = new ListNode(words[words.length-1]);
		lastWord.addNextWord(starter);
		wordList.add(lastWord);
	}
	
	/** 
	 * Generate the number of words requested.
	 *  Algorithm used:<p>
		set "currWord" to be the starter word<br>
		set "output" to be ""<br>
		add "currWord" to output<br>
		while you need more words<p>
		   find the "node" corresponding to "currWord" in the list<br>
		   select a random word "w" from the "wordList" for "node"<br>
		   add "w" to the "output"<br>
		   set "currWord" to be "w" <br>
		   increment number of words added to the list<br>
		
	 */
	@Override
	public String generateText(int numWords) {
		
		String currWord = starter;
		String output = "";
		output= currWord;
		int size = 1;
		if(currWord == null) {
			output = "";
		}
		if(numWords == 0) {
			return "";
		}
		while(size < numWords) {
			for(ListNode node : wordList) {
				if(node.getWord().equals(currWord)) {
					String random = node.getRandomNextWord(rnGenerator);
					output = output.concat(" " + random);
					currWord = random;
					break;
				}
			}
			size++;
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText){
		for (ListNode node : wordList) {
			node.clearNextWords();
		}
		//wordList.clear();
		starter = "";
		train(sourceText);
	}
	
	/**
	 * Method that checks if the wordlist contains a word. 
	 * 
	 * @param wordList - the list to check
	 * @param word - specified word
	 * @return index of the contained word, otherwise list length
	 */
	public int containWordNode(List<ListNode> wordList, ListNode word) {
		int index = 0;
		for(ListNode w : wordList) {
			if (w.getWord().equals(word.getWord())) {
				break;
			}
			index++;
		}
		return index;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again. hello.";
		//System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		//System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list
 */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public void clearNextWords() {
		nextWords.clear();
	}
	
	public String getRandomNextWord(Random generator)
	{
		int n = generator.nextInt(nextWords.size());
	    return nextWords.get(n);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


