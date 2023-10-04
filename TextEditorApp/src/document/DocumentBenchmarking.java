package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * A class for timing the EfficientDocument and BasicDocument classes
 * 
 *
 */

public class DocumentBenchmarking {

	public static void main(String[] args) {

		// number of times each test runs
		int trials = 100;

		// The text to test on
		String textfile = "data/warAndPeace.txt";

		// The amount of characters to increment each step
		int increment = 20000;

		// The number of steps to run.
		int numSteps = 20;

		// THe number of characters to start with.
		int start = 50000;
		System.out.println("NumberOfChars \t BasicTime \tEfficientTime");
		for (int numToCheck = start; numToCheck < numSteps * increment + start; numToCheck += increment) {
			System.out.print(numToCheck);
			String text = getStringFromFile(textfile, numToCheck);

			long startTimeBasic = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				BasicDocument b = new BasicDocument(text);
				b.getFleschScore();
			}
			long endTimeBasic = System.nanoTime();
			double timeBasic = (endTimeBasic - startTimeBasic) / 100000000.0;
			System.out.print("\t\t " + timeBasic + "\t");

			long startTimeEff = System.nanoTime();

			for (int i = 0; i < trials; i++) {
				EfficientDocument e = new EfficientDocument(text);
				e.getFleschScore();
			}
			long endTimeEff = System.nanoTime();
			double timeEff = (endTimeEff - startTimeEff) / 100000000.0;
			System.out.println(timeEff);

		}

	}

	/**
	 * Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of
	 *         characters
	 */
	public static String getStringFromFile(String filename, int numChars) {

		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile = new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char) val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}

		return s.toString();
	}

}
