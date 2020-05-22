package com.hibernate.service.impl;

import com.hibernate.dao.AuthorDao;
import com.hibernate.library.Inject;
import com.hibernate.library.Service;
import com.hibernate.model.Author;
import com.hibernate.service.AuthorService;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
