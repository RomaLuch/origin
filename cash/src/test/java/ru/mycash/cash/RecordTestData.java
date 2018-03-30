package ru.mycash.cash;

import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;
import ru.mycash.cash.model.User;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created by RLuchinsky on 30.03.2018.
 */
public class RecordTestData {
    public static final Integer RECORD_ID_1 = 1;
    public static final Integer RECORD_ID_2 = 2;
    public static final Integer RECORD_ID_3 = 3;

    public static final Integer ADMIN_ID = 1;
    public static final Integer USER_ID = 2;


   public static final Record RECORD_1 = new Record(RECORD_ID_1, LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "обед",new Category(1,"еда"), 500);
   public static final Record RECORD_2 = new Record(RECORD_ID_2, LocalDateTime.of(2018, Month.MAY, 30, 11, 0), "такси", new Category(2,"транспорт"), 100);
   public static final Record RECORD_3 = new Record(RECORD_ID_3, LocalDateTime.of(2018, Month.MAY, 30, 12, 0), "кино", new Category(3,"развлечения"), 600);

}
