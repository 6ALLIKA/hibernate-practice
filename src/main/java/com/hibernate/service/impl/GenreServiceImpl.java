package com.hibernate.service.impl;

import com.hibernate.dao.GenreDao;
import com.hibernate.library.Inject;
import com.hibernate.library.Service;
import com.hibernate.model.Genre;
import com.hibernate.service.GenreService;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
