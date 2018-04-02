package ru.mycash.cash.util;

import ru.mycash.cash.model.Record;

import java.util.List;

public class RecordsUtil {

    public static Integer getTotal(List<Record> records)

    {
        if (records==null) return 0;
        return records.stream().mapToInt(Record::getAmount).sum();
    }
}
