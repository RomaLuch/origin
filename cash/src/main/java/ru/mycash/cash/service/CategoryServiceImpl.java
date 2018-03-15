package ru.mycash.cash.service;


import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;
import ru.mycash.cash.repository.CategoryRepositoryImpl;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = getLogger(CategoryServiceImpl.class);
    CategoryRepository repoitory = new CategoryRepositoryImpl();

    @Override
    public Category create(Category category) {
        log.info("Create {}", category);
       return repoitory.save(category);
    }

    @Override
    public void update(Category category) {
        log.info("Update {}", category);
        repoitory.save(category);
    }

    @Override
    public void delete(Integer id) {
        log.info("delet category id({})", id);
        repoitory.delete(id);
    }

    @Override
    public Category get(Integer id) {
        log.info("get category id({})", id);
        return repoitory.get(id);
    }

    @Override
    public List<Category> getAll() {
        log.info("getAll");
        return new ArrayList<>(repoitory.getAll());
    }
}
