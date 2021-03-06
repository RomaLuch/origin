package ru.mycash.cash.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.repository.CategoryRepository;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithId;
import static ru.mycash.cash.util.ValidationUtil.checkNotFoundWithIdBoolean;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository repoitory;

    public CategoryServiceImpl() {
    }

    @Override
    public Category create(Category category, Integer userId) {
        log.info("Create {}", category);
       return repoitory.save(category, userId);
    }

    @Override
    public Category update(Category category, Integer userId) {
        log.info("Update {}", category);
        return checkNotFoundWithId(repoitory.save(category, userId),category.getId());
    }

    @Override
    public void delete(Integer id, Integer userId) {
        log.info("delet category id({})", id);
        checkNotFoundWithIdBoolean(repoitory.delete(id,userId),id);
    }

    @Override
    public Category get(Integer id, Integer userId) {
        log.info("get category id({})", id);
        return checkNotFoundWithId(repoitory.get(id, userId), id);
    }

    @Override
    public List<Category> getAll(Integer userId) {
        log.info("getAll");
        return new ArrayList<>(repoitory.getAll(userId));
    }
}
