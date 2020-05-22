package com.hibernate.dao.impl;

import com.hibernate.dao.BookDao;
import com.hibernate.exception.DataProcessingException;
import com.hibernate.library.Dao;
import com.hibernate.model.Author;
import com.hibernate.model.Book;
import com.hibernate.model.Genre;
import com.hibernate.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book add(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting " + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            Predicate predicate = criteriaBuilder.equal(root.get("title"), title);
            Book book = session.createQuery(query.where(predicate)).getSingleResult();
            Hibernate.initialize(book.getAuthor());
            return book;
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }

    @Override
    public List<Book> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Book");
            return (List<Book>) query.list();
        }
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            Predicate predicate = criteriaBuilder.isMember(author, root.get("author"));
            List<Book> books = session.createQuery(query.where(predicate)).getResultList();
            return books;
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            Predicate predicate = criteriaBuilder.equal(root.get("genre"), genre);
            List<Book> books = session.createQuery(query.where(predicate)).getResultList();
            return books;
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }
}
