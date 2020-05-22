package com.hibernate.service;

import com.hibernate.model.Author;
import java.util.List;

public interface AuthorService {
    Author add(Author author);

    List<Author> getAll();
}
