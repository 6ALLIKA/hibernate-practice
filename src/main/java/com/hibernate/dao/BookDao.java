package com.hibernate.dao;

import com.hibernate.model.Author;
import com.hibernate.model.Book;
import com.hibernate.model.Genre;
import java.util.List;

public interface BookDao {
    Book add(Book book);

    Book getByTitle(String title);

    List<Book> getAll();

    List<Book> getByAuthor(Author author);

    List<Book> getByGenre(Genre genre);
}
