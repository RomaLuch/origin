package ru.mycash.cash.controller;



import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.mycash.cash.AuthorizedUser;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.service.CategoryService;
import ru.mycash.cash.service.RecordService;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class RecordRestController
{
    private RecordService recordService;
    private CategoryService categoryService;

    public RecordRestController(RecordService recordService, CategoryService categoryService) {
        this.recordService = recordService;
        this.categoryService = categoryService;
    }

    private static final Logger log = getLogger(RecordRestController.class);

    public Record create(Record record) {
        int user_id = AuthorizedUser.id();
        log.info("create record id({})", record);
        return recordService.create(record, user_id);
    }
    public Category createCategory(Category category) {
        int user_id = AuthorizedUser.id();
        log.info("create category id({})", category);
        return categoryService.create(category, user_id);
    }


    public void update(Record record) {
        int user_id = AuthorizedUser.id();
        log.info("update record id({})", record);
        recordService.update(record, user_id);
    }
    public void updateCategory(Category category) {
        int user_id = AuthorizedUser.id();
        log.info("update category id({})", category);
        categoryService.update(category, user_id);
    }


    public void delete(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("delet record id({})", id);
        recordService.delete(id, user_id);
    }
    public void deleteCategory(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("delet category id({})", id);
        categoryService.delete(id, user_id);
    }


    public Record get(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("get record id({})", id);
        return  recordService.get(id,user_id);
    }
    public Category getCategory(Integer id) {
        int user_id = AuthorizedUser.id();
        log.info("get category id({})", id);
        return  categoryService.get(id, user_id);
    }

    public List<Record> getAll() {
        int user_id = AuthorizedUser.id();
        log.info("getAllRecords");
        return new ArrayList<>(recordService.getAll(user_id));
    }
    public List<Category> getAllCategories() {
        int user_id = AuthorizedUser.id();
        log.info("getAllRecords");
        return new ArrayList<>(categoryService.getAll(user_id));
    }

    public List<Record> getAllFiltred(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer categoryId) {
        int user_id = AuthorizedUser.id();
       return recordService.getAllFiltred(startDate, endDate, startTime,endTime,categoryId, user_id);

    }
}