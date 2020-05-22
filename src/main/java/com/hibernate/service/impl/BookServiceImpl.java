package com.hibernate.service.impl;

import com.hibernate.dao.BookDao;
import com.hibernate.library.Inject;
import com.hibernate.library.Service;
import com.hibernate.model.Author;
import com.hibernate.model.Book;
import com.hibernate.model.Genre;
import com.hibernate.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return bookDao.getByAuthor(author);
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return bookDao.getByGenre(genre);
    }
}
