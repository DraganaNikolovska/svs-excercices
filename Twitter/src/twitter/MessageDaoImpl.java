package twitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class MessageDaoImpl implements MessageDao {

	private TreeSet<Message> messages;
	private FileWriter fileWriter;
	private SimpleDateFormat sdf;

	public MessageDaoImpl() throws IOException {
		this.messages = new TreeSet<>();
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	}

	@Override
	public void insertMessage(String message) throws IOException {

		try {
			fileWriter = new FileWriter("messages.txt", true);
			String sDate = sdf.format(new Date());
			fileWriter.write(sDate + " - " + message);
			fileWriter.write(System.getProperty("line.separator"));
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			fileWriter.close();
		}

	}

	@Override
	public void findAll() throws IOException, ParseException {
		read();
		for (Message message : messages) {
			System.out.println(message);
		}

	}

	public void read() throws IOException, ParseException {
		BufferedReader reader = new BufferedReader(new FileReader("messages.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(" - ");
			Date date = sdf.parse(parts[0]);
			Message message = new Message(parts[1], date);
			messages.add(message);
		}
	}

}
