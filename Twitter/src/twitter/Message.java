package twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message> {

	private String message;
	private Date date;

	public Message(String message, Date date) {
		this.message = message;
		this.date = date;
	}

	@Override
	public int compareTo(Message o) {
		return o.date.compareTo(date);
	}

	@Override
	public String toString() {
		return date + " " + message;
	}

}
