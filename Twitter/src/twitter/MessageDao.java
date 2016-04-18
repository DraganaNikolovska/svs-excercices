package twitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.TreeSet;

public interface MessageDao {

	public void insertMessage(String message) throws IOException;
	public TreeSet<Message> getAllMessages() throws FileNotFoundException, IOException, ParseException;
}
