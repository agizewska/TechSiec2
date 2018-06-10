package model;

import connection.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {

    private Connection connection;

    public BookDao() {
        connection = ConnectionConfiguration.getConnection();
    }

    public void create (Book book){
        final String sql = "INSERT INTO books(title, author, isbn, year) values(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.executeUpdate();
            System.out.printf("Record created properly");
        } catch (SQLException e) {
            System.out.printf("Something went wrong with saving the record");
            e.printStackTrace();
        }
    }

    public Book read(int id){
        Book book = null;
        final String sql = "SELECT * FROM books WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                book = new Book();
                book.setIdbooks(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setYear(resultSet.getInt("year"));
                return book;
            } else {
                return null;
            }
        } catch (SQLException e){
            System.out.printf("Something went wrong with reading the record");
            e.printStackTrace();
            return null;
        }
    }

    public void update(Book book){
        final String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, year = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.setInt(5, book.getIdbooks());
            preparedStatement.executeUpdate();
            System.out.printf("Record updated properly");
        } catch (SQLException e) {
            System.out.printf("Something went wrong with update the record");
            e.printStackTrace();
        }
    }

    public void delete(int id){
        final String sql = "DELETE from books WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("Record deleted properly");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.printf("Something went wrong with deleting the record");
        }
    }
}
