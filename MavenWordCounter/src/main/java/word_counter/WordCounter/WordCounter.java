package word_counter.WordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class WordCounter {

	private String fileName;
	private HashMap<String, Integer> wordsCounter;
	protected Scanner scanner;

	public WordCounter(String fileName) {
		this.fileName = fileName;
		this.wordsCounter = new HashMap<String, Integer>();
	}

	public void countUniqueWords() throws FileNotFoundException {
		scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			String[] parts = scanner.nextLine().split(" ");
			for (String part : parts) {
				if (wordsCounter.containsKey(StringUtils.lowerCase(part))) {
					wordsCounter.put(StringUtils.lowerCase(part), wordsCounter.get(StringUtils.lowerCase(part)) + 1);
				} else {
					wordsCounter.put(StringUtils.lowerCase(part), 1);
				}
			}
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : wordsCounter.keySet()) {
			sb.append(key + " " + wordsCounter.get(key));
			sb.append("\n");
		}
		return sb.toString();
	}
}
