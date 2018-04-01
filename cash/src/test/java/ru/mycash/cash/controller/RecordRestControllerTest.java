package ru.mycash.cash.controller;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mycash.cash.AuthorizedUser;
import ru.mycash.cash.repository.mock.InMemoryRecordRepositoryImpl;
import ru.mycash.cash.util.exception.NotFoundException;

import static ru.mycash.cash.RecordTestData.*;


/**
 * Created by RLuchinsky on 30.03.2018.
 */
public class RecordRestControllerTest {

    private static ConfigurableApplicationContext applicationContext;
    private static RecordRestController controller;


    @BeforeClass
    public static void beforeClass() throws Exception {
    applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/mock.xml");
    controller = applicationContext.getBean(RecordRestController.class);
        AuthorizedUser.setId(ADMIN_ID);
    }

    @AfterClass
    public static void afterClass() throws Exception {
    applicationContext.close();
    }

    @Before
    public void setUp()
    {
        InMemoryRecordRepositoryImpl recordRepository = applicationContext.getBean(InMemoryRecordRepositoryImpl.class);
        recordRepository.initToTests();
    }

    @Test
    public void delete() throws Exception {
        controller.delete(RECORD_ID_1);
        controller.delete(RECORD_ID_2);
    Assert.assertEquals(controller.getAll().size(),0);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        controller.delete(RECORD_ID_3);
 /*       Assert.assertEquals(controller.getAll().size(),1);*/
    }

}