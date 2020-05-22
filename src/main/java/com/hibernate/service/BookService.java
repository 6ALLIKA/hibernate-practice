package com.hibernate.service;

import com.hibernate.model.Author;
import com.hibernate.model.Book;
import com.hibernate.model.Genre;
import java.util.List;

public interface BookService {
    Book add(Book book);

    Book getByTitle(String title);

    List<Book> getAll();

    List<Book> getByAuthor(Author author);

    List<Book> getByGenre(Genre genre);
}
