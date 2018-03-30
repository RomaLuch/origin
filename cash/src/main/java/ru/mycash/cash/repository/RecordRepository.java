package ru.mycash.cash.repository;

import ru.mycash.cash.model.Record;

import java.util.Collection;
import java.util.List;

public interface RecordRepository {
    Record save(Record record, Integer userId);
    boolean delete(Integer id, Integer userId);
    Record get(Integer id, Integer userId);
    Collection<Record> getAll(Integer userId);
}
