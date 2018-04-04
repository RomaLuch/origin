package ru.mycash.cash.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.mycash.cash.model.Category;
import ru.mycash.cash.util.exception.NotFoundException;

import static ru.mycash.cash.RecordTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CategoryServiceImplTest {

    @Autowired
    CategoryService service;

    static {
        SLF4JBridgeHandler.install();
    }

    @Test
    public void create() throws Exception {
        Category category = new Category("NEW CATEGORY");
        service.create(category, ADMIN_ID);
        assertMatch(service.get(100013,ADMIN_ID), category);
    }

    @Test
    public void update() throws Exception {
        Category categoryToUpdate = getUpdatedCategory();
        service.update(categoryToUpdate,USER_ID);
        assertMatch(service.get(100002,USER_ID), categoryToUpdate);
    }

    @Test (expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Category categoryToUpdate = getUpdatedCategory();
        service.update(categoryToUpdate,ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100002, USER_ID);
        assertMatch(service.getAll(USER_ID), CATEGORY_2);
    }

    @Test (expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(100002, ADMIN_ID);
    }

    @Test
    public void get() throws Exception {
        assertMatch(service.get(100002, USER_ID),CATEGORY_1);
    }

    @Test
    public void getAll() throws Exception {
assertMatch(service.getAll(USER_ID), CATEGORY_1, CATEGORY_2);
    }

}