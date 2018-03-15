package ru.mycash.cash.controller;



import org.slf4j.Logger;
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
        log.info("create record id({})", record);
        return service.create(record);
    }
    public Category createCategory(Category category) {
        log.info("create category id({})", category);
        return categoryService.create(category);
    }


    public void update(Record record) {
        log.info("update record id({})", record);
        service.update(record);
    }
    public void updateCategory(Category category) {
        log.info("update category id({})", category);
        categoryService.update(category);
    }


    public void delete(Integer id) {
        log.info("delet record id({})", id);
        service.delete(id);
    }
    public void deleteCategory(Integer id) {
        log.info("delet category id({})", id);
        categoryService.delete(id);
    }


    public Record get(Integer id) {
        log.info("get record id({})", id);
        return  service.get(id);
    }
    public Category getCategory(Integer id) {
        log.info("get category id({})", id);
        return  categoryService.get(id);
    }

    public List<Record> getAll() {
        log.info("getAllRecords");
        return new ArrayList<>(service.getAll());
    }
    public List<Category> getAllCategories() {
        log.info("getAllRecords");
        return new ArrayList<>(categoryService.getAll());
    }
}