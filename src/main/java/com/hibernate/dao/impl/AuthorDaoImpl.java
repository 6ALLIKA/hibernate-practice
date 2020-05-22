package com.hibernate.dao.impl;

import com.hibernate.dao.AuthorDao;
import com.hibernate.exception.DataProcessingException;
import com.hibernate.library.Dao;
import com.hibernate.model.Author;
import com.hibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author add(Author author) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting " + author, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Author> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Author");
            return query.list();
        }
    }
}
