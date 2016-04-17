package twitter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface TweeterController {

	public void tweetMessage(String message) throws IOException, ParseException;
	public void listAll() throws IOException, ParseException;
	public void exit();
}
