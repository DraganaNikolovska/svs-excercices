package twitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.TreeSet;

public class Twitter implements TweeterController {

	private MessageDao messageDao;

	public Twitter() throws IOException {
		this.messageDao = new MessageDaoImpl();
	}

	@Override
	public void tweetMessage(String message) throws IOException {
		messageDao.insertMessage(message);
	}

	@Override
	public void listAll() throws IOException, ParseException {
		for (Message message : messageDao.getAllMessages()) {
			System.out.println(message);
		}

	}

	@Override
	public void exit() {
		return;

	}
}
