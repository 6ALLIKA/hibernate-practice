package com.hibernate.dao.impl;

import com.hibernate.dao.GenreDao;
import com.hibernate.exception.DataProcessingException;
import com.hibernate.library.Dao;
import com.hibernate.model.Genre;
import com.hibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre add(Genre genre) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting " + genre, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Genre> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Genre");
            return query.list();
        }
    }
}
