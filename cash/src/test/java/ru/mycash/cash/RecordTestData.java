package ru.mycash.cash;

import ru.mycash.cash.model.Category;
import ru.mycash.cash.model.Record;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by RLuchinsky on 30.03.2018.
 */
public class RecordTestData {
    public static final Integer RECORD_ID_1 = 100005;
    public static final Integer RECORD_ID_2 = 100006;
    public static final Integer RECORD_ID_3 = 100007;
    public static final Integer RECORD_ID_4 = 100008;
    public static final Integer RECORD_ID_5 = 100009;
    public static final Integer RECORD_ID_6 = 100010;
    public static final Integer RECORD_ID_7 = 100011;
    public static final Integer RECORD_ID_8 = 100012;
    public static final Integer RECORD_ID_9_TO_TEST_CREATE = 100013;

    public static final Integer ADMIN_ID = 100001;
    public static final Integer USER_ID = 100000;

    public static final Category CATEGORY_1 = new Category(100002,"EAT");
    public static final Category CATEGORY_2 = new Category(100003,"ROAD");
    public static final Category CATEGORY_3 = new Category(100004,"SHOPING");


   public static final Record RECORD_1 = new Record(RECORD_ID_1,LocalDateTime.of(2015, Month.MAY, 30, 10,0), "BIG_MAC", CATEGORY_1, 500);
   public static final Record RECORD_2 = new Record(RECORD_ID_2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "COLA", CATEGORY_1, 1000);
    public static final Record RECORD_3 = new Record(RECORD_ID_3,LocalDateTime.of(2015, Month.MAY, 30, 20,0), "FANTA", CATEGORY_1, 500);
    public static final Record RECORD_4 = new Record(RECORD_ID_4, LocalDateTime.of(2015,Month.MAY,31, 10,0), "SUSHI", CATEGORY_1, 500);
     public static final Record RECORD_5 = new Record(RECORD_ID_5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "BUS", CATEGORY_2, 1000);
    public static final Record RECORD_6 = new Record(RECORD_ID_6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "TRAM", CATEGORY_2, 510);
    public static final Record RECORD_7 = new Record(RECORD_ID_7, LocalDateTime.of(2015, Month.JUNE, 01, 14, 0), "SHOSE", CATEGORY_3, 510);
    public static final Record RECORD_8 = new Record(RECORD_ID_8, LocalDateTime.of(2015, Month.JUNE, 01, 21, 0), "phone", CATEGORY_3, 1500);


    public static Record getUpdatedRECORD_1() {
        return new Record(RECORD_ID_1, RECORD_1.getDateTime(), "UPDATED", RECORD_1.getCategory(), 777);
    }

    public static void assertMatch(Record actual, Record expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "category");
    }

    public static void assertMatch(Iterable<Record> actual, Record... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Record> actual, Iterable<Record> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("category").isEqualTo(expected);
    }

}
