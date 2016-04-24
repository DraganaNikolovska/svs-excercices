package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import domain.Entity;

public class JDBCBookDao implements BookDao {

	public void insert(Entity entity) {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into book (isbn, title) values (?, ?)");
			Book b = (Book) entity;
			preparedStatement.setString(1, b.getIsbn());
			preparedStatement.setString(2, b.getTitle());
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

	public void update(Entity entity) {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("update book set title = ? where isbn = ?");
			Book b = (Book) entity;
			preparedStatement.setString(1, b.getTitle());
			preparedStatement.setString(2, b.getIsbn());
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

	public void delete(Object uniqueValue) {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("delete from book where isbn = ?");
			preparedStatement.setString(1, (String) uniqueValue);
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

	public List<Entity> listAll() {
		Connection connection = null;
		try {
			connection = MyDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from book");
			ResultSet set = preparedStatement.executeQuery();
			List<Entity> list = new ArrayList<Entity>();
			while (set.next()) {
				String isbn = set.getString("isbn");
				String title = set.getString("title");
				Book b = new Book();
				b.setIsbn(isbn);
				b.setTitle(title);
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


	@Override
	public Book findByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}


}
