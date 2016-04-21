package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Book;

public class JDBCBookDao implements BookDao {

	public void insertBook(String isbn, String title) {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into book (isbn, title) values (?, ?)");
			preparedStatement.setString(1, isbn);
			preparedStatement.setString(2, title);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void updateBook(String isbn, String title) {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("update book set title = ? where isbn = ?");
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, isbn);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void deleteBook(String isbn) {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from book where isbn = ?");
			preparedStatement.setString(1, isbn);		
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public ArrayList<Book> listAll() {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from book");	
		
			ArrayList<Book> list = new ArrayList<Book>();
			ResultSet set = preparedStatement.executeQuery();
			while(set.next()){
				String isbn = set.getString("isbn");
				String title = set.getString("title");
				Book b = new Book(isbn, title);
				list.add(b);
			}
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}

}
