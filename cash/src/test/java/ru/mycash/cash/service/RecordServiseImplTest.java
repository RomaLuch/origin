package ru.mycash.cash.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.mycash.cash.RecordTestData.*;

/**
 * Created by RLuchinsky on 04.04.2018.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RecordServiseImplTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private RecordService service;

    @Test
    public void create() throws Exception {
        Record record = new Record(LocalDateTime.of(2015, Month.JUNE, 01, 22, 0), "Computer", CATEGORY_3, 777);
        service.create(record, ADMIN_ID);
        assertMatch(service.get(RECORD_ID_9_TO_TEST_CREATE,ADMIN_ID),record);
    }

    @Test
    public void update() throws Exception {
        Record recordToUpdate = getUpdatedRECORD_1();
        service.update(recordToUpdate,USER_ID);
        assertMatch(service.get(RECORD_ID_1,USER_ID), recordToUpdate);
    }

    @Test (expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Record recordToUpdate = getUpdatedRECORD_1();
        service.update(recordToUpdate,ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RECORD_ID_1, USER_ID);
        service.delete(RECORD_ID_2, USER_ID);
        service.delete(RECORD_ID_3, USER_ID);
        service.delete(RECORD_ID_4, USER_ID);
        service.delete(RECORD_ID_5, USER_ID);
        assertMatch(service.getAll(USER_ID), RECORD_6);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(RECORD_ID_1, ADMIN_ID);
    }

    @Test
    public void get() throws Exception {
        Record record = service.get(RECORD_ID_1, USER_ID);
        assertMatch(record, RECORD_1);
    }

    @Test (expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        Record record = service.get(RECORD_ID_1, ADMIN_ID);
        assertMatch(record, RECORD_1);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(ADMIN_ID), RECORD_8, RECORD_7);
    }

}