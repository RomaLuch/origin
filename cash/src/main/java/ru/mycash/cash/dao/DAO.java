package ru.mycash.cash.dao;

import ru.mycash.cash.model.Record;

import java.util.List;

public interface DAO {
    void create(Record record);
    void update(Record record);
    void delete(Integer id);
    Record get(Integer id);
    List<Record> getAll();
}
