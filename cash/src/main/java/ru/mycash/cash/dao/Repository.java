package ru.mycash.cash.dao;

import ru.mycash.cash.model.Record;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    public static Map<Integer, Record> getRepository() {
        Map<Integer, Record> repository = new HashMap<>();

        repository.put(1, new Record(LocalDateTime.now(), "еда", 500));
        repository.put(2, new Record(LocalDateTime.now(), "транспорт", 100));
        repository.put(3, new Record(LocalDateTime.now(), "кино", 600));
    return repository;
    }
}