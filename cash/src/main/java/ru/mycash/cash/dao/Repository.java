package ru.mycash.cash.dao;

import ru.mycash.cash.model.Record;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    public static Map<Integer, Record> getRepository() {
        Map<Integer, Record> repository = new HashMap<>();

        repository.put(1, new Record(LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "еда", 500));
        repository.put(2, new Record(LocalDateTime.of(2018, Month.MAY, 30, 11, 0), "транспорт", 100));
        repository.put(3, new Record(LocalDateTime.of(2018, Month.MAY, 30, 12, 0), "кино", 600));
    return repository;
    }
}