package main;

import connection.ConnectionConfiguration;
import model.Book;
import model.BookDao;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner klawiatura = new Scanner(System.in);
        BookDao bookDao = new BookDao();
        ConnectionConfiguration.chceckConnection();

        while (true) {
            System.out.printf("\n What would you like to do? \n where \n 1-create a record, 2-read, 3-update, 4-delete, 5-quit \n");

            int decision = klawiatura.nextInt();


            if (decision == 1) {
                System.out.printf("Set params - title, author, isbn, year \n");
                String title = klawiatura.next();
                String author = klawiatura.next();
                String isbn = klawiatura.next();
                int year = klawiatura.nextInt();
                Book book = new Book(0, title, author, isbn, year);
                bookDao.create(book);
            } else if (decision == 2) {
                System.out.printf("Set id \n");
                int id = klawiatura.nextInt();
                Book book = bookDao.read(id);
                System.out.print(book.toString());
            } else if (decision == 3) {
                System.out.printf("Set id of record you want to update \n");
                int id = klawiatura.nextInt();
                System.out.printf("Set new params of the record \n");
                String title = klawiatura.next();
                String author = klawiatura.next();
                String isbn = klawiatura.next();
                int year = klawiatura.nextInt();
                Book book = new Book(id, title, author, isbn, year);
                bookDao.update(book);
            } else if (decision == 4) {
                System.out.printf("Pick id of record to delete \n");
                int id = klawiatura.nextInt();
                try {
                    bookDao.delete(id);
                } catch (NullPointerException e) {
                    System.out.printf("You want to remove a record which not exists");
                }
            } else if (decision == 5){
                break;
            } else {
                System.out.printf("Bad input - try again");
            }

        }
    }
}
