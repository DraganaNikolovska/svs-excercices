import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordCounter {
	private String fileName;
	private HashMap<String, Integer> wordsCounter;
	protected Scanner scanner;

	public WordCounter(String fileName) {
		this.fileName = fileName;
		this.wordsCounter = new HashMap<>();
	}

	public void countUniqueWords() throws FileNotFoundException {
		scanner = new Scanner(new File(fileName));
		while (scanner.hasNextLine()) {
			String[] parts = scanner.nextLine().split(" ");
			for (String part : parts) {
				if (wordsCounter.containsKey(part.toLowerCase())) {
					wordsCounter.put(part.toLowerCase(), wordsCounter.get(part.toLowerCase()) + 1);
				} else {
					wordsCounter.put(part.toLowerCase(), 1);
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
