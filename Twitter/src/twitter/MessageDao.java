package twitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface MessageDao {

	public void insertMessage(String message) throws IOException;
	public void findAll() throws FileNotFoundException, IOException, ParseException;
}
