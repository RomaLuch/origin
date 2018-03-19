package ru.mycash.cash.controller;



import org.slf4j.Logger;
import ru.mycash.cash.AuthorizedUser;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.service.CategoryService;
import ru.mycash.cash.service.CategoryServiceImpl;
import ru.mycash.cash.service.Service;
import ru.mycash.cash.service.ServiseImpl;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class RecordRestController
{
    private Service service = new ServiseImpl();
    private CategoryService categoryService = new CategoryServiceImpl();


private static final Logger log = getLogger(RecordRestController.class);

    public Record create(Record record) {
        int user_id = AuthorizedUser.id();
        log.info("create record id({})", record);
        return service.create(record, user_id);
    }
    public Category createCategory(Category category) {
        int user_id = AuthorizedUser.id();
        log.info("create category id({})", category);
        return categoryService.create(category);
    }


    public void update(Record record) {
        int user_id = AuthorizedUser.id();
        log.info("update record id({})", record);
        service.update(record, user_id);
    }
    public void updateCategory(Category category) {
        int user_id = AuthorizedUser.id();
        log.info("update category id({})", category);
        categoryService.update(category);
    }


    public void delete(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("delet record id({})", id);
        service.delete(id, user_id);
    }
    public void deleteCategory(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("delet category id({})", id);
        categoryService.delete(id);
    }


    public Record get(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("get record id({})", id);
        return  service.get(id,user_id);
    }
    public Category getCategory(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("get category id({})", id);
        return  categoryService.get(id);
    }

    public List<Record> getAll() {
        int user_id = AuthorizedUser.id();
        log.info("getAllRecords");
        return new ArrayList<>(service.getAll(user_id));
    }
    public List<Category> getAllCategories() {
        int user_id = AuthorizedUser.id();
        log.info("getAllRecords");
        return new ArrayList<>(categoryService.getAll());
    }
}