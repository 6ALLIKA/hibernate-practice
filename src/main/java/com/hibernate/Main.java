package com.hibernate;

import com.hibernate.library.Injector;
import com.hibernate.model.Author;
import com.hibernate.model.Book;
import com.hibernate.model.Genre;
import com.hibernate.service.AuthorService;
import com.hibernate.service.BookService;
import com.hibernate.service.GenreService;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.hibernate");

    public static void main(String[] args) {
        Author author1 = new Author();
        author1.setName("name1");
        author1.setSurname("surname1");
        Author author2 = new Author();
        author2.setName("name2");
        author2.setSurname("surname2");
        AuthorService authorService = (AuthorService) injector.getInstance(AuthorService.class);
        authorService.add(author1);
        authorService.add(author2);

        Genre genre = new Genre();
        genre.setGenre("genre");
        Genre genre1 = new Genre();
        genre1.setGenre("genre1");
        GenreService genreService = (GenreService) injector.getInstance(GenreService.class);
        genreService.add(genre);
        genreService.add(genre1);

        Book book1 = new Book();
        book1.setAuthor(List.of(author1, author2));
        book1.setDescription("description1");
        book1.setGenre(genre);
        book1.setTitle("title1");
        Book book2 = new Book();
        book2.setAuthor(List.of(author1, author2));
        book2.setDescription("description2");
        book2.setGenre(genre1);
        book2.setTitle("title2");
        BookService bookService = (BookService) injector.getInstance(BookService.class);
        bookService.add(book1);
        bookService.add(book2);

        bookService.getAll().forEach(System.out::println);
        bookService.getByAuthor(author1).forEach(System.out::println);
        bookService.getByAuthor(author2).forEach(System.out::println);
        bookService.getByGenre(genre).forEach(System.out::println);
        bookService.getByGenre(genre1).forEach(System.out::println);
        System.out.println(bookService.getByTitle("title1"));
        System.out.println(bookService.getByTitle("title2"));

    }

}
