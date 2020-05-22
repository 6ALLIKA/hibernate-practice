package com.hibernate.service;

import com.hibernate.model.Genre;
import java.util.List;

public interface GenreService {
    Genre add(Genre genre);

    List<Genre> getAll();
}
