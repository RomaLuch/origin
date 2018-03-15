package ru.mycash.cash.repository;

import ru.mycash.cash.model.Record;

import java.util.Collection;
import java.util.List;

public interface Repository {
    Record save(Record record);
    void delete(Integer id);
    Record get(Integer id);
    Collection<Record> getAll();
}
