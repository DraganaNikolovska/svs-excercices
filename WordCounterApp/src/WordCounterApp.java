import java.io.FileNotFoundException;

public class WordCounterApp {
	public static void main(String[] args) {
		if(args.length == 0)
			throw new InvalidArgumentsException("Argument list is empty!");
		WordCounter wordCounter = new WordCounter(args[0]);
		try {
			wordCounter.countUniqueWords();
			System.out.println(wordCounter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			wordCounter.scanner.close();
		}
	}
}
