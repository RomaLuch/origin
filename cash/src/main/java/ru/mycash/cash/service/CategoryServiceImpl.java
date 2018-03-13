package ru.mycash.cash.service;

import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;
import ru.mycash.cash.repository.CategoryRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryRepository repoitory = new CategoryRepositoryImpl();

    @Override
    public void save(Category category) {
        repoitory.save(category);
    }

    @Override
    public void delete(Integer id) {
repoitory.delete(id);
    }

    @Override
    public Category get(Integer id) {
        return repoitory.get(id);
    }

    @Override
    public List<Category> getAll() {
        return new ArrayList<>(repoitory.getAll());
    }
}
