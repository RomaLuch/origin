package ru.mycash.cash.service;

import ru.mycash.cash.model.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    void save(Category record);
    void delete(Integer id);
    Category get(Integer id);
    List<Category> getAll();
}
