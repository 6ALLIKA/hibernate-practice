package com.hibernate.dao;

import com.hibernate.model.Genre;
import java.util.List;

public interface GenreDao {
    Genre add(Genre genre);

    List<Genre> getAll();
}
