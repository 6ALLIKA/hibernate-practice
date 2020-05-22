package com.hibernate.dao;

import com.hibernate.model.Author;
import java.util.List;

public interface AuthorDao {
    Author add(Author author);

    List<Author> getAll();
}
